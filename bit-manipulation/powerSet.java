// LC-78: https://leetcode.com/problems/subsets/description/

/*
The intuition is to treat each subset as a binary decision pattern. For an array of size n, every element has two choices: include or exclude. This naturally gives 2^n possible subsets. Instead of generating subsets recursively, we map each subset to a number from 0 → 2^n − 1, where the binary representation of the number encodes inclusion decisions.

For any number i, its j-th bit tells whether nums[j] belongs to that subset. If the bit is 1, include the element; if 0, skip it. By iterating through all numbers in this range and decoding their bit patterns, we systematically enumerate every subset exactly once. Conceptually, this converts a combinatorial generation problem into a bitmask enumeration problem, leveraging the binary structure to model inclusion–exclusion choices efficiently.

TC & SC: O(n*2^n)
*/

import java.util.*;
public class powerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // same as 2^n

        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0)
                    subset.add(nums[j]);
            }
            ans.add(subset);
        }
        return ans;
    }
}
