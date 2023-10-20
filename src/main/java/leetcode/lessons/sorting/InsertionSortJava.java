package leetcode.lessons.sorting;

import java.util.Arrays;

/**
 * Insertion Sort
 * For this, divide the array into two parts.
 * Take first element and find its correct position in sorted array.
 * Repeat until unsorted array is empty.
 *
 * Useful when we have insufficient memory, easy to implement
 * When we hae continuous inflow of data and we want to keep them sorted.
 */
public class InsertionSortJava {
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j = i;
            while (j > 0 && arr[j-1] > temp) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        InsertionSortJava sorter = new InsertionSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4, 1};
        System.out.println(Arrays.toString(arr));
        sorter.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
