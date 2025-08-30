// You are given an array prices where prices[i] is the price of a given stock on the ith day.You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0 (LC-121): https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

/* Example 1:
 Input: prices = [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.*/

// brute-force:
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int profit = 0;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] > prices[i]) {
                    profit = prices[j] - prices[i];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        return maxProfit;
    }
}

// optimal:
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;

        int profit = 0;

        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            profit = price - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}