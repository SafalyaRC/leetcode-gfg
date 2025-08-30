// removing duplicates from array (LC-26): https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
// test case: nums[1,1,2]--->nums[1,2,_]

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}