// GFG: https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1

/*
Intuition: 
- Think of every number from 0 to 999 as a node in a graph.
- From a current number x, we can move to: (x * arr[i]) % 1000 for every element in arr.
Example: 
arr = [2, 5]
3
├──> (3*2)%1000 = 6
└──> (3*5)%1000 = 15
- Each multiplication is one operation. So every edge has weight: 1
- The question becomes: Minimum operations to reach end from start?
- which is simply: Shortest path in an unweighted graph. And shortest path in an unweighted graph is found using BFS.
- After every operation: (number * arr[i]) % 1000, the result is always: 0 ≤ result ≤ 999
- Therefore there are only: 1000 possible states. This converts the problem into a graph of at most 1000 nodes.

TC: O(1000*length of arr[]) as there 1000 states and each tries all multiplications in arr[]  &  SC: O(1000) 
 */

import java.util.*;
public class multiplicationsToEnd {
    public int minSteps(int[] arr, int start, int end) {
        // note: no need of min heap as steps increment sequentially +1
        Queue<int[]> q = new LinkedList<>(); // stores [number (graph node), steps to reach it]
        q.offer(new int[] { start, 0 });

        // the number of nodes in the dist[] is 1000 as >=10^4 will need a mod of 10^5
        int dist[] = new int[1000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int mod = 1000;

        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int node = curr[0], steps = curr[1];

            if (node == end)
                return steps;

            for (int num : arr) {
                int adjNode = (int) (((long) node * num) % mod);
                if (steps + 1 < dist[adjNode]) {
                    dist[adjNode] = steps + 1;
                    q.offer(new int[] { adjNode, dist[adjNode] });
                }
            }
        }
        return -1;
    }
}
