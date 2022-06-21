package leetcode.lessons.searching;

import java.util.Arrays;

public class LinearSearchJava {
    public static int linearSearch(int[] arr, int targetValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetValue)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {7,3,5,4,7,2,1,56,22,23};
        int target = 22;
        int index = linearSearch(arr, target);

        System.out.printf("The element %d is found in %s at index %d\n", target, Arrays.toString(arr), index);
    }
}
