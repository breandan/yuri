plugins {
  application
  idea apply true
  id("yuri-plugin") version "0.1-SNAPSHOT" apply true
}

buildscript {
  repositories {
    jcenter()
  }
}

idea.module {
  generatedSourceDirs.add(File("src/gen"))
  sourceDirs.add(File("src"))
}

application.mainClassName = "main/kotlin/HelloYuriKt"