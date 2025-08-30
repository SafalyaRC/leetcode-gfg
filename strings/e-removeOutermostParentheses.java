// LC-1021: https://leetcode.com/problems/remove-outermost-parentheses/

// sol-1:
class Solution {
    public String removeOuterParentheses(String s) {
        String ans = "";
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')')
                count--;
            if (count > 0)
                ans += ch;
            if (ch == '(')
                count++;
        }
        return ans;
    }
}

// sol-2:
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int depth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (depth > 0) // note: depth>0 signifies that the parenthesis is not outermost, hence we
                               // append it
                    ans.append(ch);
                depth++;
            } else {
                depth--;
                if (depth > 0) // note: depth>0 signifies that the parenthesis is not outermost, hence we
                               // append it
                    ans.append(ch);
            }
        }
        return ans.toString();
    }
}