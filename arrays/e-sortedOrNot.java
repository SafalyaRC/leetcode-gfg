class Solution {
    public boolean checkSorted(int nums[]) {
        for (int i = 0; i <= nums.length - 1; i--) {
            if (nums[i] > nums[i + 1])
                return false;
        }
        return true;
    }
}