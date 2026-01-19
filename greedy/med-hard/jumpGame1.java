// LC-55: https://leetcode.com/problems/jump-game/description/

/*
 Instead of trying all possible paths (which would be exponential), we can think: "How far can I possibly reach   from my current position?". The key is to maintain a variable maxReach that represents the farthest index we can get to based on the positions we've examined so far.

 Why It Works: At any index i, if we can reach i, then we can potentially reach all indices up to i + nums[i]. So we track the maximum reachable index as we iterate.
*/

class jumpGame1 {
    public boolean canJump(int[] nums) {
        int n=nums.length;
        int maxReach = 0; // farthest index we can reach
        for (int i = 0; i < n; i++) {
            if (i > maxReach)
                return false; // if we cant even reach the current index 'i' from the previous jumps, we wont obviously reach the last index, hence return false

            maxReach = Math.max(maxReach, i + nums[i]); // update the farthest index

            if (maxReach >= n - 1)
                return true; // if we can reach the last index already, return early
        }

        return true; // will reach here if the loop completes
    }
}

