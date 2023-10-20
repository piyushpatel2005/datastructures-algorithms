package leetcode.lessons.sorting;

import java.util.Arrays;

/**
 * Selection sort
 * Divide array into sorted and unsorted sections.
 * Look for minimum element and move to the left most position.
 * Space Complexity: O(1)
 * Time complexity: O(n ^ 2)
 */
public class SelectionSortJava {
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // If it's not already smallest item, then swap with smallest item.
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        SelectionSortJava sorter = new SelectionSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4};
        System.out.println(Arrays.toString(arr));
        sorter.selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
