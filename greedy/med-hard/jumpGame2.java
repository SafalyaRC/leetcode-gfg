// LC-45: https://leetcode.com/problems/jump-game-ii/description/

// core intuition: We greedily expand the reachable range level by level; each jump commits only after exhausting all positions reachable with the current jump, ensuring the minimum number of jumps

/*
 We observe that from any position, we are allowed to jump multiple steps forward.
 Instead of deciding where to jump immediately, we first explore all positions reachable with the current jump and then greedily choose the option that allows us to go farthest next.

 1. Maintain a window of indices that are reachable using the current number of jumps.
 2. While traversing this window, continuously compute the farthest index we can reach with one more jump.
 3. Once we reach the end of the current window:
 4. We must take a jump.
 5. Expand the window to the farthest reachable index.
 6. Repeat this process until the last index becomes reachable.
*/

public class jumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int maxReach = 0; // farthest index reachable with one extra jump
        int windowEnd = 0; // end of the current reachable window (current jump range)
        int jumps = 0; // total jumps taken

        for (int i = 0; i < n; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);

            if (i == windowEnd) {
                windowEnd = maxReach;
                jumps++;

                if (maxReach >= n - 1)
                    return jumps; // reached our destination
            }
        }
        return jumps;
    }
}
