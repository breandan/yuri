plugins {
  idea apply true
  `kotlin-dsl`
  `maven-publish`
  id("com.gradle.plugin-publish") version "0.11.0"
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

repositories.jcenter()

pluginBundle {
  website = "https://github.com/breandan/yuri"
  vcsUrl = "https://github.com/breandan/yuri"
  description = "A type-safe URI builder for Kotlin."
  tags = listOf("yuri", "uri", "type-safe", "codegen", "kotlin")

  mavenCoordinates {
    groupId = project.group.toString()
    artifactId = project.name
  }
}

dependencies {
  compile(kotlin("compiler-embeddable"))
  compile("com.squareup:kotlinpoet:1.5.0")
}

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "co.ndan.yuri.Yuri"
}

publishing.repositories.maven(url = "build/repository")