// Given an array arr[] containing integers and an integer k, your task is to find the length of the longest subarray where the sum of its elements is equal to the given value k. If there is no subarray with sum equal to k, return 0 : GFG-https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1

// brute-force:
class Solution {
    public int longestSubarray(int[] arr, int k) {
        // code here
        int sum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k)
                    maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}

// optimal hashmap:
class Solution {
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        int cumSum = 0, maxLen = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            cumSum += arr[i];

            if (cumSum == k) {
                maxLen = i + 1;
            }

            if (map.containsKey(cumSum - k)) {
                maxLen = Math.max(maxLen, (i - map.get(cumSum - k)));
            }
            if (!map.containsKey(cumSum))
                map.put(cumSum, i);
        }
        return maxLen;
    }

}