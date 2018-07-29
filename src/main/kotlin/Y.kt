package main.kotlin

import main.kotlin.Y.*

abstract class Directory<T>
abstract class File<T>

open class Y private constructor() {
  open class localhost_ {
    @JvmName("usr")
    operator fun div(a: usr.Companion) = usr<localhost_>()
    @JvmName("bin")
    operator fun div(a: bin.Companion) = bin<localhost_>()
    @JvmName("etc")
    operator fun div(a: etc.Companion) = etc<localhost_>()

    companion object: localhost_()
  }

  open class bin<T>: Directory<T>() {
    companion object
  }

  class sh<T>: File<T>() {
    companion object
  }

  open class etc<T>: Directory<T>() {
    companion object
  }

  class vim<T>: File<T>() {
    companion object
  }

  open class usr<T>: Directory<T>() {
    companion object
  }

  open class local<T>: Directory<T>() {
    companion object
  }

  companion object {
    fun uri(path: Any) = println(path)
  }
}

operator fun bin<localhost_>.div(a: sh.Companion) = sh<bin<localhost_>>()
operator fun bin<usr<localhost_>>.div(a: vim.Companion) = vim<bin<usr<localhost_>>>()
operator fun etc<localhost_>.div(a: vim.Companion) = vim<etc<localhost_>>()
operator fun usr<localhost_>.div(a: bin.Companion) = bin<usr<localhost_>>()
operator fun usr<localhost_>.div(a: local.Companion) = local<usr<localhost_>>()
operator fun local<usr<localhost_>>.div(a: bin.Companion) = bin<local<usr<localhost_>>>()