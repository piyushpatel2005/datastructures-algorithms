package hackerrank.functionalprogramming

/*
You are given a list of  elements. Reverse the list without using the reverse function. The input and output portions will be handled automatically. You need to write a function with the recommended method signature.

Input Format

Each element, , of the list is displayed on a separate line.

Output Format

The output is the reverse of the input list.

Sample Input

19
22
3
28
26
17
18
4
28
0
Sample Output

0
28
4
18
17
26
28
3
22
19
Method Signature

Number Of Parameters: 1
Parameters: [list]
Returns: List or Vector
 */
object ReverseAList extends App {
  // This solves it
  def f(arr:List[Int]):List[Int] = {
    val result: List[Int] = arr.foldLeft(List.empty[Int])((acc, value) => value::acc)
    println(result + "\n")
    return result
  }

  // This does not using foldRight
  def f2(arr:List[Int]): List[Int] = {
    val result = arr.foldRight(List.empty[Int])((value, acc) => value :: acc)
    println(result)
    return result
  }

  // fold is like a reduce
  def f3(arr:List[Int]): Int = {
    val result = arr.fold(0)((acc, value) => value + acc)
    println(result)
    return result
  }

  f(List(1,2,3,4))
  f2(List(1,2,3,4))
  f3(List(1,2,3))
}
