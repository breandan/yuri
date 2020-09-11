plugins {
  `java-gradle-plugin`
  kotlin("jvm") version "1.4.10"
  id("com.gradle.plugin-publish") version "0.12.0"
}

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
  implementation("com.squareup:kotlinpoet:1.6.0")
}

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "co.ndan.yuri.Yuri"
}