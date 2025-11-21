// LC-22: https://leetcode.com/problems/generate-parentheses/

import java.util.ArrayList;
import java.util.List;

public class generateParentheses {
    public void helper(List<String> ans, String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            ans.add(current);
            return;
        }
        // can add open parentheses only if it's count<n
        if (open < n)
            helper(ans, current + "(", open + 1, close, n);
        // can add close parentheses only if count of open>close, otherwise invalid [for eg: "()", adding ")" makes it invalid]
        if (open > close)
            helper(ans, current + ")", open, close + 1, n);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(ans, "", 0, 0, n);
        return ans;
    }
}
