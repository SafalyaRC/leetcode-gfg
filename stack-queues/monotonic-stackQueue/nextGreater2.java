// LC-496: https://leetcode.com/problems/next-greater-element-i/

// brute-force:
import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> arr = new ArrayList<>();
        boolean added = false;
        for (int i = 0; i < n; i++) {
            added = false;
            for (int j = (i + 1) % n; j != i; j = (j + 1) % n) {
                if (nums[j] > nums[i]) {
                    arr.add(nums[j]);
                    added = true;
                    break;
                }
            }
            if (!added)
                arr.add(-1);
        }
        return arr.stream().mapToInt(Integer::intValue).toArray();
    }
}

// optimal:
class nextGreater2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int ans[] = new int[n];

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }
            if (i < n) {
                ans[i] = (st.isEmpty()) ? -1 : st.peek();
            }
            st.push(nums[i % n]);
        }
        return ans;
    }
}