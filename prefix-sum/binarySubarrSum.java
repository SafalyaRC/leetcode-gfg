// LC-930: https://leetcode.com/problems/binary-subarrays-with-sum/description/

public class binarySubarrSum {
    // we use the sliding window pattern: f(nums,goal)-f(nums,goal-1) template as it ask for an exact "goal" sum
    public int numSubarraysWithSum(int[] nums, int goal) {
        return lessEqualsGoal(nums, goal) - lessEqualsGoal(nums, goal - 1);
    }

    public int lessEqualsGoal(int nums[], int goal) {
        if (goal < 0)
            return 0;
        int l = 0, r = 0;
        int sum = 0, count = 0;

        while (r < nums.length) {
            sum += nums[r];
            while (sum > goal) {
                sum -= nums[l];
                l++;
            }
            if (sum <= goal)
                count += (r - l + 1);
            r++;
        }
        return count;
    }
}
