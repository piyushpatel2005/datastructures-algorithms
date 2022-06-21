package leetcode.lessons.sorting;

import java.util.Arrays;

/**
 * Implement Quick Sort algorithm to sort numbers
 *
 * Quick sort is divide and conquer algorithm.
 * Find pivot number and make sure smaller numbers located at the left of pivot and bigger numbers are located to the right.
 * Do this recursively.
 * This does not require extra space to sort elements.
 */
public class QuickSortJava {

    /**
     * Partition the array around a number
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = end; // take right most number as pivot number
        int i = start - 1;
        for (int j = start; j <= end; j++) {
            if (array[j] <= pivot) {
                i++;
                // swap numbers
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        return i;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivot = partition(array, start, end);
            quickSort(array, start, pivot - 1);
            quickSort(array, pivot + 1, end);
        }
    }

    public static void main(String[] args) {
        QuickSortJava sorter = new QuickSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4, 1};
        System.out.println(Arrays.toString(arr));
        sorter.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
