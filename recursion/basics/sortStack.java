// GFG: https://www.geeksforgeeks.org/problems/sort-a-stack/1

// iterative approach:
import java.util.*;

class Solution {
    public void sortStack(Stack<Integer> st) {
        // code here
        int n = st.size();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = st.pop();
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            st.push(arr[i]);
        }
    }
}

// recursive approach:
public class sortStack {
    public void insertStack(Stack<Integer> st, int temp) {
        // base case (no need to go deeper):
        if (st.isEmpty() || st.peek() <= temp) {
            st.push(temp);
            return;
        }
        int poppedValue = st.pop(); // pop the bigger value
        insertStack(st, temp); // insert the smaller value

        st.push(poppedValue); // make sure to push back the bigger value
    }

    public void sortStack(Stack<Integer> st) {
        // base case: stack is empty
        if (st.isEmpty())
            return;

        int temp = st.pop();
        sortStack(st);
        insertStack(st, temp);
    }
}
