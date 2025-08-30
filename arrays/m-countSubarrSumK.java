// Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k (LC-560): https://leetcode.com/problems/subarray-sum-equals-k/description/

// brute-force:
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int count = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}

// optimal hashmap:
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int count = 0, cumSum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < n; i++) {
            cumSum += nums[i];

            count += map.getOrDefault(cumSum - k, 0);

            map.put(cumSum, map.getOrDefault(cumSum, 0) + 1);
        }
        return count;
    }
}