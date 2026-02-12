// GFG: https://www.geeksforgeeks.org/problems/maximum-sum-combination/1

/*
Intuition (high level):
- Sort both arrays in ascending order.
- The maximum sum always comes from the last elements of both arrays.
- Use a max heap to always extract the next best sum.
- From a pair (i, j), the next possible candidates are: (i-1, j) & (i, j-1)
- Use a set to ensure each index pair (i, j) is used at most once.
- Repeat until you extract k sums.
- This guarantees: No duplicate index pairs
Time complexity: O(k log k) & Space complexity: O(k)
*/

import java.util.*;
public class maxSumCombinations {
    class Pair {
        int sum, i, j;

        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i; // pointer for a[]
            this.j = j; // pointer for b[]
        }
    }

    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);

        // create a max heap based upon the values of sum
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((x, y) -> y.sum - x.sum);

        // to avoid using the same (i,j) again we use a set
        Set<String> visited = new HashSet<>();

        // start from the largest elements first
        maxHeap.offer(new Pair(a[n - 1] + b[n - 1], n - 1, n - 1));
        visited.add((n - 1) + "," + (n - 1));

        ArrayList<Integer> ans = new ArrayList<>();

        // continue till we have performed 'k' operations
        while (k-- > 0 && !maxHeap.isEmpty()) {
            Pair curr = maxHeap.poll();
            ans.add(curr.sum);

            int i = curr.i, j = curr.j;

            // move left in a[]
            if (i - 1 >= 0) {
                String key = (i - 1) + "," + (j);
                if (!visited.contains(key)) {
                    maxHeap.offer(new Pair(a[i - 1] + b[j], i - 1, j));
                    visited.add(key);
                }
            }

            // move left in b[]
            if (j - 1 >= 0) {
                String key = (i) + "," + (j - 1);
                if (!visited.contains(key)) {
                    maxHeap.offer(new Pair(a[i] + b[j - 1], i, j - 1));
                    visited.add(key);
                }
            }
        }
        return ans;
    }
}
