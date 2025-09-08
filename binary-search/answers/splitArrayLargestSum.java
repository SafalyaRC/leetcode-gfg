// LC-410: https://leetcode.com/problems/split-array-largest-sum/description/

// brute-force:
class Solution {
    public int subArrayPossible(int nums[], int maxSum) {
        int sum = 0, subArrayCount = 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum) {
                subArrayCount++;
                sum = nums[i];
            }
        }
        return subArrayCount;
    }

    public int splitArray(int[] nums, int k) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        for (int i = max; i <= sum; i++) {
            if (subArrayPossible(nums, i) == k)
                return i;
        }
        return max;
    }
}

// optimal:
class Solution {
    public int subArrayCount(int nums[], int maxSum) {
        int sum = 0, count = 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum) {
                count++;
                sum = nums[i];
            }
        }
        return count;
    }

    public int splitArray(int[] nums, int k) {
        int low = Integer.MIN_VALUE;
        int high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (subArrayCount(nums, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}