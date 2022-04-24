package hackerrank.functionalprogramming

object LargestString extends App {
  def getLargestString(s: String, k: Int): Unit = {
    val chars = s.map(_.toChar).sorted.reverse
    for (var i = 0; i < chars.size; i += 1) {

    }
  }

  getLargestString("zzzazz", 2) // 'zzazz'
}
