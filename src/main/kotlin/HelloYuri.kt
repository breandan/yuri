package main.kotlin

import main.kotlin.Y.*
import main.kotlin.G.*

fun main(a: Array<String>) {
  println(""" MOCK FILE TREE:

  localhost
  ├── bin
  │   ├── sh
  │   └── sh.distrib
  |       └── sh
  ├── etc
  │   ├── vim
  |   └── script.sh
  └── usr
      ├── bin
      │   └── vim
      └── local
          └── bin
              └── sh
  """)

  println("YURI-VALIDATED URIs:")

  listOf(
      // Compiles!
      Y.uri(localhost),
      Y.uri(localhost /bin),
      Y.uri(localhost /bin/sh),
      Y.uri(localhost /bin/sh.distrib),
      Y.uri(localhost /bin/sh.distrib/sh),
      Y.uri(localhost /etc),
      Y.uri(localhost /etc/vim),
      Y.uri(localhost /etc/script.sh),
      Y.uri(localhost /usr),
      Y.uri(localhost /usr/bin/vim),
      Y.uri(localhost /usr/local),
      Y.uri(localhost /usr/local/bin),
      Y.uri(localhost /usr/local/bin/sh),

      Y.uri(project),
      Y.uri(project /gradle),
      Y.uri(project /gradlew),
      Y.uri(project /settings_dot_gradle),
      Y.uri(project /src/main),
      Y.uri(project /src/main/kotlin)

      // Does not compile!
      // ,Y.uri(localhost /local)
      // ,Y.uri(localhost /bin/vim)
      // ,Y.uri(localhost /sh)
      // ,Y.uri(localhost /bin/local)
      // ,Y.uri(localhost /etc/local)
      // ,Y.uri(localhost /etc/sh)
      // ,Y.uri(localhost /etc/script)
      // ,Y.uri(localhost /usr/local/sh)
      // ,Y.uri(localhost /usr/local/bin/sh.distrib)
      // ,Y.uri(project /test)
      // ,Y.uri(project /compileKotlin)
      // ,Y.uri(project /kotlin)
      // ,Y.uri(project /production/classes/main)
  ).forEach { println("$it") }

  println("\nKLEENE STAR SEARCH:\n")

  listOf(
      // Compiles!
      *bin,
      *sh,
      *vim,
      *bin/sh,
      *etc/vim,
      *usr/bin

      // Does not compile!
      // , *etc/bin
      // , *etc/sh
      // , *local/usr
  ).forEach { path -> println("*$path -> ${Y.uris(path)}") }

  println("\nBATCH OPERATIONS:\n")

  Y.uris(*sh) { println(it) }
}