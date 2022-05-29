package leetcode.challenge.recursion;

/**
 * Given a number <code>n</code>, find the recursive sum of all elements less than that number.
 *
 * For example,
 * >> recursiveRange(5) // 15 => 5 + 4 + 3 + 2 + 1
 * >> recursiveRange(6) // 21 => 6 + 5 + 4 + 3 + 2 + 1
 */
public class RecursiveRangeJava {
    public static void main(String[] args) {
        int n = 6;
        System.out.printf("The recursive range of %d is %d\n", n, recursiveRange(n));
    }

    public static int recursiveRange(int n) {
        if (n <= 1) return 1;
        return recursiveRange(n - 1) + n;
    }
}
