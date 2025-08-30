// LC-1614: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/

class Solution {
    public int maxDepth(String s) {
        int ans = 0, parenthesesCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(')
                parenthesesCount++;
            if (ch == ')')
                parenthesesCount--;

            ans = Math.max(ans, parenthesesCount);
        }
        return ans;
    }
}