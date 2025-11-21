// LC-78: https://leetcode.com/problems/subsets/description/

// TC, SC both: O(n*2^n)

import java.util.ArrayList;
import java.util.List;

public class allSubsets {
    // either two choices: include or not an element in subset leading to 2*n subsets
    public void backtrack(int nums[], List<List<Integer>> ans, List<Integer> current, int i) {
        // base case:
        if (i == nums.length) {
            ans.add(new ArrayList<>(current)); // add a copy not the original one
            return;
        }

        // choice-1: exclude current element
        backtrack(nums, ans, current, i + 1);

        // choice-2: include current element
        current.add(nums[i]);
        backtrack(nums, ans, current, i + 1);

        // backtrack and undo the inclusion (draw tree to understand)
        current.remove(current.size() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, ans, new ArrayList<Integer>(), 0);
        return ans;
    }
}
