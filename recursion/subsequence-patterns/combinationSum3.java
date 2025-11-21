// LC-216: https://leetcode.com/problems/combination-sum-iii/

import java.util.*;

public class combinationSum3 {
    public void backtrack(List<List<Integer>> ans, List<Integer> current, int lastIndex, int k, int sum) {
        // base case:
        if (sum == 0 && current.size() == k) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // condition to prune early:
        if (sum <= 0 || current.size() > k)
            return;

        // iterate to generate the recursive tree for elements 1->9:
        for (int i = lastIndex; i <= 9; i++) {
            // pick the number for combination:
            if (i <= sum) {
                current.add(i);
                backtrack(ans, current, i + 1, k, sum - i);
                current.remove(current.size() - 1); // revert to previous stage
            } else {
                break; // dont pick the number for combination as it will make our sum more than k
            }
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<Integer>(), 1, k, n);
        return ans;
    }
}
