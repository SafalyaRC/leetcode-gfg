// GFG: https://www.geeksforgeeks.org/problems/reverse-a-stack/1

// iterative approach:
import java.util.Stack;

class Solution {
    public static void reverseStack(Stack<Integer> st) {
        Stack<Integer> temp = new Stack<>();
        while (!st.isEmpty()) {
            temp.push(st.pop());
        }
        while (!temp.isEmpty()) {
            st.push(temp.pop());
        }
    }
}

// recursive optimal:
public class reverseStack {
    public static void helper(Stack<Integer> st, int temp) {
        // base case:
        if (st.isEmpty()) {
            st.push(temp);
            return;
        }

        int top = st.pop();
        helper(st, temp);
        st.push(top);
    }

    public static void reverseStack(Stack<Integer> st) {
        // base case:
        if (st.isEmpty())
            return;

        int temp = st.pop();
        reverseStack(st);
        helper(st, temp);
    }
}
