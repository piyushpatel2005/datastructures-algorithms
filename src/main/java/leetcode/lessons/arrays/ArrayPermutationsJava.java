package leetcode.lessons.arrays;

/**
 * Given two arrays, write a function to check if they are permutation of each other.
 *
 * Permutation means they have same elements regardless of their order in both arrays.
 *
 * Example:
 * Input: arr1 = [1,2,3,4,5]   arr2 = [1,2,5,4,3]
 * Output: true because both have same elements and same size.
 */
public class ArrayPermutationsJava {
    public static void main(String[] args) {
        ArrayPermutationsJava sol = new ArrayPermutationsJava();
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {4,3,5,2,1};
        System.out.println(sol.permutation(arr1, arr2));
    }

    public boolean permutation(int[] arr1, int[] arr2) {
        // If two arrays are permutations, their sum of elements and multiplication of elements will be exact same.
        if (arr1.length != arr2.length)
            return false;
        int sum1 = 0;
        int sum2 = 0;
        int product1 = 1;
        int product2 = 1;
        for (int i = 0; i < arr1.length; i++) {
            sum1 += arr1[i];
            sum2 += arr2[i];
            product1 *= arr1[i];
            product2 *= arr2[i];
        }
        if (sum1 == sum2 && product1 == product2)
            return true;
        return false;
    }
}
