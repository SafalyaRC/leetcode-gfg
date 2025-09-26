// LC-402: https://leetcode.com/problems/remove-k-digits/

import java.util.Stack;

public class removeKDigits {
    public String removeKdigits(String num, int k) {
        if (k == num.length())
            return "0";
        int remove = k;
        Stack<Character> st = new Stack<>();
        for (char ch : num.toCharArray()) {
            while (!st.isEmpty() && remove > 0 && (st.peek() - '0') > (ch - '0')) {
                st.pop();
                remove--;
            }
            st.push(ch);
        }

        while (remove > 0 && !st.isEmpty()) {
            st.pop();
            remove--;
        }

        StringBuilder ans = new StringBuilder();
        for (char ch : st) {
            ans.append(ch);
        }
        while (ans.length() > 0 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }
}
