package leetcode.lessons.searching;

import java.util.Arrays;

/**
 * Binary Search:
 * Time Complexity: O(log n)
 */
public class BinarySearchJava {
    public static int binarySearch(int[] arr, int targetValue) {
        int start = 0;
        int end = arr.length - 1;
        int middle = (start + end) / 2;
        while (start <= end) {
            if (targetValue > arr[middle]) {
                start = middle + 1;
            } else if (targetValue < arr[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
            middle = (start + end) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 7, 9, 10, 12, 14, 18, 21, 22, 25, 28};
        int target = 100;
        int index = binarySearch(arr, target);

        System.out.printf("The element %d is found in %s at index %d\n", target, Arrays.toString(arr), index);
    }
}
