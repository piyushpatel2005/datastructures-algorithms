package leetcode.lessons.arrays;

import java.util.Arrays;

/**
 * Write a function `middle` that takes a list and returns a new list that contains all
 *  but the first and last elements.
 *
 *  Input:   [1, 2, 3, 4]
 *  Output:  [2, 3]
 */
public class MiddleFunctionJava {
    public static void main(String[] args) {
        MiddleFunctionJava sol = new MiddleFunctionJava();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(sol.middle(nums)));

        int[] nums2 = {1};
        System.out.println(Arrays.toString(sol.middle(nums2)));
    }

    public int[] middle(int[] arr) {
        if (arr.length < 2) return new int[]{};
        return Arrays.copyOfRange(arr, 1, arr.length - 1);
    }

}
