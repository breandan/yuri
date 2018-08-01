package main.kotlin

import main.kotlin.Y.*

@DslMarker
annotation class Yuri

@Yuri
abstract class Y private constructor(path: String) {
  @Yuri open class localhost_ {
    @JvmName("usr") operator fun div(a: usr.Companion) = usr<localhost_>("/")
    @JvmName("bin") operator fun div(a: bin.Companion) = bin<localhost_>("/")
    @JvmName("etc") operator fun div(a: etc.Companion) = etc<localhost_>("/")

    @Yuri companion object: localhost_()
  }

  @Yuri open class bin<T>(path: String): Y(path) { @Yuri companion object }
  @Yuri open class sh<T>(path: String): Y(path) { @Yuri companion object }
  @Yuri open class etc<T>(path: String) : Y(path) { @Yuri companion object }
  @Yuri open class vim<T>(path: String): Y(path) { @Yuri companion object }
  @Yuri open class usr<T>(path: String): Y(path) { @Yuri companion object }
  @Yuri open class local<T>(path: String): Y(path) { @Yuri companion object }
  @Yuri companion object { @Yuri fun uri(path: Any) = println(path) }

  internal open val path: String = "$path/${javaClass.simpleName}"
  override fun toString() = path
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

@Yuri operator fun <S: bin<localhost_>> S.div(a: sh.Companion) = sh<S>(path)
@Yuri operator fun <S: bin<usr<localhost_>>> S.div(a: vim.Companion) = vim<S>(path)
@Yuri operator fun <S: etc<localhost_>> S.div(a: vim.Companion) = vim<S>(path)
@Yuri operator fun <S: usr<localhost_>> S.div(a: bin.Companion) = bin<S>(path)
@Yuri operator fun <S: usr<localhost_>> S.div(a: local.Companion) = local<S>(path)
@Yuri operator fun <S: local<usr<localhost_>>> S.div(a: bin.Companion) = bin<S>(path)