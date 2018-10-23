package co.ndan.yuri

import com.squareup.kotlinpoet.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.renderer.KeywordStringsGenerated.KEYWORDS
import java.io.File

open class Yuri : Plugin<Project> {
  fun generateProjectSources(path: String) {
    val walker = File(".").walkTopDown()
    val allFiles = walker
        .maxDepth(3)
        .filter { !it.path.contains(".git") }
        .filter { file -> !file.path.toCharArray().any { it.isDigit() } }
    val topLevelFiles = walker
        .maxDepth(1)
        .filter { !it.path.contains(".git") }
    val topLevelFileNames = topLevelFiles
        .map { it.name.replace(".", "_dot_").replace("$", "_dollar_") }
    val allFileNames = allFiles
        .map {
          it.name.replace(".", "_dot_")
              .replace("-", "_dash_")
              .replace("$", "_dollar_")
        }
        .map { if (it in (KEYWORDS + "out")) "`$it`" else it }
        .toSet()

    val dirMap = allFiles.filter { it !in topLevelFiles }
        .map { file ->
          val elements = file.path
              .replace("-", "_dash_")
              .substring(2)
              .replace(".", "_dot_")
              .replace("$", "_dollar_")
              .split("/")
              .map { if (it in (KEYWORDS + "out")) "`$it`" else it }
              .asReversed()

          val value = elements.first()
          val key = elements.drop(1).joinToString("<") + "<projectDir>" + ">".repeat(elements.size - 2)

          Pair(key, value)
        }

    val header = "package generated\nimport generated.G.*\n"

    val suppressAnnotation = AnnotationSpec.builder(Suppress::class).addMember("unused").build()

    val Gkt = FileSpec.builder("", "G")
        .addComment("This file was generated by Yuri, a type-safe URI builder for Kotlin.")
        .addImport("java.io", "File")
        .addProperty(PropertySpec.builder("unused", String::class, KModifier.PRIVATE, KModifier.CONST)
            .initializer("\"UNUSED_PARAMETER\"")
            .build())
        .addType(TypeSpec.classBuilder(ClassName("", "G"))
            .addModifiers(KModifier.SEALED)
            .primaryConstructor(FunSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder("uri", String::class).defaultValue("\"/\"").build())
                .build())
            .addProperty(PropertySpec.builder("uri", String::class)
                .initializer("uri")
                .build())
            .addType(TypeSpec.objectBuilder("projectDir")
                .superclass(ClassName("", "G"))
                .addSuperclassConstructorParameter("\"$path\"")
                .addFunctions(topLevelFileNames.map {
                  FunSpec.builder("div")
                      .addModifiers(KModifier.OPERATOR)
                      .addAnnotation(AnnotationSpec.builder(JvmName::class).addMember("\"$it\"").build())
                      .addParameter(ParameterSpec.builder("a", ClassName("", "$it.Companion"))
                          .addAnnotation(suppressAnnotation).build())
                      .addStatement("return $it<projectDir>(uri)")
                      .build()
                }.asIterable())
                .addFunction(FunSpec.builder("toString")
                    .addModifiers(KModifier.OVERRIDE)
                    .addStatement("return uri")
                    .build())
                .build())
            .apply {
              allFileNames.map {
                TypeSpec.classBuilder(ClassName("", it.replace("`", "")))
                    .addTypeVariable(TypeVariableName("T"))
                    .superclass(ClassName("", "G"))
                    .addSuperclassConstructorParameter("uri")
                    .primaryConstructor(FunSpec.constructorBuilder()
                        .addParameter(ParameterSpec.builder("uri", String::class).build())
                        .build())
                    .addType(TypeSpec.companionObjectBuilder().build())
                    .build()
              }.forEach { addType(it) }
            }
            .addFunction(FunSpec.builder("toString")
                .addModifiers(KModifier.OVERRIDE)
                .addStatement("return \"\$uri/\${javaClass.simpleName}\"")
                .build())
            .addType(TypeSpec.companionObjectBuilder()
                .addFunction(FunSpec.builder("uri")
                    .addParameter(ParameterSpec.builder("file", ClassName("", "G")).build())
                    .addStatement("return File(\"\$file\")")
                    .build())
                .build())
            .build())
        .apply {
          dirMap.forEachIndexed { index, pair ->
            addFunction(FunSpec.builder("div")
                .addAnnotation(AnnotationSpec.builder(JvmName::class).addMember("\"$index\"").build())
                .addModifiers(KModifier.OPERATOR)
                .addTypeVariable(TypeVariableName("S", TypeVariableName(pair.first)))
                .receiver(TypeVariableName("S"))
                .addParameter(ParameterSpec.builder("a", ClassName("", "${pair.second}.Companion"))
                    .addAnnotation(suppressAnnotation).build())
                .addStatement("return ${pair.second}<S>(\"\$this\")")
                .build())
          }
        }
        .build()
        .toString()

    File(path).mkdirs()
    return File("$path/G.kt").writeText("$header$Gkt")
  }

  override fun apply(project: Project) {
    val path = if (project.hasProperty("ath"))
      project.getProperties()["ath"].toString()
    else
      project.projectDir.absolutePath + "/src/main/kotlin/generated"

    project.run {
      tasks {
        register("yuriTask", Task::class) {
          generateProjectSources(path)
        }

//        withType<KotlinCompile> {
//          dependsOn("yuriTask")
//        }
      }
    }
  }
}