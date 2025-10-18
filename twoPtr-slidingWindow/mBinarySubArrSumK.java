// LC-930: https://leetcode.com/problems/binary-subarrays-with-sum/

// brute-force:
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int count = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            int k = goal;
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

// optimal:
public class mBinarySubArrSumK {
    public int numSubarrayLessEqualsSum(int nums[], int goal) {
        if (goal < 0)
            return 0;
        int n = nums.length;
        int sum = 0, count = 0;
        int l = 0, r = 0;

        while (r < n) {
            sum += nums[r];
            while (sum > goal) {
                sum -= nums[l];
                l++;
            }
            if (sum <= goal) {
                count += (r - l + 1);
            }
            r++;
        }
        return count;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return numSubarrayLessEqualsSum(nums, goal) - numSubarrayLessEqualsSum(nums, goal - 1);
    }
}
