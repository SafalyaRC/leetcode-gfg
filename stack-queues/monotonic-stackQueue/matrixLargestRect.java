// LC-85: https://leetcode.com/problems/maximal-rectangle/description/

import java.util.Stack;

public class matrixLargestRect {
    public int maxRectangleArea(int heights[]) {
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!st.isEmpty() && heights[st.peek()] > heights[i]) {
                int index = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                maxArea = Math.max(maxArea, (i - pse - 1) * heights[index]);
            }
            st.push(i);
        }

        while (!st.isEmpty()) {
            int index = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();
            maxArea = Math.max(maxArea, (heights.length - pse - 1) * heights[index]);
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = (matrix[i][j] == '1') ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, maxRectangleArea(heights));
        }
        return maxArea;
    }
}
