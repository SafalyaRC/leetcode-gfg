// GFG: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1

// brute-force:

//Back-end complete function Template for Java

class Solution {
    public static int studentsAllocated(int arr[], int maxPages) {
        int studentCount = 1, pageSum = 0;
        for (int i = 0; i < arr.length; i++) {
            pageSum += arr[i];
            if (pageSum > maxPages) {
                studentCount++;
                pageSum = arr[i];
            }
        }
        return studentCount;
    }

    public static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k <= n) {
            int sum = 0, max = Integer.MIN_VALUE;
            for (int num : arr) {
                sum += num;
                max = Math.max(max, num);
            }

            for (int i = max; i <= sum; i++) {
                if (studentsAllocated(arr, i) <= k)
                    return i;
            }
            return max;
        }
        return -1;
    }
}

// optimal:

// Back-end complete function Template for Java

class Solution {
    public static int studentsAllocated(int arr[], int maxPages) {
        int studentCount = 1, pagesSum = 0;
        for (int i = 0; i < arr.length; i++) {
            pagesSum += arr[i];
            if (pagesSum > maxPages) {
                studentCount++;
                pagesSum = arr[i];
            }
        }
        return studentCount;
    }

    public static int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n)
            return -1;

        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : arr) {
            sum += num;
            max = Math.max(max, num);
        }

        int low = max, high = sum;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (studentsAllocated(arr, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}
