// LC-787: https://leetcode.com/problems/cheapest-flights-within-k-stops/description/

/* 
   Intuition: In a normal shortest path problem, once we reach a node with the minimum cost, we never need to revisit it.
   dist[node] = minimum cost to reach node. But here we have an additional constraint: At most k stops. So reaching the same node with different numbers of stops represents different states.

   Example:
   Path A:
   cost = 2
   stops = 2
   
   Path B:
   cost = 5
   stops = 1

   Normal Dijkstra would keep only Path A because: 2 < 5
   However, Path B may be the only one that can still reach the destination within the stop limit.
   Therefore: Node alone is not sufficient state. We must consider: (node, stops). Hence standard Dijkstra's greedy property breaks.

   TC & SC: O(V+E)
 */

import java.util.*;
public class cheapestFlights {
  class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
      List<List<int[]>> adj = new ArrayList<>();
      for (int i = 0; i < n; i++)
        adj.add(new ArrayList<>());
      for (int flight[] : flights) {
        int u = flight[0], v = flight[1], wt = flight[2];
        adj.get(u).add(new int[] { v, wt });
      }

      int dist[] = new int[n];
      Arrays.fill(dist, (int) 1e9);

      Queue<int[]> q = new LinkedList<>(); // store [stops,node,cost]
      q.offer(new int[] { 0, src, 0 });
      dist[src] = 0;

      while (!q.isEmpty()) {
        int curr[] = q.poll();
        int stops = curr[0], node = curr[1], cost = curr[2];
        if (stops > k)
          continue; // Because k is the maximum number of stops allowed. In the queue, stops represents how many flights (edges) have been taken so far. Once a state exceeds the allowed limit, any path extending from it is automatically invalid. Implying that the current path has used too many stops hence no point in further exploring its neighbors

        for (int neighbor[] : adj.get(node)) {
          int adjNode = neighbor[0], wt = neighbor[1];
          if (cost + wt < dist[adjNode] && stops <= k) { // use cost+wt instead of dist[node] as queue stores the cost of the current path to reach a node by extending the path, however dist[node] stores global value which maybe updated by some completely different path
            dist[adjNode] = cost + wt;
            q.offer(new int[] { stops + 1, adjNode, dist[adjNode] });
          }
        }
      }
      return dist[dst] == (int) 1e9 ? -1 : dist[dst]; // if destination not reachable/reachable at <=k stops return -1
    }
  }
}
