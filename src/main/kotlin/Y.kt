package main.kotlin

import main.kotlin.Y.*
import java.io.File

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
abstract class Y private constructor(val uri: String = "/") {
  @Yuri object localhost: Y("/") {
    @JvmName("usr") operator fun div(@Suppress(unused) a:usr.Companion) = usr<localhost>(uri)
    @JvmName("bin") operator fun div(@Suppress(unused) a:bin.Companion) = bin<localhost>(uri)
    @JvmName("etc") operator fun div(@Suppress(unused) a:etc.Companion) = etc<localhost>(uri)
    override fun toString() = uri
  }

  @Yuri open class bin<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class sh<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class etc<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class vim<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class usr<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class local<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri companion object { @Yuri fun uri(file: Y) = File("$file") }

  override fun toString() = "$uri/${javaClass.simpleName}"
}

/**
 * localhost/
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

@Yuri operator fun <S: bin<localhost>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>("$this")
@Yuri operator fun <S: bin<usr<localhost>>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@Yuri operator fun <S: etc<localhost>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@Yuri operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@Yuri operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:local.Companion) = local<S>("$this")
@Yuri operator fun <S: local<usr<localhost>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")