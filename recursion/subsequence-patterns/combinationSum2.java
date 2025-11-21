// LC-40: https://leetcode.com/problems/combination-sum-ii/

// brute-force:
import java.util.*;

class Solution {
    public void backtrack(int candidates[], Set<List<Integer>> ans, List<Integer> current, int i, int target) {
        if (i == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(current));
            }
            return;
        }

        // choice-1: pick
        if (target >= candidates[i]) {
            current.add(candidates[i]);
            backtrack(candidates, ans, current, i + 1, target - candidates[i]); // change: i++ as we cant pick same candidate multiple times
            current.remove(current.size() - 1);
        }

        // choice-2: dont pick
        backtrack(candidates, ans, current, i + 1, target);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> ans = new HashSet<>();
        backtrack(candidates, ans, new ArrayList<Integer>(), 0, target);
        List<List<Integer>> ansList = new ArrayList<>(ans);
        return ansList;
    }
}

// optimal:
public class combinationSum2 {
    public void backtrack(int candidates[], List<List<Integer>> ans, List<Integer> current, int i, int target) {
        // base case:
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // loop from current ith index till n:
        for (int ind = i; ind < candidates.length; ind++) {
            if (ind > i && candidates[ind] == candidates[ind - 1])
                continue;
            if (candidates[ind] > target)
                break; // optimization: early pruning

            // recursive calls:
            current.add(candidates[ind]);
            backtrack(candidates, ans, current, ind + 1, target - candidates[ind]);
            current.remove(current.size() - 1); // to revert to previous step while backtracking
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // as we need combination(s) in sorted manner and required in for-loop inner condition
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, ans, new ArrayList<Integer>(), 0, target);
        return ans;
    }
}
