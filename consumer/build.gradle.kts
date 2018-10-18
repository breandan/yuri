plugins {
  application
  id("yuri-plugin") version "0.1" apply true
}

buildscript {
  repositories {
    jcenter()
  }
}

application.mainClassName = "main/kotlin/HelloYuriKt"