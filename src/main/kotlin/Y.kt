package main.kotlin

import main.kotlin.Y.*
import java.io.File

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
sealed class Y constructor(private vararg val uri: Y): File(uri.toString()) {
  @Yuri object localhost: Y() {
    @JvmName("root_usr") operator fun div(@Suppress(unused) a: Array<usr<Y>>) = arrayOf(usr<localhost>(path))
    @JvmName("root_bin") operator fun div(@Suppress(unused) a: Array<bin<Y>>) = arrayOf(bin<localhost>(path))
    @JvmName("root_etc") operator fun div(@Suppress(unused) a: Array<etc<Y>>) = arrayOf(etc<localhost>(path))

    @JvmName("any_usr") operator fun times(@Suppress(unused) a: Array<usr<Y>>) = arrayOf(usr<Y>(path))
    @JvmName("any_bin") operator fun times(@Suppress(unused) a: Array<bin<Y>>) = arrayOf(bin<Y>(path))
    @JvmName("any_etc") operator fun times(@Suppress(unused) a: Array<etc<Y>>) = arrayOf(etc<Y>(path))
    @JvmName("any_local") operator fun times(@Suppress(unused) a: Array<local<Y>>) = arrayOf(local<Y>(path))
    @JvmName("any_sh") operator fun times(@Suppress(unused) a: Array<sh<Y>>) = arrayOf(sh<Y>(path))
    @JvmName("any_vim") operator fun times(@Suppress(unused) a: Array<vim<Y>>) = arrayOf(vim<Y>(path))

    override val fileName: String get() = ""
    override fun toString() = "/"
  }

  @Yuri open class bin<T>(uri: Array<Y>): Y(*uri)
  @Yuri open class script<T>(uri: Array<Y>): Y(*uri)
  @Yuri open class etc<T>(uri: Array<Y>): Y(*uri)
  @Yuri open class vim<T>(uri: Array<Y>): Y(*uri)
  @Yuri open class usr<T>(uri: Array<Y>): Y(*uri)
  @Yuri open class local<T>(uri: Array<Y>): Y(*uri)

  @Yuri
  class sh<U>(uri: Array<Y>): Y(*uri) {
    override val fileName: String get() = this.javaClass.name.split("\$").drop(1).joinToString(".")
  }

  @Yuri
  class distrib<U>(uri: Array<Y>): Y(*uri) {
    override val fileName: String get() = this.javaClass.name.split("\$").drop(1).joinToString(".")
  }

  @Yuri companion object {
    private val allPaths = arrayOf(
        "/bin",
        "/bin/sh",
        "/bin/sh.distrib",
        "/bin/sh.distrib/sh",
        "/etc",
        "/etc/vim",
        "/etc/script.sh",
        "/usr",
        "/usr/bin",
        "/usr/bin/vim",
        "/usr/local",
        "/usr/local/bin",
        "/usr/local/bin/sh"
    )

    @Yuri fun uri(y: Array<out Y>): File = File("${y.first()}")
    @Yuri fun uri(g: G) = File("$g")
    @Yuri fun uris(y: Y) = allPaths.filter { it.endsWith("$y") }.map { File(it) }
    @Yuri fun uri(file: Y, function: Y.() -> Unit = {}) = file.apply(function)
  }

  open val path: Array<Y> get() = arrayOf(*uri, this)
  open val fileName: String get() = this.javaClass.simpleName
  override fun toString() = path.joinToString("/") { it.fileName }
}

val usr: Array<usr<Y>> = arrayOf(usr(arrayOf()))
val etc: Array<etc<Y>> = arrayOf(etc(arrayOf()))
val bin: Array<bin<Y>> = arrayOf(bin(arrayOf()))
val vim: Array<vim<Y>> = arrayOf(vim(arrayOf()))
val sh: Array<sh<Y>> = arrayOf(sh(arrayOf()))
val script: Array<script<Y>> = arrayOf(script(arrayOf()))
val local: Array<local<Y>> = arrayOf(local(arrayOf()))

