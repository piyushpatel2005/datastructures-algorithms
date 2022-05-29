package leetcode.lessons.arrays;

import java.util.Arrays;

/**
 * Find pair of numbers which make up maximum product in the given array of numbers.
 *
 * Example:
 * Input: numbers = [10, 20, 30, 40, 50]
 * Output: [40, 50]
 */
public class MaxProductOfTwoNumbersJava {
    public static void main(String[] args) {
        MaxProductOfTwoNumbersJava sol = new MaxProductOfTwoNumbersJava();
        int[] nums = {10, 20, 30, 40, 50};
        System.out.println(Arrays.toString(sol.maxProduct(nums)));
    }

    public int[] maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int[] numbers = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] * nums[j] > maxProduct){
                    numbers[0] = nums[i];
                    numbers[1] = nums[j];
                }
            }
        }
        return numbers;
    }
}
