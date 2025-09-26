// LC-907: https://leetcode.com/problems/sum-of-subarray-minimums/

// brute-force (TLE):
import java.util.Stack;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        int n = arr.length;
        double mod = 1e9 + 7;

        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);
                sum = (sum + min) % (int) mod;
            }
        }
        return (int) sum;
    }
}

// optimal:
public class sumSubArrMin {
    class Solution {
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            int nse[] = new int[n];
            int pse[] = new int[n];

            Stack<Integer> nseStack = new Stack<>();
            Stack<Integer> pseStack = new Stack<>();

            // precompute nse for all elements
            for (int i = n - 1; i >= 0; i--) {
                while (!nseStack.isEmpty() && arr[nseStack.peek()] >= arr[i])
                    nseStack.pop();
                nse[i] = (nseStack.isEmpty()) ? n : nseStack.peek();
                nseStack.push(i);
            }

            // precompute pse for all elements
            for (int i = 0; i < n; i++) {
                while (!pseStack.isEmpty() && arr[pseStack.peek()] > arr[i])
                    pseStack.pop();
                pse[i] = (pseStack.isEmpty()) ? -1 : pseStack.peek();
                pseStack.push(i);
            }

            long sum = 0;
            long mod = (long) 1e9 + 7;
            for (int i = 0; i < n; i++) {
                long left = i - pse[i];
                long right = nse[i] - i;
                sum = (sum + (((left * right) % mod) * arr[i]) % mod) % mod;
            }
            return (int) (sum % mod);

        }
    }
}
