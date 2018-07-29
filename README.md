# yuri

A type-safe URI builder for Kotlin. Work in progress.

```
import Y.*

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
  Y.uri(localhost_)
  Y.uri(localhost_/bin)
  Y.uri(localhost_/bin/sh)
  Y.uri(localhost_/etc)
  Y.uri(localhost_/etc/vim)
  Y.uri(localhost_/usr)
  Y.uri(localhost_/usr/bin/vim)
  Y.uri(localhost_/usr/local)
  Y.uri(localhost_/usr/local/bin)

  //Does not compile!
  Y.uri(localhost_/local)
  Y.uri(localhost_/bin/vim)
  Y.uri(localhost_/sh)
  Y.uri(localhost_/bin/local)
  Y.uri(localhost_/etc/local)
  Y.uri(localhost_/etc/sh)
}
```


## run

`./gradlew compileKotlin`

## why?

- Because Strings are a pain and most filesystems can be scanned quickly

- To stress test Kotlin's type checker (more on this later)

- Real time automatic code generation is getting much better

- Abusing operator overloading is fun! Learn how a DSL works

- Might come in handy for scripting and build automation

- Static resource discovery, e.g. webpage crawling, IP address scanning
