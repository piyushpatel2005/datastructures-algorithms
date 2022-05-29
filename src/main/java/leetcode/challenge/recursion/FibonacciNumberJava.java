package leetcode.challenge.recursion;

/*
Given a number n, find the nth fibonacci number.
Fibonacci number is calculated as sum of its previous two numbers.
Fibonacci series looks like this: 0,1,1,2,3,5,8
fib(4) => 3
fib(10) => 55
 */
public class FibonacciNumberJava {
    public static void main(String[] args) {
        int n = 4;
        System.out.printf("The %d fibonacci number is %d\n", n, fib(n));

        n = 10;
        System.out.printf("The %d fibonacci number is %d\n", n, fib(n));
    }

    public static int fib(int n) {
        if (n < 0) return -1;
        if (n == 0 || n == 1) return n;
        return fib(n-1) + fib(n-2);
    }
}
