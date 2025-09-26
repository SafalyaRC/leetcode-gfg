import java.util.Stack;

public class prefixToInfix {
    static String preToInfix(String pre_exp) {
        // code here
        Stack<String> st = new Stack<>();
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                st.push(String.valueOf(ch));
            } else {
                String top1 = st.pop();
                String top2 = st.pop();
                String converted = "(" + top1 + ch + top2 + ")";
                st.push(converted);
            }
        }
        return st.pop();
    }
}
