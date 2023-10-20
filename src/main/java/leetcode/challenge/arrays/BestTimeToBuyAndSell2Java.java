package leetcode.challenge.arrays;

/**
 * Problem 122: Best Time to Buy and Sell Stock II
 *
 * You are given an integer array `prices` where `prices[i]` is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any
 * time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 *
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 *
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 *
 *
 *
 * Constraints:
 *
 *     1 <= prices.length <= 3 * 104
 *     0 <= prices[i] <= 104
 */
public class BestTimeToBuyAndSell2Java {

    public int bruteForce(int[] prices) {
        int total = 0;
        return total;
    }

    /**
     * This is solved using peak and valley.
     *     Whenever we have a valley, we set the peak and valley to same value and if next value is bigger than peak, we set peak to this new value.
     *     As long as next value is larger, peak will keep increasing.
     *     When we find valley, at that time, we have to add `peak-valley` to the `total`.
     *      At this point, we also reset our valley to this new valley and peak to this value.
     *      At the end of the array, we may not have next valley, so at that point, we have to manually add `peak-valley` at end of loop.
     *      This will work for those cases where we only had valley without peak because at this point, we have both peak and valley at same value.
     *      resulting in `peak-valley` of 0. So, `total` is not affected.
     *
     *      Time Complexity: O(n)
     *      Space Complexity: O(1)
     */
    public int maxProfit(int[] prices) {
        int total = 0;
        int valley = Integer.MAX_VALUE;
        int peak = valley;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < peak) {
                total += peak - valley;
                valley = prices[i];
                peak = valley;
            } else {
                peak = prices[i];
            }
        }
        // at loop end, it may not add to total because there is no valley following after the last peak
        total += peak - valley;
        return total;
    }


    /**
     * There is another option of adding only positive differences. We do not need to keep track of valley and peak.
     * Every time, the next value is larger, we can add that difference to total.
     * If next value is even larger, we can add these two consecutive differences.
     * This results in difference of peak and valley and we do not need two variables.
     * @param prices
     * @return
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxProfit2(int[] prices) {
        int total = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                total += prices[i] - prices[i - 1];
            }
        }
        return total;
    }
}
