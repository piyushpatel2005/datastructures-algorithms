package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Write a function to find all pairs of integers whose sum is equal to given number.
 *
 * Example:
 *
 * Input:
 * nums = [2, 7, 11, 15], targetSum = 9
 * Output:
 * [0, 1] because sum of nums[0] + nums[1] = 2 + 7 = 9
 *
 * Input:
 * nums = [3, 2, 4], targetSum = 6
 * Output:
 * [1, 2]
 */
public class SumPairsJava {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        int[] indices = sumPairs(nums, target);
        System.out.printf("The pair of indices which return sum %d in array %s is %s\n", target, Arrays.toString(nums), Arrays.toString(indices));
    }

    public static int[] sumPairs(int[] nums, int targetSum) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == targetSum)
                    return new int[] {i, j};
            }
        }
        throw new IllegalArgumentException(String.format("Invalid target sum '%d' for array %s", targetSum, Arrays.toString(nums)));
    }
}
