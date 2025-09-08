
// brute-I:
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int max = Integer.MIN_VALUE;
        int m = mat.length, n = mat[0].length;
        int x = -1, y = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] > max) {
                    max = mat[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        return new int[] { x, y };
    }
}

// brute-II:
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int current = mat[i][j];
                int top = -1, bottom = -1, left = -1, right = -1;

                if (i > 0)
                    top = mat[i - 1][j];
                if (i < m - 1)
                    bottom = mat[i + 1][j];
                if (j > 0)
                    left = mat[i][j - 1];
                if (j < n - 1)
                    right = mat[i][j + 1];

                if ((current > top && current > bottom) && (current > left && current > right))
                    return new int[] { i, j };
            }
        }
        return new int[] { -1, -1 };
    }
}

// optimal:
class Solution {
    public int maxCol(int mat[][], int mid, int m, int n) {
        int max = Integer.MIN_VALUE, pos = -1;
        for (int i = 0; i < m; i++) {
            if (mat[i][mid] > max) {
                max = mat[i][mid];
                pos = i;
            }
        }
        return pos;
    }

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int row = maxCol(mat, mid, m, n); // determine which row no. contains max of the "mid" col

            // finding max of a column already means that element is greater than top,bottom
            int left = -1, right = -1;

            if (mid > 0)
                left = mat[row][mid - 1];
            if (mid < n - 1)
                right = mat[row][mid + 1];

            if (mat[row][mid] > left && mat[row][mid] > right)
                return new int[] { row, mid };
            else if (mat[row][mid] < left)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return new int[] { -1, -1 };
    }
}