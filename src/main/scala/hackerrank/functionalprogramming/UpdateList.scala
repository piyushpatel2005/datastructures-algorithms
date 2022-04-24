package hackerrank.functionalprogramming

/*
Update the values of a list with their absolute values. The input and output portions will be handled automatically during grading. You only need to write a function with the recommended method signature.

Input Format

There are  integers, each on a separate line. These are the  elements of the input array.

Output Format

Output the absolute value of  integers, each on a separate line in the same input order.

Sample Input

2
-4
3
-1
23
-4
-54
Sample Output

2
4
3
1
23
4
54
 */
object UpdateList extends App {
  def f(arr: List[Int]): List[Int] = {
    val result: List[Int] = arr.map(_.abs)
    println(result)
    result
  }

  def f2(arr: List[Int]): List[Int] = {
    val result: List[Int] = arr.map(value => value match {
      case value:Int  if value < 0 => -(value)
      case value => value
    })
    println(result)
    result
  }

  f(List(2, -4, 3, -1, 23, -4, -54))
  f2(List(2, -4, 3, -1, 23, -4, -54))
}
