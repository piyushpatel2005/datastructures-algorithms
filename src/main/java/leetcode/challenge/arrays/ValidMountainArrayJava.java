package leetcode.challenge.arrays;

/**
 * Problem 941: Valid Mountain Array
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 *     arr.length >= 3
 *     There exists some i with 0 < i < arr.length - 1 such that:
 *         arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *         arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1]
 * Output: false
 *
 * Example 2:
 *
 * Input: arr = [3,5,5]
 * Output: false
 *
 * Example 3:
 *
 * Input: arr = [0,3,2,1]
 * Output: true
 *
 * Example 4:
 * Input: arr = [0, 2, 3, 3, 5, 2, 1, 0]
 * Output: false
 *
 * Explanation:
 * For first example, array size is less than 3
 * For second one, it is increasing but there is no decreasing portion to make a triangle
 * For third one, 0-3: increasing then it's continuously decreasing. So, it is mountain array.
 * For fourth example, 0-3: incrasing, then 3-3 is steady, so it is not mountain array.
 *
 * Constraints:
 *
 *     1 <= arr.length <= 104
 *     0 <= arr[i] <= 104
 */
public class ValidMountainArrayJava {

    /**
     * Start from beginning, the next number should be increasing.
     * If it stops increasing then it should continuously decrease.
     * @param arr - input array to check
     * @return - whether this array is mountain array
     */
    public boolean validMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }

        boolean rising = arr[1] > arr[0];
        if (!rising) return false; // If not increasing, it's not mountain array.

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1])
                return false; // same values are not allowed
            if (rising) {
                // As long as rising, do nothing. If found two numbers where it's decreasing, that will be first decreasing, so it should start decreasing.
                if (arr[i] < arr[i - 1])
                    rising = false; // In this case, it's decreasing or plateau
            } else {
                // As long as decreasing, it's ok.
                // If a number comes where it suddenly increases after rising, then that's not mountain array.
                if (arr[i] > arr[i - 1])
                    return false;
            }
        }
        return !rising; // We were expecting rising to go down, so return inverse of rising variable.

    }
}
