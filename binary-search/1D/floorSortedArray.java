// GFG: https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1

class Solution {
    public int findFloor(int[] arr, int x) {
        // code here
        int n = arr.length;
        int low = 0, high = n - 1;
        int floor = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                floor = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return floor;
    }
}
