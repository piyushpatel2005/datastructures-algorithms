package leetcode.challenge.beginners;

/**
 * Leetcode Challenge 1342: Number of Steps to Reduce a Number to Zero
 *
 * Given an integer num, return the number of steps to reduce it to zero.
 *
 * In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 14
 * Output: 6
 * Explanation:
 * Step 1) 14 is even; divide by 2 and obtain 7.
 * Step 2) 7 is odd; subtract 1 and obtain 6.
 * Step 3) 6 is even; divide by 2 and obtain 3.
 * Step 4) 3 is odd; subtract 1 and obtain 2.
 * Step 5) 2 is even; divide by 2 and obtain 1.
 * Step 6) 1 is odd; subtract 1 and obtain 0.
 *
 * Example 2:
 *
 * Input: num = 8
 * Output: 4
 * Explanation:
 * Step 1) 8 is even; divide by 2 and obtain 4.
 * Step 2) 4 is even; divide by 2 and obtain 2.
 * Step 3) 2 is even; divide by 2 and obtain 1.
 * Step 4) 1 is odd; subtract 1 and obtain 0.
 *
 * Example 3:
 *
 * Input: num = 123
 * Output: 12
 *
 * Divinding a number by 2 is equivalent to shift right. So, that can help with this problem
 * To check if number if odd or even, we can use and operation with 1. => 0000 0001
 *
 * Time complexity: O(log n) as we are halving the number in each step.
 */
public class NumberOfStepsToReduceToZero {
    // Intuitive common approach
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            if (num % 2 == 0)
                num = num / 2;
            else
                num = num - 1;
            steps++;
        }
        return steps;
    }

    // Bitwise approach
    public int numberOfSteps2(int num) {
        int steps = 0;
        while (num > 0) {
            if ((num & 1) == 0)
                num = num >> 1; // shift right
            else
                num = num - 1;
            steps++;
        }
        return steps;
    }
}
