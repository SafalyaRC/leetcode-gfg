// LC-1283: https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/

// brute-force:
class Solution {
    public int divisorSum(int nums[], int divisor) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.ceil((double) nums[i] / (double) divisor);
        }
        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        for (int i = 1; i <= max; i++) {
            if (divisorSum(nums, i) <= threshold)
                return i;
        }
        return -1;
    }
}

// optimal:
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);

        int low = 1, high = max;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sumDivisor(nums, mid) <= threshold) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    public int sumDivisor(int nums[], int mid) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.ceil((double) num / (double) mid);
        }
        return sum;
    }
}