// LC-1334: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/

/*
Intuition: We need to find, For every city:
    How many cities can be reached
    with distance ≤ distanceThreshold

Then return: City with minimum reachable cities. If tie: return larger city number.

Key Observation
- To know how many cities are reachable from a city:
- We need shortest distance from that city to every other city.
- This immediately suggests: Single Source Shortest Path
- Since, All edge weights are positive, we can use Dijkstra 

Strategy
- Run Dijkstra:
from city 0
from city 1
from city 2
...
from city n-1
- For each source city:
Find shortest distances to all cities.
Count cities whose distance is:
≤ distanceThreshold
Store the count.
- Finally: Pick minimum count and if tie: pick larger city index.

Algorithm
- Step 1: Build adjacency list.
- Step 2: For every city: Run Dijkstra and get:dist[]
- Step 3: Count dist[j] <= distanceThreshold excluding itself.
- Step 4: Maintain: minReachableCities and answerCity, Update: if(count <= minCount). Notice: <= not < as This automatically handles, Tie -> larger city index, because we're traversing cities in increasing order.

TC: O(V × E log V) & SC: O(V+E)
 */

import java.util.*;
public class neighborThresholdDistance {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new int[] { v, wt });
            adj.get(v).add(new int[] { u, wt });
        }

        int minCount = Integer.MAX_VALUE;
        int ans = -1;

        for (int src = 0; src < n; src++) {
            int dist[] = getDijkstra(adj, src, n);
            int count = 0;

            for (int city = 0; city < n; city++) {
                if (city != src && dist[city] <= distanceThreshold)
                    count++;
            }

            if (count <= minCount) {
                minCount = count;
                ans = src;
            }
        }
        return ans;
    }

    private int[] getDijkstra(List<List<int[]>> adj, int src, int n) {
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, src });
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int curr[] = pq.poll();
            int cost = curr[0], node = curr[1];

            if (cost > dist[node])
                continue;

            for (int neighbor[] : adj.get(node)) {
                int adjNode = neighbor[0], wt = neighbor[1];
                if (cost + wt < dist[adjNode]) {
                    dist[adjNode] = cost + wt;
                    pq.offer(new int[] { dist[adjNode], adjNode });
                }
            }
        }
        return dist;
    }
}
