// LC-1004: https://leetcode.com/problems/max-consecutive-ones-iii/description/

// brute-force(TLE):
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            int canFlip = k;
            for (int j = i; j < n; j++) {
                if (nums[j] == 1)
                    count++;
                else if (nums[j] == 0 && canFlip > 0) {
                    count++;
                    canFlip--;
                } else
                    break;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}

// better:
class better {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int zeroCount = 0, maxLen = 0;
        int l = 0, r = 0;

        while (r < n) {
            if (nums[r] == 0)
                zeroCount++;
            while (zeroCount > k) {
                if (nums[l] == 0)
                    zeroCount--;
                l++;
            }
            if (zeroCount <= k) {
                int len = r - l + 1;
                maxLen = Math.max(maxLen, len);
            }
            r++;
        }
        return maxLen;
    }
}

// optimal:
public class mMaxConsecutiveOnes {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0, zeroCount = 0;
        int l = 0, r = 0;

        while (r < n) {
            if (nums[r] == 0)
                zeroCount++;
            if (zeroCount > k) {
                if (nums[l] == 0)
                    zeroCount--;
                l++;
            }
            if (zeroCount <= k) {
                int len = r - l + 1;
                maxLen = Math.max(maxLen, len);
            }
            r++;
        }
        return maxLen;
    }
}
