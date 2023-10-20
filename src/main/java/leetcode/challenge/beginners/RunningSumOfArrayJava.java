package leetcode.challenge.beginners;

import java.util.Arrays;

/**
 * Leetcode Challenge: 1480 Running Sum of 1D array
 *
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [1,3,6,10]
 * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 *
 * Example 2:
 *
 * Input: nums = [1,1,1,1,1]
 * Output: [1,2,3,4,5]
 * Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 *
 * Example 3:
 *
 * Input: nums = [3,1,2,10,1]
 * Output: [3,4,6,16,17]
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
public class RunningSumOfArrayJava {

    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length <= 0)
            return result;
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }

    public int[] runningSum2(int[] nums) {
        if (nums.length <= 0)
            return nums;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        RunningSumOfArrayJava sol = new RunningSumOfArrayJava();
        int[] arr = {1,2,3,4};
        System.out.println(Arrays.toString(sol.runningSum(arr)));
        System.out.println(Arrays.toString(sol.runningSum2(arr)));
    }
}
