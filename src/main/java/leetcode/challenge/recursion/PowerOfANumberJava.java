package leetcode.challenge.recursion;

/**
 * Given a number as <code>base</code> and an integer <code>exponent</code>, find value of base to the power of exponent.
 */
public class PowerOfANumberJava {
    public static void main(String[] args) {
        int base = 2;
        int exp = 3;
        System.out.printf("%d to the power of %d is %d", base, exp, power(base, exp));
    }

    public static int power(int base, int exp) {
        if (exp == 1) return base;
        if (exp < 0) return -1;
        return base * power(base, exp - 1);
    }
}
