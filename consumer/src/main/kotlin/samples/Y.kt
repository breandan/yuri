package samples

import samples.Y.*
import java.io.File

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

typealias `/` = localhost
typealias `/bin/` = bin<`/`>
typealias `/bin/sh` = sh<`/bin/`>
typealias `/bin/sh.distrib/` = distrib<`/bin/sh`>
typealias `/etc/` = etc<`/`>
typealias `/usr/` = usr<`/`>
typealias `/usr/bin/` = bin<`/usr/`>
typealias `/usr/local/` = local<`/usr/`>
typealias `/usr/local/bin/` = bin<`/usr/local/`>

typealias `bin/` = bin<Y>
typealias `bin/sh` = sh<bin<Y>>
typealias `bin/sh.distrib/` = distrib<`bin/sh`>
typealias `etc/` = etc<Y>
typealias `local/` = local<Y>
typealias `usr/` = usr<Y>
typealias `usr/bin/` = bin<`usr/`>
typealias `usr/local/` = local<`usr/`>
typealias `usr/local/bin/` = bin<`usr/local/`>
typealias `sh.distrib/` = distrib<sh<Y>>
typealias `script.sh` = sh<script<Y>>

// Base filenames
val usr: Array<usr<Y>> = arrayOf(usr(arrayOf()))
val etc: Array<etc<Y>> = arrayOf(etc(arrayOf()))
val bin: Array<bin<Y>> = arrayOf(bin(arrayOf()))
val vim: Array<vim<Y>> = arrayOf(vim(arrayOf()))
val sh: Array<sh<Y>> = arrayOf(sh(arrayOf()))
val script: Array<script<Y>> = arrayOf(script(arrayOf()))
val local: Array<local<Y>> = arrayOf(local(arrayOf()))

@JvmName("aa") operator fun <S: `/`, T: usr<Y>> S.div(@Suppress(unused) a: Array<T>) = arrayOf(usr<S>(path))
@JvmName("ab") operator fun <S: `/`, T: bin<Y>> S.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("ac") operator fun <S: `/`, T: etc<Y>> S.div(@Suppress(unused) a: Array<T>) = arrayOf(etc<S>(path))

@JvmName("00") operator fun <S: `/bin/`, T: sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("01") operator fun <S: `/bin/`, T: `sh.distrib/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(distrib<sh<S>>(path))
@JvmName("02") operator fun <S: `/bin/sh.distrib/`, T: sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("03") operator fun <S: `/etc/`, T: vim<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(vim<S>(path))
@JvmName("04") operator fun <S: `/etc/`, T: `script.sh`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<script<S>>(path))
@JvmName("05") operator fun <S: `/usr/`, T: `bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("06") operator fun <S: `/usr/`, T: `local/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(local<S>(path))
@JvmName("07") operator fun <S: `/usr/bin/`, T: vim<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(vim<S>(path))
@JvmName("08") operator fun <S: `/usr/local/`, T: `bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("09") operator fun <S: `/usr/local/bin/`, T: sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))

// File extension notation
val <T: sh<Y>> Array<T>.distrib: Array<distrib<T>> get() = arrayOf(distrib(path))
val <T: script<Y>> Array<T>.sh: Array<sh<T>> get() = arrayOf(sh(path))

// Shorthand for accessing path
private val <T: Y> Array<T>.path: Array<Y> get() = this[0].path

// All valid prefix strings for Kleene-star search
@JvmName("10") operator fun <T: `bin/`, S: `usr/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("11") operator fun <T: `bin/`, S: `local/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("12") operator fun <T: `bin/`, S: `usr/local/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(bin<S>(path))
@JvmName("13") operator fun <T: `local/`, S: `usr/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(local<S>(path))
@JvmName("14") operator fun <T: `sh.distrib/`, S: `bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(distrib<sh<S>>(path))

@JvmName("15") operator fun <T: sh<Y>, S: `bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("16") operator fun <T: sh<Y>, S: `sh.distrib/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("17") operator fun <T: sh<Y>, S: `bin/sh.distrib/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("18") operator fun <T: sh<Y>, S: `usr/local/bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<S>(path))
@JvmName("19") operator fun <T: `script.sh`, S: `etc/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(sh<script<S>>(path))
@JvmName("20") operator fun <T: vim<Y>, S: `bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(vim<S>(path))
@JvmName("21") operator fun <T: vim<Y>, S: `etc/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(vim<S>(path))
@JvmName("22") operator fun <T: vim<Y>, S: `usr/bin/`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(vim<S>(path))

@DslMarker
annotation class Yuri

private const val unused = "UNUSED_PARAMETER"

@Yuri
sealed class Y constructor(private vararg val uri: Y): File(uri.toString()) {
  @Yuri object localhost: Y() {
    override val fileName: String get() = ""
    override fun toString() = "/"
  }

  @Yuri class bin<T>(uri: Array<Y>): Y(*uri)
  @Yuri class script<T>(uri: Array<Y>): Y(*uri)
  @Yuri class etc<T>(uri: Array<Y>): Y(*uri)
  @Yuri class vim<T>(uri: Array<Y>): Y(*uri)
  @Yuri class usr<T>(uri: Array<Y>): Y(*uri)
  @Yuri class local<T>(uri: Array<Y>): Y(*uri)
  @Yuri class sh<U>(uri: Array<Y>): Y(*uri)
  @Yuri class distrib<U>(uri: Array<Y>): Y(*uri)

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

    @Yuri fun uri(y: Y): File = y
    @Yuri fun uri(y: Array<out Y>): File = File("${y.first()}")
//    @Yuri fun uri(g: G) = File("$g")
    @Yuri fun uris(y: Y) = allPaths.filter { it.endsWith("$y") }.map { File(it) }
    @Yuri fun uris(vararg files: Y, function: (File) -> Unit = {}) = uris(files.first()).forEach(function)
  }

  open val path: Array<Y> get() = arrayOf(*uri, this)
  open val fileName: String get() = this.javaClass.simpleName
  override fun toString() = path.joinToString("/") { it.fileName }
}
