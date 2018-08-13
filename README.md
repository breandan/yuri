# yuri

A type-safe URI builder for Kotlin. Work in progress.

```
import Y.*

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
      localhost *bin,
      localhost *sh,
      localhost *vim,
      localhost *bin/sh,
      localhost *etc/vim,
      localhost *usr/bin

      // Does not compile!
      // ,localhost *etc/bin
      // ,localhost *etc/sh
      // ,localhost *local/usr
  ).forEach { println("*$it -> ${Y.uris(it)}") }
}
```


## run

`./gradlew run`

## why?

- Because Strings are a pain and most filesystems can be scanned quickly

- To stress script Kotlin's type checker (more on this later)

- Real time automatic code generation is getting much better

- Abusing operator overloading is fun! Learn how a DSL works

- Might come in handy for scripting and build automation

- Static resource discovery, e.g. webpage crawling, IP address scanning

- Projects freqently need to reference project-local resources
