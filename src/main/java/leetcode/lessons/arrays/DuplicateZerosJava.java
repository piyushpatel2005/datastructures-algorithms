package leetcode.lessons.arrays;

import java.util.Arrays;

/**
 * Leetcode Arrays Lesson
 * Problem 1089 Duplicate Zeros
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 * Example 3:
 * Input: [1,2,0,0,3]
 * Output: [1,2,0,0,0]
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 9
 */
public class DuplicateZerosJava {
    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        DuplicateZerosJava sol = new DuplicateZerosJava();
        sol.duplicateZeros(arr);
        System.out.println(Arrays.toString(arr)); // [1,0,0,2,3,0,0,4]

        int[] arr2 = {1, 2, 0, 0, 3};
        sol.duplicateZeros(arr2);
        System.out.println(Arrays.toString(arr2)); // [1
    }

    public void duplicateZeros(int[] arr) {
        int zeros = 0;
        int length = arr.length - 1;

        // First iteration count how many elements we need to insert in the final output array
        // The number of elements will be the number of elements in the existing array and possible duplicates will be number of zeros.
        for (int i = 0; i <= length - zeros; i++) {
            if (arr[i] == 0) {
                // Edge case: If 0 can be repeated only once as in second example.
                if (i == length - zeros) {
                    arr[length] = 0;
                    length -= 1;
                    break;
                }
                zeros++;
            }
        }
        System.out.println("Length is " + length + " and zeros " + zeros);
        // Start filling array from the last element.
        int last = length - zeros;

        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + zeros] = 0;
                zeros--;
                arr[i + zeros] = 0;
            }
            arr[i + zeros] = arr[i];
        }
    }
}
