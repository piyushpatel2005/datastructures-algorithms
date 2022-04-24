package hackerrank.functionalprogramming

/**
Print "Hello World"  amount of times. The input portion will be handled automatically. You need to write a function with the recommended method signature.

Input Format

A single line of input containing integer , the number of times to print "Hello World".

Output Format

Output  lines, each containing "Hello World".

Constraints


Sample Input

4
Sample Output

Hello World
Hello World
Hello World
Hello World
 */
object HelloWorldNTimes extends App {
  def f(n: Int): Unit = {
    for (i <- 1 to n)
      println("Hello World")
  }

  f(4)
}
