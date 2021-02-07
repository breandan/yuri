plugins {
  application
  kotlin("jvm") version "1.4.30"
  id("yuri-plugin")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
}

application.mainClass.set("samples.HelloYuriKt")
