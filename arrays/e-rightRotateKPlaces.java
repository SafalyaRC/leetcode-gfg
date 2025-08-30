// LC-189: Rotate Array https://leetcode.com/problems/rotate-array/description/

// brute-force: O(n) time and space
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int temp[] = new int[n];
        k = k % n;

        for (int i = 0; i < k; i++) {
            temp[i] = nums[n - k + i];
        }
        for (int i = 0; i < n - k; i++) {
            temp[i + k] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }
}

// optimal:
class Solution {
    public void reverse(int arr[], int left, int right) {
        int n = arr.length;
        while (left <= right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
}
