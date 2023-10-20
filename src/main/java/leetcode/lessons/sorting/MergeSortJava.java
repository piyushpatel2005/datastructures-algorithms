package leetcode.lessons.sorting;

import java.util.Arrays;

/**
 * Merge Sort
 * This is a divide and conquer algorithm.
 * Divide the input array into two halves and we keep halving recursively until they become too small that  cannot be broker further.
 * Merge halves by sorting them.
 *
 * This is useful when we need stable sort or when average complexity is O(n * log n)
 * When space is concert, avoid merge sort because it has O(n) space complexity.
 *
 *
 * Space Complexity: O(n)
 * Time Complexity: O(n * log n)
 */
public class MergeSortJava {
    private void merge(int[] arr, int left, int middle, int right) {
        int[] leftTempArray = new int[middle - left + 2];
        int[] rightTempArray = new int[right - middle + 1];
        for (int i = 0; i <= middle - left; i++) {
            leftTempArray[i] = arr[i + left];
        }
        for (int i = 0; i < right - middle; i++) {
            rightTempArray[i] = arr[middle + i + 1];
        }

        leftTempArray[middle - left + 1] = Integer.MAX_VALUE;
        rightTempArray[right - middle] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        for (int k = left; k <= right; k++) {
            if (leftTempArray[i] < rightTempArray[j]) {
                arr[k] = leftTempArray[i];
                i++;
            } else {
                arr[k] = rightTempArray[j];
                j++;
            }
        }
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (right > left) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        MergeSortJava sorter = new MergeSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4, 1};
        System.out.println(Arrays.toString(arr));
        sorter.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
