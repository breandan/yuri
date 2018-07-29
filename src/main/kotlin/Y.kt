package main.kotlin

import main.kotlin.Y.*

abstract class Directory<T> {}
abstract class File<T> {}

open class Y<A> private constructor() {
  open class localhost_ {
    @JvmName("usr")
    operator fun div(a: usr.Companion) = usr<localhost_>()
    @JvmName("bin")
    operator fun div(a: bin.Companion) = bin<localhost_>()
    @JvmName("etc")
    operator fun div(a: etc.Companion) = etc<localhost_>()

    companion object: localhost_()
  }

  open class bin<T>: Directory<T>(), (bin<T>) -> bin<T> {
    override fun invoke(p1: bin<T>): bin<T> = this
    companion object
  }

  class sh<T>: File<T>(), (sh<T>) -> sh<T> {
    override fun invoke(p1: sh<T>): sh<T> = this
    companion object
  }

  open class etc<T>: Directory<T>(), (etc<T>) -> etc<T> {
    override fun invoke(p1: etc<T>): etc<T> = this
    companion object
  }

  class vim<T>: File<T>(), (vim<T>) -> vim<T> {
    override fun invoke(p1: vim<T>): vim<T> = this
    companion object
  }

  open class usr<T>: Directory<T>(), (usr<T>) -> usr<T> {
    override fun invoke(p1: usr<T>): usr<T> = this
    companion object
  }

  open class local<T>: Directory<T>(), (local<T>) -> local<T> {
    override fun invoke(p1: local<T>): local<T> = this
    companion object
  }

  companion object {
    fun uri(path: Any) = println()
  }
}

operator fun bin<localhost_>.div(a: sh.Companion) = sh<bin<localhost_>>()
operator fun bin<usr<localhost_>>.div(a: vim.Companion) = vim<bin<usr<localhost_>>>()
operator fun etc<localhost_>.div(a: vim.Companion) = vim<etc<localhost_>>()
operator fun usr<localhost_>.div(a: bin.Companion) = bin<usr<localhost_>>()
operator fun usr<localhost_>.div(a: local.Companion) = local<usr<localhost_>>()
operator fun local<usr<localhost_>>.div(a: bin.Companion) = bin<local<usr<localhost_>>>()

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
  Y.uri(localhost_/bin/sh)
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