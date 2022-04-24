package hackerrank.functionalprogramming

/*
You are given a list. Return the sum of odd elements from the given list. The input and output portions will be handled automatically. You need to write a function with the recommended method signature.

Constraints

The list will have  elements.
Each element will be an integer  where .

Sample Input

3
2
4
6
5
7
8
0
1
Sample Output

16
Explanation

Sum of odd elements is
 */
object SumOfOddElements extends App {
  def f(arr:List[Int]): Int = {
    val sum: Int = arr.filter(_ % 2 != 0).sum
    println(sum)
    sum
  }

  def f2(arr: List[Int]): Int = {
    val sum: Int = arr.filter(_ % 2 != 0).reduce(_ + _)
    println(sum)
    sum
  }

  f(List(3,2,4,6,5,7,8,0,1))
  f2(List(3,2,4,6,5,7,8,0,1))
}
