// LC-136: https://leetcode.com/problems/single-number/description/

// optimal-I:
class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor = xor ^ nums[i];
        }
        return xor;
    }
}

// optimal-II:
class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        return 0;
    }
}