package main.kotlin

import main.kotlin.Y.*
import java.io.File

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
abstract class Y private constructor(open val uri: String = "/") {
  @Yuri object localhost: Y("") {
    @JvmName("root_usr") operator fun div(@Suppress(unused) a:usr.Companion) = usr<localhost>(uri)
    @JvmName("root_bin") operator fun div(@Suppress(unused) a:bin.Companion) = bin<localhost>(uri)
    @JvmName("root_etc") operator fun div(@Suppress(unused) a:etc.Companion) = etc<localhost>(uri)

    @JvmName("any_usr") operator fun times(@Suppress(unused) a:usr.Companion) = usr<Y>(uri)
    @JvmName("any_bin") operator fun times(@Suppress(unused) a:bin.Companion) = bin<Y>(uri)
    @JvmName("any_etc") operator fun times(@Suppress(unused) a:etc.Companion) = etc<Y>(uri)
    @JvmName("any_local") operator fun times(@Suppress(unused) a:local.Companion) = local<Y>(uri)
    @JvmName("any_sh") operator fun times(@Suppress(unused) a:sh.Companion) = sh<Y>(uri)
    @JvmName("any_vim") operator fun times(@Suppress(unused) a:vim.Companion) = vim<Y>(uri)

    override fun toString() = uri
  }

  @Yuri open class bin<T>(uri: String): Y(uri) { @Yuri companion object: Y("bin") }
  @Yuri open class sh<T>(uri: String): Y(uri) { @Yuri companion object: Y("sh") }
  @Yuri open class etc<T>(uri: String): Y(uri) { @Yuri companion object: Y("etc") }
  @Yuri open class vim<T>(uri: String): Y(uri) { @Yuri companion object: Y("vim") }
  @Yuri open class usr<T>(uri: String): Y(uri) { @Yuri companion object: Y("sh") }
  @Yuri open class local<T>(uri: String): Y(uri) { @Yuri companion object: Y("local") }
  @Yuri companion object {
    private val allPaths = listOf<String>(
        "/bin",
        "/bin/sh",
        "/etc",
        "/etc/vim",
        "/usr",
        "/usr/bin",
        "/usr/bin/vim",
        "/usr/local",
        "/usr/local/bin",
        "/usr/local/bin/sh"
    )

    @Yuri fun uri(file: Y) = File("$file")
    @Yuri fun uris(y: Y) = allPaths.filter { it.endsWith("$y") }.map { File(it) }
  }

  fun filename() = javaClass.simpleName
  fun original() = javaClass.simpleName

  override fun toString() = "$uri/${javaClass.simpleName}"
}

/**
 * localhost
 * ├── bin
 * │   └── sh
 * ├── etc
 * │   └── vim
 * └── usr
 *     ├── bin
 *     │   └── vim
 *     └── local
 *         └── bin
 *             └── sh
 */

@JvmName("00") operator fun <S: bin<localhost>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>("$this")
@JvmName("01") operator fun <S: bin<usr<localhost>>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@JvmName("02") operator fun <S: etc<localhost>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@JvmName("03") operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@JvmName("04") operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:local.Companion) = local<S>("$this")
@JvmName("05") operator fun <S: local<usr<localhost>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@JvmName("06") operator fun <S: bin<local<usr<localhost>>>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>("$this")

@JvmName("07") operator fun <S: bin<Y>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>("$this")
@JvmName("08") operator fun <S: bin<Y>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@JvmName("09") operator fun <S: etc<Y>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>("$this")
@JvmName("10") operator fun <S: usr<Y>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@JvmName("11") operator fun <S: bin<usr<Y>>> S.div(@Suppress(unused) a:bin.Companion) = vim<S>("$this")
@JvmName("12") operator fun <S: usr<Y>> S.div(@Suppress(unused) a:local.Companion) = local<S>("$this")
@JvmName("13") operator fun <S: local<Y>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@JvmName("14") operator fun <S: local<usr<Y>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>("$this")
@JvmName("15") operator fun <S: bin<local<usr<Y>>>> S.div(@Suppress(unused) a:bin.Companion) = sh<S>("$this")