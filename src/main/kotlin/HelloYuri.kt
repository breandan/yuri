package main.kotlin

import main.kotlin.Y.*
import main.kotlin.G.*
import java.io.File

/**
 * localhost_/
 * ├── bin/
 * │   └── sh
 * ├── etc/
 * │   └── vim
 * └── usr/
 *     ├── bin/
 *     │   └── vim
 *     └── local/
 *         └── bin/
 */

fun main(a: Array<String>) {
  //Compiles!
  listOf(
      Y.uri(localhost_),
      Y.uri(localhost_/bin),
      Y.uri(localhost_/bin/sh),
      Y.uri(localhost_/etc),
      Y.uri(localhost_/etc/vim),
      Y.uri(localhost_/usr),
      Y.uri(localhost_/usr/bin/vim),
      Y.uri(localhost_/usr/local),
      Y.uri(localhost_/usr/local/bin),

      //Does not compile!
//      Y.uri(localhost_/local),
//      Y.uri(localhost_/bin/vim),
//      Y.uri(localhost_/sh),
//      Y.uri(localhost_/bin/local),
//      Y.uri(localhost_/etc/local),
//      Y.uri(localhost_/etc/sh),
//      Y.uri(localhost_/usr/local/usr),
//      G.uri(project_/test),
//      G.uri(project_/compileKotlin),
//      G.uri(project_/kotlin),
//      G.uri(project_/production/classes/main),

      G.uri(project_/gradle),
      G.uri(project_/gradlew),
      G.uri(project_/settings_dot_gradle),
      G.uri(project_/src/main),
      G.uri(project_/src/main/kotlin)
  ).forEach { println("$it") }
}
