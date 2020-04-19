plugins {
  idea
  application
  kotlin("jvm")
  id("yuri-plugin") version "0.1-SNAPSHOT"
}

application.mainClassName = "samples.HelloYuriKt"

dependencies {
  compile(kotlin("stdlib"))
}

repositories.jcenter()