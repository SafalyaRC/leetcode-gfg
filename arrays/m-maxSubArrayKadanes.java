// Given an integer array nums, find the subarray with the largest sum, and return its sum (LC-53): https://leetcode.com/problems/maximum-subarray/description/

// brute-force:
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int sum = 0, maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}

// optimal(kadane's algo):
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);

            if (sum < 0)
                sum = 0; // reset the sum
        }
        return maxSum;
    }
}