plugins {
  application
  kotlin("jvm") version "1.5.0-M1"
  id("yuri-plugin")
}

repositories.mavenCentral()

dependencies {
  implementation(kotlin("stdlib"))
}

application.mainClass.set("samples.HelloYuriKt")