// LC-84: https://leetcode.com/problems/largest-rectangle-in-histogram/description/

// brute-force:
import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int nse[] = new int[n];
        int pse[] = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                st.pop();
            }
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int largestArea = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            largestArea = Math.max(largestArea, (heights[i] * (nse[i] - pse[i] - 1)));
        }
        return largestArea;
    }
}

// optimal:
public class largestRectangle {
    public int largestRectangleArea(int[] heights) {
        // stack contains all pse indices
        // calculate a specific element's nse while popping

        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                int index = st.pop();
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(maxArea, ((nse - pse - 1) * heights[index]));
            }
            st.push(i);
        }

        // for remaining uncomputed heights:
        while (!st.isEmpty()) {
            int index = st.pop();
            int nse = n;
            int pse = st.isEmpty() ? -1 : st.peek();
            maxArea = Math.max(maxArea, ((nse - pse - 1) * heights[index]));
        }
        return maxArea;
    }
}
