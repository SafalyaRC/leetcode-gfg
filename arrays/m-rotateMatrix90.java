// LC-48: https://leetcode.com/problems/rotate-image/

//brute-force:
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int result[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }
}

// optimal:
