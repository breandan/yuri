import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.serialization.js.DynamicTypeDeserializer.id

plugins {
  idea apply true
  `kotlin-dsl`
  id("com.gradle.plugin-publish") version "0.10.0"
  `java-gradle-plugin`
  `maven-publish`
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

buildscript {
  dependencies.classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.0-rc-190")
//  repositories {
//    jcenter()
//    mavenCentral()
//  }
}

repositories {
  jcenter()
}

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
  compile(kotlin("stdlib-jdk8"))
  compile(kotlin("compiler-embeddable"))
  compile("com.squareup:kotlinpoet:1.0.0-RC1")
}

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "co.ndan.yuri.Yuri"
}

publishing.repositories.maven(url = "build/repository")
