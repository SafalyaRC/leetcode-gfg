// LC-15: https://leetcode.com/problems/3sum/description/

// better:
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> ansSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> checkSet = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int target = -(nums[i] + nums[j]);
                if (checkSet.contains(target)) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(temp);
                    ansSet.add(temp);
                }
                checkSet.add(nums[j]);
            }
        }
        return new ArrayList<>(ansSet);
    }
}

// optimal:
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0)
                    j++;
                else if (sum > 0)
                    k--;
                else {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    while (j < k && nums[j - 1] == nums[j])
                        j++;
                    while (j < k && nums[k + 1] == nums[k])
                        k--;
                }
            }
        }
        return ans;
    }
}