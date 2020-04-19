plugins {
  idea
  `kotlin-dsl`
  `maven-publish`
  kotlin("jvm") version "1.3.72"
  id("com.gradle.plugin-publish") version "0.11.0"
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

repositories.jcenter()

pluginBundle {
  website = "https://github.com/breandan/yuri"
  vcsUrl = "https://github.com/breandan/yuri"
  description = "A type-safe URI builder for Kotlin."
  tags = listOf("uri", "types", "codegen", "kotlin")

  mavenCoordinates {
    groupId = project.group.toString()
    artifactId = project.name
  }
}

dependencies {
  implementation(kotlin("compiler-embeddable"))
  implementation("com.squareup:kotlinpoet:1.5.0")
}

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "co.ndan.yuri.Yuri"
}

publishing.repositories.maven(url = "build/repository")