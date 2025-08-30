// check if array is sorted and rotated (LC-1752) :https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/

/*
 * Given an array nums, return true if the array was originally sorted in
 * non-decreasing order, then rotated some number of positions (including zero).Otherwise, return false. There may be duplicates in the original   array.
  Input: nums = [3,4,5,1,2] Output: true
  Explanation: [1,2,3,4,5] is the original sorted array. You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].
 */

// brute force: first rotate the array then check if its sorted or not
class Solution {
    public void reverse(int nums[], int left, int right) {
        while (left <= right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public boolean check(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i <= n - 2; i++) {
            if (nums[i] > nums[i + 1])
                k = i + 1;
        }

        k = k % n;
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        reverse(nums, 0, n - 1);

        for (int i = 0; i <= n - 2; i++) {
            if (nums[i] > nums[i + 1])
                return false;
        }
        return true;
    }
}

// optimal: count the number of times, an element the pattern of sorting breaks,
// if its once or zero, it's rotated and sorted, if more than once, not rotated
// and sorted
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n])
                count++;
        }
        return count <= 1;
    }
}