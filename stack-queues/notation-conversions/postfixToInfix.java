import java.util.Stack;

public class postfixToInfix {
    static String postToInfix(String exp) {
        // code here
        Stack<String> st = new Stack<>();
        for (char ch : exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch))
                st.push(String.valueOf(ch));
            else {
                String top1 = st.pop();
                String top2 = st.pop();
                String converted = "(" + top2 + ch + top1 + ")";
                st.push(converted);
            }
        }
        return st.pop();
    }
}
