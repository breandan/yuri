package main.kotlin

import main.kotlin.Y.*
import main.kotlin.G.*

/**
 * localhost/
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
      Y.uri(localhost),
      Y.uri(localhost /bin),
      Y.uri(localhost /bin),
      Y.uri(localhost /bin/sh),
      Y.uri(localhost /etc),
      Y.uri(localhost /etc/vim),
      Y.uri(localhost /usr),
      Y.uri(localhost /usr/bin/vim),
      Y.uri(localhost /usr/local),
      Y.uri(localhost /usr/local/bin),

      //Does not compile!
//      Y.uri(localhost /local),
//      Y.uri(localhost /bin/vim),
//      Y.uri(localhost /sh),
//      Y.uri(localhost /bin/local),
//      Y.uri(localhost /etc/local),
//      Y.uri(localhost /etc/sh),
//      Y.uri(localhost /usr/local/sh),
//      G.uri(project /test),
//      G.uri(project /compileKotlin),
//      G.uri(project /kotlin),
//      G.uri(project /production/classes/main),

      G.uri(project /gradle),
      G.uri(project /gradlew),
      G.uri(project /settings_dot_gradle),
      G.uri(project /src/main),
      G.uri(project /src/main/kotlin)
  ).forEach { println("$it") }
}