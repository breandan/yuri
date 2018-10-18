tasks {
  val plugin by registering(GradleBuild::class) {
    dir = file("plugin")
    tasks = listOf("publish")
  }

  val consumer by registering(GradleBuild::class) {
    dir = file("consumer")
    tasks = listOf("yuriTask")
  }

  consumer {
    dependsOn(plugin)
  }
}