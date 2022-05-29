package leetcode.lessons.arrays;

import java.util.Arrays;

/**
 * Given a square matrix of N x N elements. Write a function to rotate elements by 90 degrees.
 *
 * 1  2  3              7  4  1
 * 4  5  6        =>    8  5  2
 * 7  8  9              9  6  3
 */
public class RotateMatrixJava {
    public static void main(String[] args) {
        RotateMatrixJava mn = new RotateMatrixJava();
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        mn.rotateMatrix(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public boolean rotateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            System.out.println("This is not a square array, so it cannot be rotated.");
            return false;
        }
        int size = matrix.length;
        // layer represents outer elements and we rotate one element at a time starting from top left corner.
        for (int layer = 0; layer < size / 2; layer++) {
            int first = layer;
            int last = size - 1 - layer;
            for (int i = first; i < last; i++) {
                // offset is like whether it's corner element or second element from the corner, etc.
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last-offset][first];
                matrix[last-offset][first] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        return true;
    }
}
