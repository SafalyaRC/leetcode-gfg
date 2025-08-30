// LC-18: https://leetcode.com/problems/4sum/description/

// better:
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> ansSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Long> checkSet = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long fourth = (long) target - ((long) nums[i] + (long) nums[j] + (long) nums[k]);
                    if (checkSet.contains(fourth)) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int) fourth);
                        Collections.sort(temp);
                        ansSet.add(temp);
                    }
                    checkSet.add((long) nums[k]);
                }
            }
        }
        return new ArrayList<>(ansSet);
    }
}

// optimal:
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i - 1] == nums[i])
                continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j])
                    continue;

                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[l];
                    if (sum < (long) target)
                        k++;
                    else if (sum > (long) target)
                        l--;
                    else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;

                        while (k < l && nums[k - 1] == nums[k])
                            k++;
                        while (k < l && nums[l + 1] == nums[l])
                            l--;
                    }
                }
            }
        }
        return ans;
    }
}