import java.util.Stack;

public class postfixToPrefix {
    static String postToPre(String post_exp) {
        // code here
        Stack<String> st = new Stack<>();
        for (char ch : post_exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else {
                String top1 = st.pop();
                String top2 = st.pop();
                st.push(ch + top2 + top1);
            }
        }
        return st.pop();
    }
}
