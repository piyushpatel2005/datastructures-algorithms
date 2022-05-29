package leetcode.lessons.arrays;

import java.util.Arrays;

/**
 * Given a sorted array with duplicates, write a function to return only unique elements.
 *
 * Input: [1, 1, 2, 3, 3, 4, 5, 6]
 * Output: [1, 2, 3, 4, 5, 6
 */
public class RemoveDuplicatesJava {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 5, 6};
        RemoveDuplicatesJava sol = new RemoveDuplicatesJava();
        System.out.println(Arrays.toString(sol.removeDuplicates(arr)));
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
}
