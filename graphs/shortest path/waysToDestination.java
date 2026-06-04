// LC-1976: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

/*
Intuition: This is a Dijkstra variation. Normally Dijkstra answers: What is the shortest distance from source to every node?
This problem asks:What is the shortest distance? AND How many different shortest paths achieve that distance?
So besides storing: dist[node], we also store ways[node], where ways[node] = number of shortest paths to reach node

* When relaxing an edge: node ----wt----> adjNode
Let: newDist = dist[node] + wt

- Case 1: Found a shorter path: newDist < dist[adjNode]
Example: Current shortest distance to adjNode = 10, New distance found = 7
Then:
dist[adjNode] = 7
ways[adjNode] = ways[node]
Why? Because all previously known paths are no longer shortest. The only shortest paths now are those coming through node.

- Case 2: Found another shortest path: newDist == dist[adjNode]
Example: Existing shortest distance = 7, New distance found = 7
Then: ways[adjNode] += ways[node]
Why? Because we've discovered another route that reaches the same node with the same minimum distance. So we add its contribution.

- Example
0
|\
1 1
|  \
1   1
|    \
2 ---> 3
Shortest distance to node 3: 2
Paths: 0 → 1 → 3 & 0 → 2 → 3
Therefore: ways[3] = 2

TC: O(E log V) & SC: O(V+E)
 */


import java.util.*;
public class waysToDestination {
    public int countPaths(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int road[] : roads) {
            int u = road[0], v = road[1], wt = road[2];
            adj.get(u).add(new int[] { v, wt });
            adj.get(v).add(new int[] { u, wt });
        }

        long dist[] = new long[n];
        long ways[] = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0])); // stores [dist,node] sorted by distance ascending order
        pq.offer(new long[] { 0, 0 });
        dist[0] = 0;
        ways[0] = 1; // can reach source in 1 way initially
        long mod = 1000000007;

        while (!pq.isEmpty()) {
            long curr[] = pq.poll();
            long cost = curr[0];
            int node = (int) curr[1];

            for (int neighbor[] : adj.get(node)) {
                int adjNode = neighbor[0], wt = neighbor[1];
                if (cost + wt < dist[adjNode]) {
                    dist[adjNode] = cost + wt;
                    pq.offer(new long[] { dist[adjNode], adjNode });
                    ways[adjNode] = ways[node]; // since distance was INF or bigger, and we reached it in minimal cost right now, it takes the number of ways to reach the adjacent node, same as it's parent node as it come throught its parent's path
                } else if (cost + wt == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod; // means that since the dist[adjNode] is already minimum means there were way(s) previously to reach adjNode along with the current way to reach the adjNode
                }
            }
        }
        return (int) (ways[n - 1] % mod);
    }
}