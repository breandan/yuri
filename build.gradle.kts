import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.renderer.KeywordStringsGenerated.KEYWORDS

plugins {
  kotlin("jvm") version "1.2.51"
  idea apply true
  application
  id("com.gradle.plugin-publish") version "0.10.0"
  `java-gradle-plugin`
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

application {
  mainClassName = "main/kotlin/HelloYuriKt"
}

dependencies {
  compile(kotlin("stdlib-jdk8"))
  //TODO: Add project proper source code generation
//    compile("com.squareup:kotlinpoet:1.0.0-RC1")
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
      .filter { !it.path.toCharArray().any { it.isDigit() } }
  val topLevelFiles = walker
      .maxDepth(1)
      .filter { !it.path.contains(".git") }
  val topLevelFileNames = topLevelFiles
      .map { it.name.replace(".", "_dot_").replace("$", "_dollar_")}
  val allFileNames = allFiles
      .map {
        it.name.replace(".", "_dot_")
            .replace("-", "_dash_")
            .replace("$", "_dollar_")
      }
      .map { if (it in (KEYWORDS + "out")) "`$it`" else it }
      .toSet()

  val secondLevelFiles = allFiles.filter { it !in topLevelFiles }

  val header = """// This file was generated by Yuri, a type-safe URI builder for Kotlin.
    package main.kotlin
    import main.kotlin.G.*
    import java.io.File

    abstract class G private constructor(path: String) {
    open class project_ private constructor(val prefix: String): G(prefix) {""".trimIndent()

  var generatedFile = header
  topLevelFileNames.forEach {
    generatedFile += "\n@JvmName(\"$it\") operator fun div(a: $it.Companion) = $it<project_>(prefix)\n"
  }

  generatedFile += """
      companion object: project_(File("").absolutePath) {
        override val path = prefix
      }
    }
  """.trimIndent()

  allFileNames.forEach {
    generatedFile += "\nopen class $it<T>(path: String): G(path) { companion object }\n"
  }

  generatedFile += "internal open val path: String = \"\$path/\${javaClass.simpleName}\"\n  override fun toString() = path\n  companion object {\n    fun uri(path: Any) = println(path)\n}\n}"
  val dirMap = secondLevelFiles
      .map {
        val elements = it.path
            .replace("-", "_dash_")
            .substring(2)
            .replace(".", "_dot_")
            .replace("$", "_dollar_")
            .split("/")
            .map { if (it in (KEYWORDS + "out")) "`$it`" else it }
            .asReversed()


        val value = elements.first()
        val key = elements.drop(1).joinToString("<") + "<project_>" + ">".repeat(elements.size - 2)

        Pair(key, value)
      }

  var randomString  = 1
  dirMap.forEach {
    randomString++
    generatedFile += "@JvmName(\"$randomString\") operator fun <S: ${it.first}> S.div(a: ${it.second}.Companion) = ${it.second}<S>(path)\n"
  }

  File("src/main/kotlin/G.kt").apply { this.createNewFile() }.writeText(generatedFile)

  File("build/generated/source/yuri/main/").apply { mkdirs() }
  File("build/generated/source/yuri/main/G.kt").apply { createNewFile() }.writeText(generatedFile)
}

//@JvmName("1")
//fun generateRandomString(): String {
//  val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
//  var randomString = ""
//  for (i in 0..10) {
//    randomString += chars[Math.floor(Math.random() * chars.length).toInt()]
//  }
//  return randomString
//}
