// GFG: https://www.geeksforgeeks.org/problems/generate-all-binary-strings/1

// recursive solution:

// TC: (n*2^n) SC: O(n)

import java.util.*;
class generateBinarySubstrings {
    public ArrayList<String> binstr(int n) {
        ArrayList<String> ans = new ArrayList<>();
        helper(ans, "", n);
        return ans;
    }

    public void helper(ArrayList<String> ans, String current, int n) {
        // base case:
        if (current.length() == n) {
            ans.add(current);
            return;
        }
        // recursive calls:
        helper(ans, current + "0", n);
        helper(ans, current + "1", n);
    }
}