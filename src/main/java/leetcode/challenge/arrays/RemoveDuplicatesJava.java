package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Given a sorted array with duplicates, write a function to return only unique elements.
 *
 * Input: [1, 1, 2, 3, 3, 4, 5, 6]
 * Output: [1, 2, 3, 4, 5, 6
 *
 * Problem 26: Remove Duplicates from Sorted Array
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Custom Judge:
 *
 * The judge will test your solution with the following code:
 *
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 *
 * If all assertions pass, then your solution will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 *
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 3 * 104
 *     -100 <= nums[i] <= 100
 *     nums
 */
public class RemoveDuplicatesJava {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 5, 6};
        RemoveDuplicatesJava sol = new RemoveDuplicatesJava();
        System.out.println(Arrays.toString(sol.removeDuplicates(arr)));

        int[] arr2 = {1, 1, 2, 3, 3, 4, 5, 6};
        System.out.println("Input array: " + Arrays.toString(arr2));
        int result = sol.removeDuplicates2(arr2);
        System.out.printf("Return value: %d and output array %s\n", result, Arrays.toString(arr2));
    }

    public int[] removeDuplicates(int[] arr) {
        int size = arr.length;
        if (size < 2) return arr;
        int outputSize = removeDuplicateElements(arr);
        int[] outputArray = new int[outputSize];
        for (int i = 0; i < outputSize; i++) {
            outputArray[i] = arr[i];
        }
        return outputArray;
    }

    // If we don't write this method, it will return result with unique elements but later elements will appear as zeros.
    // [1, 2, 3, 4, 5, 0, 0]
    private int removeDuplicateElements(int[] arr) {
        int size = arr.length;

        int[] outputArray = new int[size];
        int j = 0;
        for (int i = 0; i < size - 1; i++) {
            if (arr[i] != arr[i+1])
                arr[j++] = arr[i];
        }
        return j;
    }

    /**
     * This is Leetcode problem 26 solution.
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int j = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[j] = nums[i + 1];
                j++;
            }
        }
        return j;
    }
}
