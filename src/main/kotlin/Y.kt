package main.kotlin

import main.kotlin.Y.*

abstract class Directory<T> {}
abstract class File<T> {}

open class Y<A> private constructor() {
  open class localhost_: Directory<Nothing>() {
    @JvmName("usr")
    operator fun div(a: usr<localhost_>.() -> usr.Companion) = usr
    @JvmName("bin")
    operator fun div(a: bin<localhost_>.() -> bin.Companion) = bin
    @JvmName("etc")
    operator fun div(a: etc<localhost_>.() -> etc.Companion) = etc

    companion object: localhost_() {}
  }

  open class bin<T>: Directory<T>() {
    companion object: (bin<*>) -> Companion {
      override fun invoke(a: bin<*>) = this
      @JvmName("localhost_")
      operator fun div(a: sh<bin<localhost_>>.() -> sh.Companion) = sh
      @JvmName("usr_localhost_")
      operator fun div(a: vim<bin<usr<localhost_>>>.() -> vim.Companion) = vim
    }
  }

  class sh<T>: File<T>() {
    companion object: (sh<bin<localhost_>>) -> Companion {
      override fun invoke(a: sh<bin<localhost_>>) = this
    }
  }

  open class etc<T>: Directory<T>() {
    companion object: (etc<*>) -> Companion {
      override fun invoke(a: etc<*>) = this
      operator fun div(a: vim<etc<localhost_>>.() -> vim.Companion) = vim
    }
  }

  class vim<T>: File<T>() {
    companion object: (vim<*>) -> Companion {
      override fun invoke(a: vim<*>) = this
    }
  }

  open class usr<T>: Directory<T>() {
    companion object: (usr<*>) -> Companion {
      override fun invoke(a: usr<*>) = this
      operator fun div(a: local<usr<localhost_>>.() -> local.Companion) = local
      operator fun div(a: bin<usr<localhost_>>.() -> bin.Companion) = bin
    }
  }

  open class local<T>: Directory<T>() {
    companion object: (local<*>) -> Companion {
      override fun invoke(a: local<*>) = this
      operator fun div(a: bin<local<usr<localhost_>>>.() -> bin.Companion) = bin
    }
  }

  companion object {
    fun uri(path: Any) {
      TODO()
    }
  }
}

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
  Y.uri(localhost_/sh)
  Y.uri(localhost_/bin/local)
  Y.uri(localhost_/etc/local)
  Y.uri(localhost_/etc/sh)
}