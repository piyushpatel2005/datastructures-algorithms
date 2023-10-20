package leetcode.lessons.sorting;

import java.util.Arrays;

/**
 * Bubble sort or Sinking sort
 * compare each pair of adjacent items and swap if they are in wrong order.
 * Time complexity: O(n ^ 2)
 * When input is probably almost sorted, then use bubble sort or when space is constraint because this uses O(1) space complexity.
 */
public class BubbleSortJava {
    public void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSortJava sorter = new BubbleSortJava();
        int[] arr = {3,6, 4, 2, 7, 8, 4};
        System.out.println(Arrays.toString(arr));
        sorter.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
