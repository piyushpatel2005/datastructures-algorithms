package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Find the missing number in an integer array of 1 to 10.
 * The input array will have only one number missing and the array will be sorted.
 *
 * Example:
 *
 * [1,2,3,4,5,6,8,9,10]
 * missingNumber => 7
 */
public class FindMissingNumberJava {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,8,9,10};
        System.out.printf("The number missing in array %s is %d\n", Arrays.toString(nums), missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        // To find the missing numbers, we can calculate sum of all elements for all elements
        // Check that sum against all elements for all elements where the size of series is last element.
        int numsSum = 0;
        for (int num: nums) {
            numsSum += num;
        }
        // Find sum using n * (n + 1) / 2
        int expectedSum = (nums[nums.length - 1]) * (nums[nums.length - 1] + 1) / 2;
        int missingNumber = expectedSum - numsSum;
        return missingNumber;
    }

}
