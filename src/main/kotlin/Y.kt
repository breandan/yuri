package main.kotlin

import main.kotlin.Y.*
import java.io.File

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
abstract class Y private constructor(private vararg val uri: Y) {
  @Yuri object localhost: Y() {
    @JvmName("root_usr") operator fun div(@Suppress(unused) a:usr.Companion) = usr<localhost>(path)
    @JvmName("root_bin") operator fun div(@Suppress(unused) a:bin.Companion) = bin<localhost>(path)
    @JvmName("root_etc") operator fun div(@Suppress(unused) a:etc.Companion) = etc<localhost>(path)

    @JvmName("any_usr") operator fun times(@Suppress(unused) a:usr.Companion) = usr<Y>(path)
    @JvmName("any_bin") operator fun times(@Suppress(unused) a:bin.Companion) = bin<Y>(path)
    @JvmName("any_etc") operator fun times(@Suppress(unused) a:etc.Companion) = etc<Y>(path)
    @JvmName("any_local") operator fun times(@Suppress(unused) a:local.Companion) = local<Y>(path)
    @JvmName("any_sh") operator fun times(@Suppress(unused) a:sh.Companion) = sh<Y>(path)
    @JvmName("any_vim") operator fun times(@Suppress(unused) a:vim.Companion) = vim<Y>(path)

    override val fileName: String get() = ""
    override fun toString() = "/"
  }

  @Yuri open class bin<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }
  @Yuri open class sh<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }
  @Yuri open class etc<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }
  @Yuri open class vim<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }
  @Yuri open class usr<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }
  @Yuri open class local<T>(uri: Array<Y>): Y(*uri) { @Yuri companion object }

  @Yuri companion object {
    private val allPaths = arrayOf(
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
    @Yuri fun uri(file: G) = File("$file")
    @Yuri fun uris(y: Y) = allPaths.filter { it.endsWith("$y") }.map { File(it) }
  }

  open val path: Array<Y> get() = arrayOf(*uri, this)
  open val fileName: String get() = javaClass.simpleName

  override fun toString() = path.joinToString("/") { it.fileName }
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


@JvmName("00") operator fun <S: bin<localhost>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>(path)
@JvmName("01") operator fun <S: bin<usr<localhost>>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>(path)
@JvmName("02") operator fun <S: etc<localhost>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>(path)
@JvmName("03") operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>(path)
@JvmName("04") operator fun <S: usr<localhost>> S.div(@Suppress(unused) a:local.Companion) = local<S>(path)
@JvmName("05") operator fun <S: local<usr<localhost>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>(path)
@JvmName("06") operator fun <S: bin<local<usr<localhost>>>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>(path)

@JvmName("07") operator fun <S: bin<Y>> S.div(@Suppress(unused) a:sh.Companion) = sh<S>(path)
@JvmName("08") operator fun <S: bin<Y>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>(path)
@JvmName("09") operator fun <S: etc<Y>> S.div(@Suppress(unused) a:vim.Companion) = vim<S>(path)
@JvmName("10") operator fun <S: usr<Y>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>(path)
@JvmName("11") operator fun <S: bin<usr<Y>>> S.div(@Suppress(unused) a:bin.Companion) = vim<S>(path)
@JvmName("12") operator fun <S: usr<Y>> S.div(@Suppress(unused) a:local.Companion) = local<S>(path)
@JvmName("13") operator fun <S: local<Y>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>(path)
@JvmName("14") operator fun <S: local<usr<Y>>> S.div(@Suppress(unused) a:bin.Companion) = bin<S>(path)
@JvmName("15") operator fun <S: bin<local<usr<Y>>>> S.div(@Suppress(unused) a:bin.Companion) = sh<S>(path)