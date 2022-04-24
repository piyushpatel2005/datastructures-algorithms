package hackerrank.functionalprogramming

object ListReplication extends App {

  /**
  Given a list, repeat each element in the list  amount of times. The input and output portions will be handled automatically by the grader. You need to write a function with the recommended method signature.

Input Format

The first line contains the integer  where  is the number of times you need to repeat the elements.
The next  lines each contain an integer. These are the  elements in the array.

Output Format

Output each element of the original list  times, each on a separate line. You have to return the list/vector/array of  integers. The relative positions of the values should be the same as the original list provided in the input.

Constraints



Sample Input

3
1
2
3
4
Sample Output

1
1
1
2
2
2
3
3
3
4
4
4
   */
  def f(num:Int,arr:List[Int]):List[Int] = arr.map(item => List.fill(num)(item)).flatten
  println(f(3, List(1,2,3,4)))
}
