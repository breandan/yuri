import com.squareup.kotlinpoet.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.renderer.KeywordStringsGenerated.KEYWORDS

plugins {
  kotlin("jvm") version "1.2.60"
  idea apply true
  application
  id("com.gradle.plugin-publish") version "0.10.0"
  `java-gradle-plugin`
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

buildscript {
  dependencies {
    classpath("com.squareup:kotlinpoet:1.0.0-RC1")
  }
}

repositories {
  mavenCentral()
}

application {
  mainClassName = "main/kotlin/HelloYuriKt"
}

dependencies {
  compile(kotlin("stdlib-jdk8"))
}

val genSources by tasks.creating(Task::class.java) {
  generateProjectSources()
}

tasks.withType<KotlinCompile> {
  dependsOn(genSources)
  kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
  (plugins) {
    "yuri" {
      id = "co.ndan.yuri"
      implementationClass = "co.ndan.yuri.Yuri"
    }
  }
}

pluginBundle {
  website = "http://www.gradle.org/"
  vcsUrl = "https://github.com/breandan/yuri"

  description = "A type-safe URI builder for Kotlin."

  (plugins) {
    "yuri" {
      displayName = "Yuri"
      tags = listOf("uri", "type-safe", "codegen", "kotlin")
      version = "0.1"
    }
  }

  mavenCoordinates {
    groupId = "co.ndan"
    artifactId = "yuri"
    version = "0.1"
  }
}

// TODO: Figure out why this doesn't work
idea {
  module {
    generatedSourceDirs.add(File("build/generated/source/yuri/main"))
    sourceDirs.add(File("build/generated/source/yuri/main"))
  }
}

fun generateProjectSources() {
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
        val key = elements.drop(1).joinToString("<") + "<project>" + ">".repeat(elements.size - 2)

        Pair(key, value)
      }

  val header = "package main.kotlin\nimport main.kotlin.G.*\n"

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
          .addType(TypeSpec.objectBuilder("project")
              .superclass(ClassName("", "G"))
              .addSuperclassConstructorParameter("\"${project.projectDir.absolutePath}\"")
              .addFunctions(topLevelFileNames.map {
                FunSpec.builder("div")
                    .addModifiers(KModifier.OPERATOR)
                    .addAnnotation(AnnotationSpec.builder(JvmName::class).addMember("\"$it\"").build())
                    .addParameter(ParameterSpec.builder("a", ClassName("", "$it.Companion"))
                        .addAnnotation(suppressAnnotation).build())
                    .addStatement("return $it<project>(uri)")
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

  File("src/main/kotlin/G.kt").writeText("$header$Gkt")
}