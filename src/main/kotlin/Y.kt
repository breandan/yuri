package main.kotlin

import main.kotlin.Y.*
import java.io.File

val allPaths = arrayOf(
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

private typealias `/` = localhost
private typealias `/bin/` = D.bin<`/`>
private typealias `/bin/sh` = F.sh<`/bin/`>
private typealias `/bin/sh.distrib/` = D.sh.distrib<`/bin/`>
private typealias `/etc/` = D.etc<`/`>
private typealias `/usr/` = D.usr<`/`>
private typealias `/usr/bin/` = D.bin<`/usr/`>
private typealias `/usr/local/` = D.local<`/usr/`>
private typealias `/usr/local/bin/` = D.bin<`/usr/local/`>

private typealias `*usr` = Y.usr<Y>
private typealias `*etc` = Y.etc<Y>
private typealias `*bin` = Y.bin<Y>
private typealias `*vim` = Y.vim<Y>
private typealias `*sh` = Y.sh<Y>
private typealias `*script` = Y.script<Y>
private typealias `*local` = Y.local<Y>
private typealias `*distrib` = Y.distrib<Y>
private typealias `*script.sh` = Y.script.sh<Y>
private typealias `*sh.distrib` = Y.sh.distrib<Y>

private typealias `*bin/sh` = Y.sh<D.bin<Y>>
private typealias `*bin/vim` = Y.vim<D.bin<Y>>
private typealias `*bin/sh.distrib` = Y.sh.distrib<D.bin<Y>>
private typealias `*bin/sh.distrib/sh` = Y.sh<D.sh.distrib<D.bin<Y>>>
private typealias `*etc/script.sh` = Y.script.sh<D.etc<Y>>
private typealias `*etc/vim` = Y.vim<D.etc<Y>>
private typealias `*local/bin` = Y.bin<D.local<Y>>
private typealias `*local/bin/sh` = Y.sh<D.bin<D.local<Y>>>
private typealias `*usr/bin` = Y.bin<D.usr<Y>>
private typealias `*usr/bin/vim` = Y.vim<D.bin<D.usr<Y>>>
private typealias `*usr/local` = Y.local<D.usr<Y>>
private typealias `*usr/local/bin` = Y.bin<D.local<D.usr<Y>>>
private typealias `*usr/local/bin/sh` = Y.sh<D.bin<D.local<D.usr<Y>>>>
private typealias `*sh.distrib/sh` = Y.sh<D.sh.distrib<Y>>

@DslMarker
annotation class Yuri

// Base tokens
@Yuri val sh: Array<`*sh`> = arrayOf(Y.sh(null))
@Yuri val usr: Array<`*usr`> = arrayOf(Y.usr(null))
@Yuri val etc: Array<`*etc`> = arrayOf(Y.etc(null))
@Yuri val bin: Array<`*bin`> = arrayOf(Y.bin(null))
@Yuri val vim: Array<`*vim`> = arrayOf(Y.vim(null))
@Yuri val local: Array<`*local`> = arrayOf(Y.local(null))
@Yuri val script: Array<`*script`> = arrayOf(Y.script(null))
@Yuri val distrib: Array<`*distrib`> = arrayOf(Y.distrib(null))

@JvmName("aa") operator fun <T: Y.usr<Y>> `/`.div(@Suppress(unused) a: Array<T>) = arrayOf(D.usr<`/`>(`/`))
@JvmName("ab") operator fun <T: Y.bin<Y>> `/`.div(@Suppress(unused) a: Array<T>) = arrayOf(D.bin<`/`>(`/`))
@JvmName("ac") operator fun <T: Y.etc<Y>> `/`.div(@Suppress(unused) a: Array<T>) = arrayOf(D.etc<`/`>(`/`))

@JvmName("00") operator fun <S: `/bin/`, T: Y.sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.sh<S>(path))
@JvmName("01") operator fun <S: `/bin/`, T: `*sh.distrib`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(D.sh.distrib<S>(path))
@JvmName("02") operator fun <S: `/bin/sh.distrib/`, T: Y.sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.sh<S>(path))
@JvmName("03") operator fun <S: `/etc/`, T: Y.vim<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.vim<S>(path))
@JvmName("04") operator fun <S: `/etc/`, T: `*script.sh`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.script.sh<S>(path))
@JvmName("05") operator fun <S: `/usr/`, T: `*bin`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(D.bin<S>(path))
@JvmName("06") operator fun <S: `/usr/`, T: `*local`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(D.local<S>(path))
@JvmName("07") operator fun <S: `/usr/bin/`, T: Y.vim<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.vim<S>(path))
@JvmName("08") operator fun <S: `/usr/local/`, T: `*bin`> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(D.bin<S>(path))
@JvmName("09") operator fun <S: `/usr/local/bin/`, T: Y.sh<Y>> Array<S>.div(@Suppress(unused) a: Array<T>) = arrayOf(F.sh<S>(path))

// File extension notation
val <S: `*sh`> Array<S>.distrib: Array<`*sh.distrib`> get() = arrayOf(`*sh.distrib`(path))
val <S: `*script`> Array<S>.sh: Array<`*script.sh`> get() = arrayOf(`*script.sh`(path))

// Shorthand for accessing path
private val <T: Y> Array<T>.path: Y get() = this[0]

