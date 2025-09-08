// GFG: https://www.geeksforgeeks.org/problems/ceil-in-a-sorted-array/1

// User function Template for Java
class Solution {
    public int findCeil(int[] arr, int x) {
        // code here
        int n = arr.length;
        int low = 0, high = n - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ceil = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ceil;
    }
}
