plugins {
  `java-gradle-plugin`
  kotlin("jvm") version "1.7.10"
  id("com.gradle.plugin-publish") version "0.12.0"
}

repositories.mavenCentral()

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
  implementation("com.squareup:kotlinpoet:1.7.2")
}

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "co.ndan.yuri.Yuri"
}