plugins {
  application
  kotlin("jvm") version "1.3.72"
  id("yuri-plugin") version "0.1-SNAPSHOT"
}

application.mainClassName = "samples.HelloYuriKt"

dependencies {
  compile(kotlin("stdlib"))
}

repositories.jcenter()