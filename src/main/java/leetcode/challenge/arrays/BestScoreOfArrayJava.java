package leetcode.challenge.arrays;

import java.util.Arrays;
/**
 * Given an array, write a function to get first, second best scores from the array.
 *
 * Constraint: Array may contain duplicates.
 *
 * Input: {84, 85, 86, 87, 90, 85, 23, 45}
 * firstSecond(arr)   // 90,87
 */
public class BestScoreOfArrayJava {

    public static void main(String[] args) {
        BestScoreOfArrayJava sol = new BestScoreOfArrayJava();
        Integer[] arr = {84, 85, 86, 87, 90, 85, 23, 45, 90};
        System.out.printf("The first and second best scores in %s is %s\n", Arrays.toString(arr), sol.firstSecond(arr));
    }

    public String firstSecond(Integer[] arr) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > first) {
                System.out.println(first + " " + second);
                second = first;
                first = arr[i];
            }
        }
        return String.format("%s,%s", first, second);
    }
}
