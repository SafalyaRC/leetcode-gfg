// LC-493: https://leetcode.com/problems/reverse-pairs/

// brute-force (TLE):
class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((long) nums[i] > (long) 2 * nums[j])
                    count++;
            }
        }
        return count;
    }
}

// optimal (merge sort):
class Solution {
    public void merge(int nums[], int low, int mid, int high) {
        int count = 0;
        int left = low, right = mid + 1;
        List<Integer> temp = new ArrayList<>();

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right])
                temp.add(nums[left++]);
            else
                temp.add(nums[right++]);
        }

        while (left <= mid)
            temp.add(nums[left++]);
        while (right <= high)
            temp.add(nums[right++]);

        for (int i = low; i <= high; i++)
            nums[i] = temp.get(i - low);
    }

    public int countReversePairs(int nums[], int low, int mid, int high) {
        int right = mid + 1, left = low;
        int count = 0;

        while (left <= mid) {
            while (right <= high && (long) nums[left] > (long) 2 * nums[right])
                right++;
            count += right - (mid + 1);
            left++;
        }
        return count;
    }

    public int mergeSort(int nums[], int low, int high) {
        int count = 0;
        int mid = (low + high) / 2;

        if (low >= high)
            return count;
        count += mergeSort(nums, low, mid);
        count += mergeSort(nums, mid + 1, high);
        count += countReversePairs(nums, low, mid, high);
        merge(nums, low, mid, high);

        return count;
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }
}