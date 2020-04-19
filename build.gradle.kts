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