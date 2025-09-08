// GFG: https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1

// brute-force:
class Solution {
    int countFreq(int[] arr, int target) {
        // code here
        int count = 0;
        for (int num : arr) {
            if (num == target)
                count++;
        }
        return count;
    }
}

// optimal:
class Solution {
    int countFreq(int[] arr, int target) {
        // code here
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = 0;
        int first = -1, last = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        low = 0;
        high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                last = mid;
                low = mid + 1;
            } else if (arr[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        if (first == -1 || last == -1)
            return 0;
        return (last - first) + 1;
    }
}
