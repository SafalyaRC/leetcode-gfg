// DUTCH NATIONAL FLAG ALGORITHM: Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue (LC-75): https://leetcode.com/problems/sort-colors/description/

// better:
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                red++;
            else if (nums[i] == 1)
                white++;
            else
                blue++;
        }

        for (int i = 0; i < red; i++)
            nums[i] = 0;
        for (int i = red; i < red + white; i++)
            nums[i] = 1;
        for (int i = red + white; i < n; i++)
            nums[i] = 2;
    }
}

// optimal three pointer:
class Solution {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = nums.length - 1;

        while (white <= blue) {
            if (nums[white] == 0) {
                int temp = nums[white];
                nums[white] = nums[red];
                nums[red] = temp;
                red++;
                white++;
            } else if (nums[white] == 1) {
                white++;
            } else {
                int temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                blue--;
            }
        }
    }
}