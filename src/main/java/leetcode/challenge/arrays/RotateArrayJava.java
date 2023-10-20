package leetcode.challenge.arrays;

import java.util.Arrays;

/**
 * Problem 189: Rotate Array
 * Given an integer 2D array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 105
 *     -231 <= nums[i] <= 231 - 1
 *     0 <= k <= 105
 *
 *
 *
 * Follow up:
 *
 *     Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 *     Could you do it in-place with O(1) extra space?
 */
public class RotateArrayJava {
    /**
     * This is brute force approach. Here, we iterate `k` times for rotation.
     * In each rotation, we move each element one position forward. To swap two numbers we need three physical variables
     * So, we create previous and temp and one variable from array itself.
     * If we have k > n, then we will unnecessarily iterate array twice. so, we can use k = nums.length % k to iterate
     * only required number of times.
     * @param nums
     * @param
     *
     * Time Complexity: O(n * k)
     *      Space Complexity: O(1)
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int previous, temp;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
//            System.out.println(Arrays.toString(nums));
        }
    }

    /**
     * The logic for this method is not intuitive.
     * There are two approaches, we use the second one in this method.
     * The steps to rotate array by specific number of times is as follows.
     * Let's take array as [1,2,3,4,5,6,7] and we want to rotate 3 times, so it should become [5,6,7,1,2,3,4]
     * Here, n = length of array = 7, k = 3
     * 1. Flip first `n-k` numbers first. => [4,3,2,1,    5,6,7]
     * 2. Flip last `k` numbers => [4,3,2,1,    7,6,5]
     * 3. Reverse the entire array => [5,6,7, 1,2,3,4]
     *
     * Alternative to this is below.
     * 1. Reverse entire array first => [7,6,5,4,3,2,1]
     * 2. Flip/reverse first `k` numbers => [5,6,7,    4,3,2,1]
     * 3. Flip/reverse last `n-k` numbers => [5,6,7,    1,2,3,4]
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        if (nums.length == 0 || (k % nums.length) == 0) {
            // In this case, the rotation will not change array
            return;
        }

        int n = nums.length;

        // 1. Reverse entire array. O(n / 2)
        reverse(nums);

        // 2. Flip first k numbers
        reverse(nums, 0, k);

        // 3. Reverse last n-k numbers.
        reverse(nums, k, nums.length);

    }

    private void reverse(int[] nums) {
        for (int i = nums.length - 1, j = 0; i > j; i--,j++) {
//            System.out.printf("i=%d, j=%d\n", i, j);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    private void reverse(int[] nums, int startIndex, int endIndex) {
        for (int i = endIndex - 1, j = startIndex; i > j; i--,j++) {
//            System.out.printf("i=%d, j=%d\n", i, j);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        RotateArrayJava rotateArrayJava = new RotateArrayJava();
        int[] array = {1,2,3,4,5,6,7};
        rotateArrayJava.rotate(array, 3);
        System.out.println("rotateArrayJava = " + Arrays.toString(array));

        int[] array2 = {1,2,3,4,5,6,7};
        rotateArrayJava.rotate2(array2, 3);
        System.out.println("rotateArrayJava = " + Arrays.toString(array2));
    }
}
