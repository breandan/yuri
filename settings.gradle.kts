include("plugin", "consumer")

pluginManagement.repositories {
  gradlePluginPortal()
  maven { url = uri("plugin/build/repository") }
}