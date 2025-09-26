import java.util.Stack;

public class infixToPrefix {
    public int priority(char ch) {
        if (ch == '^')
            return 3;
        else if (ch == '/' || ch == '*')
            return 2;
        else if (ch == '+' || ch == '-')
            return 1;
        else
            return -1;
    }

    public String infixToPrefixx(String s) {
        // code here
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        char[] arr = sb.toString().toCharArray();
        // when reversing, must also take care of reversing the parentheses with it's opposite one
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(')
                arr[i] = ')';
            else if (arr[i] == ')')
                arr[i] = '(';
        }
        String reversed = new String(arr);

        for (char ch : reversed.toCharArray()) {
            if (Character.isLetterOrDigit(ch))
                ans.append(ch);
            else if (ch == '(')
                st.push(ch);
            else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(')
                    ans.append(st.pop());
                st.pop();
            } else {
                if (ch == '^') {
                    // two ^ cannot be together
                    while (!st.isEmpty() && priority(ch) <= priority(st.peek())) {
                        ans.append(st.pop());
                    }
                } else {
                    while (!st.isEmpty() && priority(ch) < priority(st.peek())) {
                        ans.append(st.pop());
                    }
                }
                st.push(ch);
            }
        }
        while (!st.isEmpty())
            ans.append(st.pop());
        ans = ans.reverse();
        return ans.toString();
    }
}
