package educative.arrays;

import java.util.HashMap;
import java.util.Map;

public class AddSumToNJava {

    /**
     * Implement method that will find two numbers which sum up to target value of `n`
     * @param arr   Input array
     * @param n     target sum
     * @return      The numbers which sum up to target value `n`
     */
    public int[] findSum(int[] arr, int n) {
        int[] results = new int[2];
        boolean found = false;
        Map<Integer, Integer> numbers = new HashMap<>();
        for (int num : arr) {
            if (numbers.get(n - num) != null) {
                results[0] = num;
                results[1] = n - num;
                found = true;
                return results;
            } else {
                numbers.put(num, num);
            }
        }
        return results;
    }
}