// All valid prefix strings for Kleene-star search
@JvmName("10") operator fun <S: `*usr`> Array<S>.div(@Suppress(unused) a: Array<`*bin`>) = arrayOf(`*usr/bin`(path))
@JvmName("13") operator fun <S: `*usr`> Array<S>.div(@Suppress(unused) a: Array<`*local`>) = arrayOf(`*usr/local`(path))
@JvmName("11") operator fun <S: `*local`> Array<S>.div(@Suppress(unused) a: Array<`*bin`>) = arrayOf(`*local/bin`(path))
@JvmName("12") operator fun <S: `*usr/local`> Array<S>.div(@Suppress(unused) a: Array<`*bin`>) = arrayOf(`*usr/local/bin`(path))
@JvmName("14") operator fun <S: `*bin`> Array<S>.div(@Suppress(unused) a: Array<`*sh.distrib`>) = arrayOf(`*bin/sh.distrib`(path))
@JvmName("15") operator fun <S: `*bin`> Array<S>.div(@Suppress(unused) a: Array<`*sh`>) = arrayOf(`*bin/sh`(path))
@JvmName("16") operator fun <S: `*sh.distrib`> Array<S>.div(@Suppress(unused) a: Array<`*sh`>) = arrayOf(`*sh.distrib/sh`(path))
@JvmName("17") operator fun <S: `*local/bin`> Array<S>.div(@Suppress(unused) a: Array<`*sh`>) = arrayOf(`*local/bin/sh`(path))
@JvmName("18") operator fun <S: `*bin/sh.distrib`> Array<S>.div(@Suppress(unused) a: Array<`*sh`>) = arrayOf(`*bin/sh.distrib/sh`(path))
@JvmName("19") operator fun <S: `*usr/local/bin`> Array<S>.div(@Suppress(unused) a: Array<`*sh`>) = arrayOf(`*usr/local/bin/sh`(path))
@JvmName("20") operator fun <S: `*etc`> Array<S>.div(@Suppress(unused) a: Array<`*script.sh`>) = arrayOf(`*etc/script.sh`(path))
@JvmName("21") operator fun <S: `*bin`> Array<S>.div(@Suppress(unused) a: Array<`*vim`>) = arrayOf(`*bin/vim`(path))
@JvmName("22") operator fun <S: `*etc`> Array<S>.div(@Suppress(unused) a: Array<`*vim`>) = arrayOf(`*etc/vim`(path))
@JvmName("23") operator fun <S: `*usr/bin`> Array<S>.div(@Suppress(unused) a: Array<`*vim`>) = arrayOf(`*usr/bin/vim`(path))

private const val unused = "UNUSED_PARAMETER"

sealed class Y constructor(private vararg val parent: Y?): File(parent.toString()) {
  object localhost: Y() {
    override val fileName: String get() = ""
    override fun toString() = "/"
  }

  open class bin<T>(parent: Y?): Y(parent)
  open class etc<T>(parent: Y?): Y(parent)
  open class vim<T>(parent: Y?): Y(parent)
  open class usr<T>(parent: Y?): Y(parent)
  open class local<T>(parent: Y?): Y(parent)
  open class sh<T>(parent: Y?): Y(parent) {
    open class distrib<T>(parent: Y?): Y(parent) {
      override val fileName: String get() = "sh.distrib"
    }
  }
  open class script<T>(parent: Y?): Y(parent) {
    open class sh<T>(parent: Y?): Y(parent) {
      override val fileName: String get() = "script.sh"
    }
  }

  open class distrib<T>(parent: Y?): Y(parent)

  open class F<T>(private val parent: Y?): Y(parent) {
    open class bin<T>(parent: Y?): Y.bin<T>(parent)
    open class etc<T>(parent: Y?): Y.etc<T>(parent)
    open class vim<T>(parent: Y?): Y.vim<T>(parent)
    open class usr<T>(parent: Y?): Y.usr<T>(parent)
    open class local<T>(parent: Y?): Y.local<T>(parent)
    open class script<T>(parent: Y?): Y(parent) {
      open class sh<T>(parent: Y?): Y(parent) {
        override val fileName: String get() = "script.sh"
      }
    }
    open class sh<T>(parent: Y?): F<T>(parent) {
      open class distrib<T>(parent: Y?): F<T>(parent) {
        override val fileName: String get() = "sh.distrib"
      }
    }
  }

  open class D<T>(private val parent: Y?): F<T>(parent) {
    class bin<T>(parent: Y?): D<T>(parent)
    class etc<T>(parent: Y?): D<T>(parent)
    class usr<T>(parent: Y?): D<T>(parent)
    class local<T>(parent: Y?): D<T>(parent)
    open class sh<T>(parent: Y?): D<T>(parent) {
      open class distrib<T>(parent: Y?): D<T>(parent) {
        override val fileName: String get() = "sh.distrib"
      }
    }

    override fun toString() = if(parent == null) "*$fileName/" else "$parent$fileName/"
  }

  companion object {
    fun uri(y: Y): File = y
    fun uri(y: Array<out Y>): File = File("${y.first()}")
    fun uri(g: G) = File("$g")
    fun uris(y: Y) = allPaths.filter { it.endsWith("$y") }.map { File(it) }
    fun uris(vararg files: Y, function: (File) -> Unit = {}) = uris(files.first()).forEach(function)
  }

  open val fileName: String get() = this.javaClass.simpleName
  override fun toString() = if(parent.isEmpty() || parent[0] == null) "*$fileName" else "${parent[0]}$fileName"
}