package leetcode.challenge.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem 1346: Check If N and Its Double Exist
 * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
 *
 * More formally check if there exists two indices i and j such that :
 *
 *     i != j
 *     0 <= i, j < arr.length
 *     arr[i] == 2 * arr[j]
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
 *
 * Example 2:
 *
 * Input: arr = [7,1,14,11]
 * Output: true
 * Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
 *
 * Example 3:
 *
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: In this case does not exist N and M, such that N = 2 * M.
 *
 *
 *
 * Constraints:
 *
 *     2 <= arr.length <= 500
 *     -10^3 <= arr[i] <= 10^3
 */
public class CheckIfNAndItsDoubleExist {
    public boolean checkIfExists(int[] arr) {
        Map<Integer, Integer> numbersMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // for each iteration check if twice of this number is already in map
            // Also check if half of it exists. In this case, due to rounding, we should also check for modulo resulting as 0.
            // Otherwise for numbers like 3 and 7, it will return true.
            if (numbersMap.containsKey(arr[i] * 2) || (numbersMap.containsKey(arr[i] / 2) && arr[i] % 2 == 0))
                return true;
            numbersMap.put(arr[i], 1);
        }
//        for (int i = 0; i < arr.length; i++) {
//            if (numbersMap.containsKey(2 * arr[i]) && arr[i] != 0) {
//                return true;
//            }
//        }
        return false;
    }

    // Using HashSet
    public boolean checkIfExists2(int[] arr) {
        Set<Integer> numbersSet = new HashSet<>();
        for (int num: arr) {
            if (numbersSet.contains(2 * num) || (numbersSet.contains(num / 2) && num % 2 == 0))
                return true;
            numbersSet.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        CheckIfNAndItsDoubleExist sol = new CheckIfNAndItsDoubleExist();
        int[] arr = {3,1,7,11};
        System.out.println(sol.checkIfExists(arr));
        int[] arr2 = {7,1,14,11};
        System.out.println(sol.checkIfExists(arr2));
    }
}
