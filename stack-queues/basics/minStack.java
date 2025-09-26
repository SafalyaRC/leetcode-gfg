// LC-155 : https://leetcode.com/problems/min-stack/
import java.util.Stack;

public class minStack {
    Stack<Long> st;
    long min;

    public minStack() {
        st = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public void push(int val) {
        long v = val;
        if (st.isEmpty()) {
            min = v;
            st.push(v);
        } else {
            if (v < min) {
                st.push(2 * v - min); // store encoded value
                min = v;
            } else {
                st.push(v);
            }
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;
        long popped = st.pop();
        if (popped < min) {
            min = 2 * min - popped; // restore previous min
        }
    }

    public int top() {
        long top = st.peek();
        if (top < min)
            return (int) min;
        return (int) top;
    }

    public int getMin() {
        return (int) min;
    }
}
