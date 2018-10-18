import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.jetbrains.kotlin.psi.KtModifierListOwner
//import org.jetbrains.kotlin.renderer.KeywordStringsGenerated.KEYWORDS

plugins {
  idea apply true
  application
  `kotlin-dsl`
  //id("com.gradle.plugin-publish") version "0.10.0"
  `java-gradle-plugin`
  `maven-publish`
}

group = "co.ndan"
version = "0.1-SNAPSHOT"

buildscript {
  dependencies.classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.60")
  repositories.maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

repositories {
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  jcenter()
}

//application.mainClassName = "main/kotlin/HelloYuriKt"

dependencies {
  compile(kotlin("stdlib-jdk8"))
  compile(kotlin("compiler-embeddable"))
  compile("com.squareup:kotlinpoet:1.0.0-RC1")
}

//tasks.withType<KotlinCompile> {
//  kotlinOptions.jvmTarget = "1.8"
//}

// TODO: Figure out why this doesn't work
idea.module {
  generatedSourceDirs.add(File("src/gen"))
  sourceDirs.add(File("src"))
}

group = "co.ndan"
version = "0.1"

gradlePlugin.plugins.register("yuriPlugin") {
  id = "yuri-plugin"
  implementationClass = "main.kotlin.plugin.Yuri"
}

publishing {
  repositories {
    maven(url = "build/repository")
  }
}