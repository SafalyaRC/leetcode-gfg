// Given an array nums of size n, return the majority element. The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array. (LC-169): https://leetcode.com/problems/majority-element/description/

// better:
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2)
                return entry.getKey();
        }
        return -1;
    }
}

// optimal (moore voting algorithm):
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, candidate = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (count == 0)
                candidate = nums[i];

            if (nums[i] == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }
}
