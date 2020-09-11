plugins {
  application
  kotlin("jvm") version "1.4.10"
  id("yuri-plugin")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
}

application.mainClassName = "samples.HelloYuriKt"