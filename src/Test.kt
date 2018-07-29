import Y.*

fun main(a: Array<String>) {
  Y.uri(localhost_/usr/local/bin)
  Y.uri(localhost_/bin)

  //Does not compile!
  //Y.uri(localhost_/usr/bin)
  //Y.uri(localhost_/local)
}