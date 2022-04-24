package hackerrank.functionalprogramming

/*
Count the number of elements in an array without using count, size or length operators (or their equivalents). The input and output portions will be handled automatically by the grader. You only need to write a function with the recommended method signature.

Input Format

A list of  integers, each on a separate line.

Output Format

Output a single integer , the length of the list that was provided as input.

Sample Input

2
5
1
4
3
7
8
6
0
9
Sample Output

10
Constraints


Each element, , in the list:
 */
object ListLength extends App {
  def f(arr:List[Int]):Int = {
    var count = 0
    for (_ <- arr) {
      count = count + 1
    }
    println(count)
    count
  }

  def f2(arr: List[Int]): Int = {
    val sum: Int = arr.fold(0)((acc, _) => acc + 1)
    println(sum)
    sum
  }

  f(List(2, 5, 1, 4, 3, 7, 8, 6, 0, 9))
  f2(List(2, 5, 1, 4, 3, 7, 8, 6, 0, 9))
}
