package leetcode.challenge.arrays;

/**
 * Write a program to check if an array contains same element twice or it has unique elements.
 *
 * Example:
 * Input: [1,2,3,4,5,6]
 * Output: true
 *
 * Input: [1,2,3,4,2]
 * Output: false
 * In this case, 2 occurs twice in array, hence it's not unique.
 */
public class ArrayContainsUniqueJava {
    public static void main(String[] args) {
        ArrayContainsUniqueJava sol = new ArrayContainsUniqueJava();
        int[] arr = {1,2,3,4,5,6};
        boolean result = sol.isUnique(arr);
        System.out.println(result); // true
        int[] arr2 = {1,2,3,1,4};
        System.out.println(sol.isUnique(arr2)); // false
    }

    public boolean isUnique(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j])
                    return false;
            }
        }
        return true;
    }
}
