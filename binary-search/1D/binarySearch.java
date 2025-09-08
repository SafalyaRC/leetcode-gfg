// iterative implementation of binary search:
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            else if (target > nums[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}

// recursive implementation of binary search:
class Solution {
    public int binarySearch(int nums[], int low, int high, int target) {
        int low = 0, high = nums.length - 1;

        if (low > high)
            return -1;

        int mid = (low + high) / 2;
        if (target == nums[mid])
            return mid;
        else if (target > nums[mid])
            return binarySearch(nums, mid + 1, high, target);
        else
            return binarySearch(nums, low, mid - 1, target);

    }
}