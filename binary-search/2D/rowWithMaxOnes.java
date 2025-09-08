// LC-2643: https://leetcode.com/problems/row-with-maximum-ones/description/

// brute-force:
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int maxCount = Integer.MIN_VALUE, count = 0, position = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                position = i;
            }
            count = 0;
        }
        return new int[] { position, maxCount };
    }
}

// optimal:
class Solution {
    public int lowerBound(int mat[], int n, int target) {
        int low = 0, high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mat[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }

    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int maxCount = Integer.MIN_VALUE, position = -1;

        for (int i = 0; i < m; i++) {
            Arrays.sort(mat[i]);
            int count = n - lowerBound(mat[i], n, 1);
            if (count > maxCount) {
                maxCount = count;
                position = i;
            }
        }
        return new int[] { position, maxCount };
    }
}