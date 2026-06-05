// GFG: https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

/*
1. What is Bellman-Ford? Bellman-Ford is a single-source shortest path algorithm that finds the shortest distance from a source node to all other nodes. 
Unlike Dijkstra:
Dijkstra
✓ Positive weights
✗ Negative weights
Bellman-Ford
✓ Positive weights
✓ Negative weights
✓ Detects negative cycles
It works by repeatedly relaxing all edges.

Relaxation means: 
if(dist[u] + wt < dist[v]) {
    dist[v] = dist[u] + wt;
}
which asks: "Can I reach v more cheaply through u?"

2. Why does it run exactly (V - 1) times?
Key Observation In a graph with V vertices: A shortest path can contain at most (V - 1) edges.
Why? Because if a path contains: V edges, then some vertex must repeat (Pigeonhole Principle). That creates a cycle.
And: Shortest paths never need cycles.

Intuition
Suppose:
0 → 1 → 2 → 3
Source:
0
After 1st iteration
Only nodes reachable using: 1 edge, get correct distances. 0 → 1
After 2nd iteration. Nodes reachable using: 2 edges become correct.
0 → 1 → 2
After 3rd iteration
Nodes reachable using: 3 edges become correct.
0 → 1 → 2 → 3
Therefore:
After i iterations,
all shortest paths using ≤ i edges are correct.

Since a shortest path can use at most: V - 1 edges
we need exactly:
V - 1 iterations.


3. How does Bellman-Ford detect a Negative Cycle?
After V - 1 iterations all shortest distances should already be finalized. No further improvement should be possible.
Suppose after completing: V - 1 passes, we do one more pass. 
If we can still relax an edge: dist[u] + wt < dist[v]
then something is wrong.

Why? Because shortest paths should already be finished. The only way distances keep decreasing forever is if we're repeatedly going around a cycle whose total weight is negative.

Example
1 → 2 (1)
2 → 3 (-2)
3 → 1 (-2)

Cycle sum:
1 + (-2) + (-2) = -3
Every loop around the cycle: distance decreases by 3

Example:
0
-3
-6
-9
-12
...
Distances can always be improved.
Hence: Relaxation possible after V-1 iterations, ⇒ Negative Cycle Exists

TC: O(V*E) & SC: O(V)
 */

import java.util.*;
public class bellmanFord {
    public int[] bellmanFordAlgorithm(int V, int[][] edges, int src) {
        int dist[] = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;

        // relax all V-1 edges
        for (int i = 0; i < V; i++) {
            for (int edge[] : edges) {
                int u = edge[0], v = edge[1], wt = edge[2];
                if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) // relax edges if possible
                    dist[v] = dist[u] + wt;
            }
        }

        // check for -ve cycles
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v])
                return new int[] { -1 };  // when a -ve cycle is found
        }
        return dist;
    }
}