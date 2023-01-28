package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Write a program to check if an array contains a number and return its index position.
 *
 * Input: [1,2,3,4,5,6]
 * searchInArray(arr, 6); // 5
 */
public class SearchNumberInArrayJava {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        int target = 6;
        System.out.printf("The index positions for number %d in array %s is %d\n", target, Arrays.toString(nums), searchInArray(nums, target));
    }

    public static int searchInArray(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }
}
