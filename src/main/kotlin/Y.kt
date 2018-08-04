package main.kotlin

import main.kotlin.Y.*
import java.io.File

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
abstract class Y private constructor(val uri: String) {
  @Yuri object localhost_: Y("/") {
    @JvmName("usr") operator fun div(@Suppress(unused) a:usr.Companion) = usr<localhost_>("")
    @JvmName("bin") operator fun div(@Suppress(unused) a:bin.Companion) = bin<localhost_>("")
    @JvmName("etc") operator fun div(@Suppress(unused) a:etc.Companion) = etc<localhost_>("")
    override fun toString() = uri
  }

  @Yuri open class bin<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class sh<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class etc<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class vim<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class usr<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri open class local<T>(uri: String): Y(uri) { @Yuri companion object }
  @Yuri companion object { @Yuri fun uri(y: Y) = File("$y") }

  override fun toString() = "$uri/${javaClass.simpleName}"
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

@Yuri operator fun <S: bin<localhost_>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>("$this")
@Yuri operator fun <S: bin<usr<localhost_>>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@Yuri operator fun <S: etc<localhost_>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@Yuri operator fun <S: usr<localhost_>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@Yuri operator fun <S: usr<localhost_>> S.div(@Suppress(unused) a:local.Companion) = local<S>("$this")
@Yuri operator fun <S: local<usr<localhost_>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")