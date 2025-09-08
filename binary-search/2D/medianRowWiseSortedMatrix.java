// link: https://www.naukri.com/code360/problems/median-of-a-row-wise-sorted-matrix_1115473?leftPanelTabValue=PROBLEM

// brute-force:
import java.util.Collections;
import java.util.ArrayList;

public final class Solution {
    public static int findMedian(int matrix[][], int m, int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int row[] : matrix) {
            for (int num : row) {
                arr.add(num);
            }
        }
        Collections.sort(arr);
        return arr.get(((m * n) - 1) / 2);
    }
}

// optimal:

public final class Solution {
    static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                // look for a smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    static int countSmallEqual(int[][] matrix, int m, int n, int x) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += upperBound(matrix[i], x, n);
        }
        return cnt;
    }

    public static int findMedian(int matrix[][], int m, int n) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        // point low and high to right elements
        for (int i = 0; i < m; i++) {
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][n - 1]);
        }

        int req = (n * m) / 2;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int smallEqual = countSmallEqual(matrix, m, n, mid);
            if (smallEqual <= req) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return low;
    }
}