package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Leetcode Problem 1572: Matrix Diagonal Sum
 * Given a 2D array, calculate the sum of diagonal elements.
 */
public class SumOfDiagonalElementsJava {
    public static void main(String[] args) {
        SumOfDiagonalElementsJava sol = new SumOfDiagonalElementsJava();
        int[][] arr = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.printf("Sum of elements of %s is %d\n", Arrays.deepToString(arr), sol.sumDiagonal(arr));
    }

    public int sumDiagonal(int[][] arr) {
        if (arr.length != arr[0].length) {
            System.out.println("Given array " + Arrays.deepToString(arr) + " is not square matrix so it doesn't have diagonal.");
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][i];
        }
        return sum;
    }

}
