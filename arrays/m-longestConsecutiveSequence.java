// LC-128: https://leetcode.com/problems/longest-consecutive-sequence/description/

// brute-force:
class Solution {
    public boolean linearSearch(int nums[], int key) {
        for (int num : nums) {
            if (num == key)
                return true;
        }
        return false;
    }

    public int longestConsecutive(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;

        int curr = nums[0], count = 1;
        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            curr = nums[i];
            count = 1;

            while (linearSearch(nums, curr + 1)) {
                count++;
                curr += 1;
            }
            maxLen = Math.max(maxLen, count);
        }
        return maxLen;
    }
}

// better:
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        int count = 0, maxLen = 0;
        int lastElement = Integer.MIN_VALUE;

        Arrays.sort(nums);

        if (n == 0)
            return 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 == lastElement) {
                count++;
                lastElement = nums[i];
            } else if (nums[i] != lastElement) {
                count = 1;
                lastElement = nums[i];
            }
            maxLen = Math.max(maxLen, count);
        }
        return maxLen;
    }
}

// optimal set approach:
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int count = 1, maxLen = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();

        for (int num : nums)
            set.add(num);

        for (int element : set) {
            if (!set.contains(element - 1)) {
                count = 1;
                while (set.contains(element + 1)) {
                    count++;
                    element++;
                }
                maxLen = Math.max(maxLen, count);
            }
        }
        return maxLen;

    }
}