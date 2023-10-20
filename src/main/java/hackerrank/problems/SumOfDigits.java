package hackerrank.problems;

/**
 * Find sum of all digits of a number
 * Example:
 * input: 38
 * Output: 11
 *
 * Input: 112
 * Output: 4
 *
 * Explanation:
 * For example, input 38, divide number by 10, we get 30, find modulo, we get 8, so 8 + sumOfDigits(30)
 */
public class SumOfDigits {
    public static int sumOfDigits(int num) {
        if (num == 0) return 0;
        return num % 10 + sumOfDigits(num / 10);
    }

    public static void main(String[] args) {
        int num = 21;
        System.out.printf("Sum of digits of %d is %d\n", num, sumOfDigits(num));
        num = 112;
        System.out.printf("Sum of digits of %d is %d\n", num, sumOfDigits(num));
    }
}
