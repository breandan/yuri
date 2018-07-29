abstract class Directory<T> {}

open class Y<A> private constructor() {
  open class localhost_: Directory<Nothing>() {
    @JvmName("usr")
    operator fun div(a: usr<*>.() -> usr.Companion) = usr
    @JvmName("bin")
    operator fun div(a: bin<*>.() -> bin.Companion) = bin


    companion object: localhost_() {}
  }

  open class bin<T>: Directory<T>() {
    companion object: (bin<*>) -> Companion {
      override fun invoke(a: bin<*>) = this
    }
  }

  open class usr<T>: Directory<T>() {
    companion object: (usr<*>) -> Companion {
      override fun invoke(a: usr<*>) = this
      operator fun div(a: local<*>.() -> local.Companion) = local
    }

  }


  open class local<T>: Directory<T>() {
    companion object: (local<*>) -> Companion {
      override fun invoke(a: local<*>) = this
      operator fun div(a: bin<*>.() -> bin.Companion) = bin
    }
  }


  companion object {
    fun uri(path: Any) {
      TODO()
    }
  }
}