// Two Sum (LC-1): https://leetcode.com/problems/two-sum/description/

// brute-force:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        int result[] = new int[2];
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum = nums[i] + nums[j];
                if (sum == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }
}

// optimal hashmap:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
}
