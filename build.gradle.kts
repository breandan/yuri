plugins {
  kotlin("jvm") version "1.4.0"
}

allprojects {
  group = "co.ndan"
  version = "0.1-SNAPSHOT"

  repositories.jcenter()
}

tasks {
  val plugin by creating(GradleBuild::class) {
    dir = file("plugin")
    tasks = listOf("publish")
  }

  val consumer by creating(GradleBuild::class) {
    dependsOn(plugin)
    dir = file("consumer")
    tasks = listOf("run")
  }
}