// LC-31: https://leetcode.com/problems/next-permutation/description/

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

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int breakPoint = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                breakPoint = i;
                break;
            }
        }

        if (breakPoint == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        for (int i = n - 1; i > breakPoint; i--) {
            if (nums[i] > nums[breakPoint]) {
                int temp = nums[i];
                nums[i] = nums[breakPoint];
                nums[breakPoint] = temp;
                break;
            }
        }
        reverse(nums, breakPoint + 1, n - 1);
    }
}
