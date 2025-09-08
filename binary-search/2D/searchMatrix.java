// LC-74: https://leetcode.com/problems/search-a-2d-matrix/description/

// brute:
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int num : row) {
                if (num == target)
                    return true;
            }
        }
        return false;
    }
}

// better:
class Solution {
    public boolean binarySearch(int matrix[], int target) {
        int low = 0, high = matrix.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (matrix[mid] == target)
                return true;
            else if (matrix[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][n - 1]) {
                return binarySearch(matrix[i], target);
            }
        }
        return false;
    }
}

// optimal:
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = (m * n) - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target)
                high = mid - 1;
            else
                low = mid = 1;
        }
        return false;
    }
}