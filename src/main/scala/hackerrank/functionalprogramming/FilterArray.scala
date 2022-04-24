package hackerrank.functionalprogramming


object FilterArray extends App {

  /**
   * Filter a given array of integers and output only those values that are less than a specified value . The output integers should be in the same sequence as they were in the input. You need to write a function with the recommended method signature for the languages mentioned below. For the rest of the languages, you have to write a complete code.

Input Format

The first line contains the delimiter .
The next  lines each contain an integer, which represents the elements of the list/array. You have to read the input to the End-Of-File.

Output Format

Print all the integers from the array that are less than the given upper limit  in value on separate lines. The sequence should be the same as it was in the original array.

Constraints


For any element,  in the array,

Note

The purpose of this challenge is to learn how to write your own implementation of a filter function. We recommend not using the inbuilt library function.

Sample Input

3
10
9
8
2
7
5
1
3
0
Sample Output

2
1
0
Explanation

 and  are the list elements that are less than the  delimiter, . They are displayed in the same order as they were in the original list.
   */
  def f(delim: Int, arr: List[Int]): List[Int] = arr.filter(_ < delim)
  f(3, List(10, 9, 8, 2, 7, 5, 1, 3, 0))
}
