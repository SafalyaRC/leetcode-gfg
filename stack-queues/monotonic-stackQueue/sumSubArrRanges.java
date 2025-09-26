// LC-2104: https://leetcode.com/problems/sum-of-subarray-ranges/description/

// brute-force:
import java.util.Stack;

class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum += (max - min);
            }
        }
        return sum;
    }
}

// optimal:
public class sumSubArrRanges {
    public long sumSubArrMin(int nums[]) {
        int n = nums.length;
        int pse[] = new int[n];
        int nse[] = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i])
                st.pop();
            pse[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i])
                st.pop();
            nse[i] = (st.isEmpty()) ? n : st.peek();
            st.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            sum += ((right * left) * nums[i]);
        }
        return sum;
    }

    public long sumSubArrMax(int nums[]) {
        int n = nums.length;
        int pge[] = new int[n];
        int nge[] = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            nge[i] = (st.isEmpty()) ? n : st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            pge[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - pge[i];
            long right = nge[i] - i;
            sum += ((left * right) * nums[i]);
        }
        return sum;
    }

    public long subArrayRanges(int[] nums) {
        return sumSubArrMax(nums) - sumSubArrMin(nums);
    }
}
