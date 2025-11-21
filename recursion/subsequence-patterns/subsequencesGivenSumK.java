// given nums array, print all subsequences with given sum=K
// nums=[1,2,1] and k=2 then o/p: [ [1,1], [2] ]

import java.util.*;

class subsequencesGivenSumK {
    public static void main(String[] args) {
        int nums[] = { 1, 2, 1 };
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(subsequencesSumK(nums, 2));
        for (ArrayList<Integer> outer : list) {
            for (Integer num : outer) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> subsequencesSumK(int nums[], int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        helper(nums, k, ans, new ArrayList<Integer>(), 0, 0);
        return ans;
    }

    public static void helper(int nums[], int k, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> current, int sum,
            int i) {
        // base case:
        if (i == nums.length) {
            if (sum == k) {
                ans.add(new ArrayList<Integer>(current));
            }
            return;
        }
        // choice-1: dont choose nums[i]:
        helper(nums, k, ans, current, sum, i + 1);

        // choice-2: choose nums[i]:
        current.add(nums[i]);
        sum += nums[i];
        helper(nums, k, ans, current, sum, i + 1);
        sum -= nums[i];
        current.remove(current.size() - 1);
    }
}