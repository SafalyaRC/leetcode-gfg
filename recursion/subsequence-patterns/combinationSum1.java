// LC-39: https://leetcode.com/problems/combination-sum/

import java.util.*;

class combinationSum1 {
    public void backtrack(int candidates[], List<List<Integer>> ans, List<Integer> current, int i, int target) {
        if (i == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(current));
            }
            return;
        }

        // choice-1: pick a candidate
        if (target >= candidates[i]) {
            current.add(candidates[i]);
            backtrack(candidates, ans, current, i, target - candidates[i]); // no i++ as we pick same candidate multiple time
            current.remove(current.size() - 1); // for backtracking to previous stage
        }

        // choice-2: dont pick a candidate
        backtrack(candidates, ans, current, i + 1, target);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, ans, new ArrayList<Integer>(), 0, target);
        return ans;
    }
}