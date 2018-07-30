package main.kotlin

import main.kotlin.Y.*

@DslMarker
annotation class Yuri

abstract class File<T>

@Yuri
open class Y private constructor() {
  @Yuri open class localhost_ {
    @JvmName("usr") operator fun div(a: usr.Companion) = usr<localhost_>()
    @JvmName("bin") operator fun div(a: bin.Companion) = bin<localhost_>()
    @JvmName("etc") operator fun div(a: etc.Companion) = etc<localhost_>()

    @Yuri companion object: localhost_()
  }

  @Yuri open class bin<T>: File<T>() { @Yuri companion object }
  @Yuri open class sh<T>: File<T>() { @Yuri companion object }
  @Yuri open class etc<T>: File<T>() { @Yuri companion object }
  @Yuri open class vim<T>: File<T>() { @Yuri companion object }
  @Yuri open class usr<T>: File<T>() { @Yuri companion object }
  @Yuri open class local<T>: File<T>() { @Yuri companion object }

  @Yuri companion object { @Yuri fun uri(path: Any) = println(path) }
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

@Yuri operator fun bin<localhost_>.div(a: sh.Companion) = sh<bin<localhost_>>()
@Yuri operator fun bin<usr<localhost_>>.div(a: vim.Companion) = vim<bin<usr<localhost_>>>()
@Yuri operator fun etc<localhost_>.div(a: vim.Companion) = vim<etc<localhost_>>()
@Yuri operator fun usr<localhost_>.div(a: bin.Companion) = bin<usr<localhost_>>()
@Yuri operator fun usr<localhost_>.div(a: local.Companion) = local<usr<localhost_>>()
@Yuri operator fun local<usr<localhost_>>.div(a: bin.Companion) = bin<local<usr<localhost_>>>()