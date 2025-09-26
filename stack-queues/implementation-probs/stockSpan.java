// LC-901: https://leetcode.com/problems/online-stock-span/

// brute-force:
import java.util.ArrayList;
import java.util.Stack;

class stockSpan {
    ArrayList<Integer> arr;

    public stockSpan() {
        arr = new ArrayList<>();
    }

    public int next(int price) {
        arr.add(price);
        int count = 1;
        for (int i = arr.size() - 2; i >= 0; i--) {
            if (arr.get(i) <= price)
                count++;
            else
                break;
        }
        return count;
    }
}

// optimal:
class StockSpanner {
    Stack<int[]> st;
    int ind;

    public StockSpanner() {
        st = new Stack<>();
        ind = -1;
    }

    public int next(int price) {
        ind++;
        while (!st.isEmpty() && st.peek()[0] <= price) {
            st.pop();
        }
        int ans = ind - (st.isEmpty() ? -1 : (st.peek()[1]));
        st.push(new int[] { price, ind });
        return ans;
    }
}
