// LC-3211: https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/

// TC: O(φ^n), where φ is the golden ratio (~1.618).
// SC: O(n * Fibonacci(n)), dominated by the storage of all valid strings
import java.util.*;

public class generateBinaryStringsWithoutAdjacentZeroes {
    public void helper(List<String> ans, String current, int n) {
        // base case:
        if (current.length() == n) {
            ans.add(current);
            return;
        }
        // add 0s only when current string is empty or the last character isn't a zero
        if (current.length() == 0 || current.charAt(current.length() - 1) != '0') {
            helper(ans, current + "0", n);
        }
        // can always add a one:
        helper(ans, current + "1", n);
    }

    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();
        helper(ans, "", n);
        return ans;
    }
}
