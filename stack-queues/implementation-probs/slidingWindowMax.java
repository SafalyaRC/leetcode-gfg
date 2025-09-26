// LC-239: https://leetcode.com/problems/sliding-window-maximum/description/

// brute-force(TLE):
import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int ans[] = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            ans[i] = max;
        }
        return ans;
    }
}

// optimal:
public class slidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int ans[] = new int[n - k + 1];
        int ind = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peek() == i - k) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i >= k - 1) // we can start having first ans
            {
                ans[ind++] = nums[dq.peek()];
            }
        }
        return ans;
    }
}
