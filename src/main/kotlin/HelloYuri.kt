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

      Y.uri(projectDir),
      Y.uri(projectDir /gradle),
      Y.uri(projectDir /gradlew),
      Y.uri(projectDir /settings_dot_gradle),
      Y.uri(projectDir /src/main),
      Y.uri(projectDir /src/main/kotlin)

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
      // ,Y.uri(projectDir /test)
      // ,Y.uri(projectDir /compileKotlin)
      // ,Y.uri(projectDir /kotlin)
      // ,Y.uri(projectDir /production/classes/main)
  ).forEach { println("$it") }

  println("\nKLEENE STAR SEARCH:\n")

  listOf(
      // Compiles!
      *bin,
      *sh,
      *vim,
      *bin/sh,
      *bin/sh.distrib,
      *etc/vim,
      *usr/bin,
      *sh.distrib,
      *sh.distrib/sh,
      *script.sh,
      *etc/script.sh

      // Does not compile!
      // , *etc/bin
      // , *etc/sh
      // , *local/usr
      // , *sh/distrib
      // , *sh/sh
  ).forEach { path -> println("*$path -> ${Y.uris(path)}") }

  println("\nBATCH OPERATIONS:\n")

  Y.uris(*sh) { println(it) }
}