package leetcode.challenge.recursion;

/**
 * Given two integer numbers, find the greatest common divisor of those numbers.
 */
public class GCDJava {
    public static void main(String[] args) {
        int a = 8;
        int b = 4;
        System.out.printf("The GCD of %d and %d is %d\n", a, b, gcd(a, b));
        a = 4;
        b = 8;
        System.out.printf("The GCD of %d and %d is %d\n", a, b, gcd(a, b));
    }

    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) return -1;
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