/**
 * localhost
 * ├── bin
 * │   ├── sh
 * │   └── sh.distrib
 * |       └── sh
 * ├── etc
 * │   ├── vim
 * |   └── script.sh
 * └── usr
 *     ├── bin
 *     │   └── vim
 *     └── local
 *         └── bin
 *             └── sh
 */

@JvmName("00") operator fun <S: bin<localhost>> Array<S>.div(@Suppress(unused) a: Array<sh<Y>>) = arrayOf(sh<S>(path))
@JvmName("01") operator fun <S: bin<usr<localhost>>> Array<S>.div(@Suppress(unused) a: Array<vim<Y>>) = arrayOf(vim<S>(path))
@JvmName("02") operator fun <S: etc<localhost>> Array<S>.div(@Suppress(unused) a: Array<vim<Y>>) = arrayOf(vim<S>(path))
@JvmName("03") operator fun <S: usr<localhost>> Array<S>.div(@Suppress(unused) a: Array<bin<Y>>) = arrayOf(bin<S>(path))
@JvmName("04") operator fun <S: usr<localhost>> Array<S>.div(@Suppress(unused) a: Array<local<Y>>) = arrayOf(local<S>(path))
@JvmName("05") operator fun <S: local<usr<localhost>>> Array<S>.div(@Suppress(unused) a: Array<bin<Y>>) = arrayOf(bin<S>(path))
@JvmName("06") operator fun <S: bin<local<usr<localhost>>>> Array<S>.div(@Suppress(unused) a: Array<sh<Y>>) = arrayOf(sh<S>(path))

// File extension dot operator notation
@JvmName("00") operator fun <S: bin<localhost>> Array<S>.div(@Suppress(unused) a: Array<distrib<sh<Y>>>) = arrayOf(distrib<sh<S>>(path))
@JvmName("00") operator fun <S: distrib<sh<bin<localhost>>>> Array<S>.div(@Suppress(unused) a: Array<sh<Y>>) = arrayOf(sh<S>(path))
@JvmName("00") operator fun <S: etc<localhost>> Array<S>.div(@Suppress(unused) a: Array<sh<script<Y>>>) = arrayOf(sh<script<S>>(path))

val <T: sh<Y>> Array<T>.distrib: Array<distrib<T>>
  get() = arrayOf(distrib(path))

val <T: script<Y>> Array<T>.sh: Array<sh<T>>
  get() = arrayOf(sh(path))

private val <T: Y> Array<T>.path: Array<Y>
  get() = this[0].path

@JvmName("07") operator fun <S: bin<Y>> Array<S>.div(@Suppress(unused) a:Array<sh<Y>>) = arrayOf(sh<S>(path))
@JvmName("08") operator fun <S: bin<Y>> Array<S>.div(@Suppress(unused) a:Array<vim<Y>>) = arrayOf(vim<S>(path))
@JvmName("09") operator fun <S: bin<Y>> Array<S>.div(@Suppress(unused) a:Array<distrib<Y>>) = arrayOf(distrib<sh<S>>(path))
@JvmName("10") operator fun <S: etc<Y>> Array<S>.div(@Suppress(unused) a:Array<vim<Y>>) = arrayOf(vim<S>(path))
@JvmName("11") operator fun <S: usr<Y>> Array<S>.div(@Suppress(unused) a:Array<bin<Y>>) = arrayOf(bin<S>(path))
@JvmName("12") operator fun <S: bin<usr<Y>>> Array<S>.div(@Suppress(unused) a:Array<vim<Y>>) = arrayOf(vim<S>(path))
@JvmName("13") operator fun <S: usr<Y>> Array<S>.div(@Suppress(unused) a:Array<local<Y>>) = arrayOf(local<S>(path))
@JvmName("14") operator fun <S: local<Y>> Array<S>.div(@Suppress(unused) a:Array<bin<Y>>) = arrayOf(bin<S>(path))
@JvmName("15") operator fun <S: local<usr<Y>>> Array<S>.div(@Suppress(unused) a:Array<bin<Y>>) = arrayOf(bin<S>(path))
@JvmName("16") operator fun <S: bin<local<usr<Y>>>> Array<S>.div(@Suppress(unused) a:Array<sh<Y>>) = arrayOf(sh<S>(path))