package co.ndan.yuri

import com.squareup.kotlinpoet.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.renderer.KeywordStringsGenerated.KEYWORDS
import java.io.File

open class Yuri : Plugin<Project> {
  val specialMap = listOf("." to "_dot_", "-" to "_dash_", "$" to "_dollar_")
  fun String.escaped() =
    specialMap.fold(this) { acc, pair -> acc.replace(pair.first, pair.second) }

  fun generateProjectSources(path: String) {
    val walker = File(path).walkTopDown()
    val allFiles = walker.filter(canIgnore())
    val topLevelFiles = walker.maxDepth(1).filter(canIgnore())
    val topLevelFileNames = topLevelFiles.map { it.name.escaped() }
    val allFileNames = allFiles
      .map { it.name.escaped() }
      .map { if (it in (KEYWORDS + "out")) "`$it`" else it }
      .toSet()

    val dirMap = allFiles.filter { it !in topLevelFiles }
      .map { file ->
        val elements = file.relativeTo(File(path)).path
          .escaped()
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
    return File("$path/src/main/kotlin/generated/G.kt").writeText("$header$Gkt")
  }

  private fun canIgnore() = { it: File ->
    !(it.endsWith(".") ||
      "git" in it.path ||
      "idea" in it.path ||
      "gradle" in it.path ||
      "build" in it.path ||
      it.path.any(Char::isDigit))
  }

  override fun apply(project: Project) {
    val path = if (project.hasProperty("ath"))
      project.getProperties()["ath"].toString()
    else project.projectDir.absolutePath

    project.run {
      tasks.register("genSources", Task::class) {
        generateProjectSources(path)
      }
    }
  }
}
