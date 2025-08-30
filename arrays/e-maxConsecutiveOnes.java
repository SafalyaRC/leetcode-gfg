// Max consecutive ones in an array (LC-485): https://leetcode.com/problems/max-consecutive-ones/description/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxOnes = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
                if (count > maxOnes) {
                    maxOnes = count;
                }
            } else {
                count = 0;
            }
        }
        return maxOnes;
    }
}