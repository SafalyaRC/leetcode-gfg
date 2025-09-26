// GFG: https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1

// brute-force:
import java.util.ArrayList;
import java.util.Stack;

class Solution {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        boolean added = false;
        for (int i = 0; i < n; i++) {
            added = false;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    ans.add(arr[j]);
                    added = true;
                    break;
                }
            }
            if (!added)
                ans.add(-1);
        }
        return ans;
    }
}

public class nextSmaller {
    static int[] nextSmallerEle(int[] arr) {
        // code here
        int n = arr.length;
        int ans[] = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[i] <= st.peek()) {
                st.pop();
            }
            ans[i] = (st.isEmpty()) ? -1 : st.peek();
            st.push(arr[i]);
        }
        return ans;
    }
}
