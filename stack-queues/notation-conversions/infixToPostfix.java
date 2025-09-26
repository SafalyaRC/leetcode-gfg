
// TC: O(2n), SC: O(n)
import java.util.Stack;

class infixToPostfix {
    public static int priority(char ch) {
        if (ch == '^')
            return 3;
        else if (ch == '/' || ch == '*')
            return 2;
        else if (ch == '+' || ch == '-')
            return 1;
        else
            return -1;
    }

    public static String infixToPostfixx(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                ans.append(ch);
            } else if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    ans.append(st.pop());
                }
                st.pop(); // pop '('
            } else {
                while (!st.isEmpty() &&
                        ((priority(ch) < priority(st.peek())) ||
                                (priority(ch) == priority(st.peek()) && ch != '^'))) {
                    ans.append(st.pop());
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()) {
            ans.append(st.pop());
        }
        return ans.toString();
    }
}