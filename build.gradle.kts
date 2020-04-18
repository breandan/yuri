plugins {
  idea
  kotlin("jvm") version "1.3.72"
}

tasks {
  val plugin by registering(GradleBuild::class) {
    dir = file("plugin")
    tasks = listOf("publish")
  }

  val consumer by registering(GradleBuild::class) {
    dir = file("consumer")
    tasks = listOf("run")
  }

  consumer {
    dependsOn(plugin)
  }
}