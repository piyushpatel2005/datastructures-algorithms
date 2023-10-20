package hackerrank.functionalprogramming

object FilterPositionsInAList extends App {

  /**
   * Output the list with the integers at odd positions removed i.e. the first element, the third element and so on. The relative positions of the remaining even-position elements should be the same as they were in the original array. Each integer will be on a separate line.

NOTE: By odd positions, we mean the first, third, fifth, etc position of the array needs to be filtered out. As per programming language conventions, these might (and they often do) correspond to indices  and so on.

Sample Input

2
5
3
4
6
7
9
8
Sample Output

5
4
7
8
   * @param arr
   */

  def f(arr: List[Int]): List[Int] =
    arr.zipWithIndex.filter(pair => pair._2 % 2 != 0)
      .map(_._1)

  def f2(arr: List[Int]): List[Int] =
    arr.zipWithIndex.collect{ case (a,b) if b % 2 != 0 => a}

  println(f(List(2,5,3,4,6,7,9,8)))

  println(f2(List(2,5,3,4,6,7,9,8)))

}
