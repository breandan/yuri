plugins {
  idea
  kotlin("jvm") version "1.4.0"
  application
  id("yuri-plugin") version "0.1-SNAPSHOT"
}

application.mainClassName = "samples.HelloYuriKt"

dependencies {
  implementation(kotlin("stdlib"))
}

repositories.jcenter()