# Yuri

A type-safe URI builder for Kotlin. Work in progress.

## tree

All usage examples are based on the following hypothetical file tree:

```
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
```
## import

To work properly, Yuri needs a wildcard import:

```kotlin
import Y.*
```

## usage

Yuri provides compile-time URI validation with a [URI-like](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) syntax.

```kotlin
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
```

## search

You can perform Kleene-star prefix searches using Kotlin's [spread-operator](https://kotlinlang.org/docs/reference/functions.html#variable-number-of-arguments-varargs):

```kotlin
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
```

## batch

Yuri provides an [extension function](https://kotlinlang.org/docs/reference/extensions.html#extension-functions) for batch ops over multiple files:

```kotlin
Y.uris(*sh) { println(it) }
Y.uris(*sh) { it.createNewFile() }
Y.uris(*sh) { it.appendText("Hello Yuri") }
Y.uris(*sh) { it.setReadOnly() }
```

## examples

To reproduce the example code above, run the following command from the project directory:

```
./gradlew consumer
```

## generate

To generate the file tree, run the following command from the project directory:

```
./gradlew genSources [-Path=<absolute path to perform file tree scan>]
```

Unless `-Path` is specified, Yuri will scan the project directory. Code generation is provided by [Kotlin Poet](https://github.com/square/kotlinpoet).

## how?

See:

* [Y.kt](consumer/src/main/kotlin/samples/Y.kt), for DSL and type checking
* [build.gradle.kts](build.gradle.kts), for code generation

## why?

- Because Strings are error prone ([`FileNotFoundException`](https://docs.oracle.com/javase/7/docs/api/java/io/FileNotFoundException.html), [`NoSuchFileException`](https://docs.oracle.com/javase/7/docs/api/java/nio/file/NoSuchFileException.html)...)

- Many filesystems can be scanned quickly and do not change

- To stress test Kotlin's type checker

- Real time automatic code generation is getting better

- Abusing operator overloading is fun! Learn how a DSL works

- Might come in handy for scripting and build automation

- Static resource discovery, e.g. webpage crawling, IP address scanning

- Projects freqently need to reference absolute or project-local resources
