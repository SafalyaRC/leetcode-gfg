// LC-229: https://leetcode.com/problems/majority-element-ii/description/

// better:
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 3) {
                temp.add(entry.getKey());
            }
        }
        return temp;
    }
}

// optimal:
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        int count1 = 0, count2 = 0;
        int candidate1 = -1, candidate2 = -1;

        for (int num : nums) {
            if (count1 == 0 && num != candidate2) {
                count1 = 1;
                candidate1 = num;
            } else if (count2 == 0 && num != candidate1) {
                count2 = 1;
                candidate2 = num;
            } else if (num == candidate1)
                count1++;
            else if (num == candidate2)
                count2++;
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1)
                count1++;
            else if (num == candidate2)
                count2++;
        }
        if (count1 > n / 3)
            ans.add(candidate1);
        if (count2 > n / 3)
            ans.add(candidate2);

        return ans;
    }
}
