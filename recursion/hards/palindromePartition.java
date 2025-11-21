// LC-131: https://leetcode.com/problems/palindrome-partitioning/description/

import java.util.ArrayList;
import java.util.List;

public class palindromePartition {
    public boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else
                return false;
        }
        return true;
    }

    public void backtrack(String s, List<List<String>> ans, List<String> curr, int i) {
        // base case (when all letters of string traversed):
        if (i == s.length()) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // loop through and generate all possible substrings:
        for (int ind = i; ind < s.length(); ind++) {
            // partition only when it's a palindromic:
            if (isPalindrome(s, i, ind)) {
                // recursive calls:
                curr.add(s.substring(i, ind + 1)); // add the palindromic substr to our current list
                backtrack(s, ans, curr, ind + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        backtrack(s, ans, new ArrayList<String>(), 0);
        return ans;
    }
}
