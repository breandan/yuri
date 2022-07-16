plugins {
  application
  kotlin("jvm") version "1.7.10"
  id("yuri-plugin")
}

repositories.mavenCentral()

dependencies {
  implementation(kotlin("stdlib"))
}

application.mainClass.set("samples.HelloYuriKt")