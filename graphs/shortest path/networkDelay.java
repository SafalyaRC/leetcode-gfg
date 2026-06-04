// LC-743: https://leetcode.com/problems/network-delay-time/description/

/*
We are sending a signal from node k.The signal spreads through the network along directed edges.
For every node, we need to know: What's the minimum time required for the signal to reach this node? This is exactly a single-source shortest path problem.

Example:
2 --1--> 1
|
1
v
3 --1--> 4

Starting from:
k = 2

Arrival times:
Node 2 = 0
Node 1 = 1
Node 3 = 1
Node 4 = 2
Since node 4 receives the signal last: Answer = max(0,1,1,2) = 2

TC: O(V+E log V) & SC: O(V+E)
 */

import java.util.*;
public class networkDelay {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>()); // graph is 1-indexed
        for (int time[] : times) {
            int u = time[0], v = time[1], wt = time[2];
            adj.get(u).add(new int[] { v, wt });
        }

        int dist[] = new int[n + 1]; // given nodes are in the range [1,n] i.e., 1-indexed
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // store [time(aka dist),node]- sorted by time
        dist[k] = 0;
        pq.offer(new int[] { 0, k });

        while (!pq.isEmpty()) {
            int curr[] = pq.poll();
            int time = curr[0], node = curr[1];
            if (time > dist[node])
                continue; // to ignore outdated (bigger) entries

            for (int neighbor[] : adj.get(node)) {
                int adjNode = neighbor[0], wt = neighbor[1];
                if (time + wt < dist[adjNode]) { // dist[node]+wt also works as we did a check just above
                    dist[adjNode] = time + wt;
                    pq.offer(new int[] { dist[adjNode], adjNode });
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++)
            ans = Math.max(ans, dist[i]);

        return ans == Integer.MAX_VALUE ? -1 : ans; // ans=INT_MAX signifies one of the nodes was unreachable as dist[unreachable_node]=INF meaning it wasnt explored
    }
}
