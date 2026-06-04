// GFG: https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1

/* Intuition: 
  - Problem Recognition: We need shortest paths from source (node 0) in a Directed Acyclic Graph (DAG) - edges only go from u to  v with weights [u,v,w], no cycles.
  - Key Insight: In a DAG, if we process nodes in topological order, when we reach a node, all paths to it have already been considered (no incoming edges from unprocessed nodes).
  - Why Topological Order Works: In normal Dijkstra/BFS, we need to revisit nodes because cycles can create shorter paths later
  - In DAG, once we process a node in topological order, its distance is finalized (no backward edges). This allows O(V+E) solution instead of O((V+E)log V)
  - Approach Evolution: First, get topological order via DFS. Then relax edges in that order. This guarantees we process each node after all its predecessors
  - Visual Intuition: Imagine a flowchart (DAG) - you can only go forward. If you list tasks topologically (do prerequisites first), then process shortest paths left-to-right, you never need to go back and update.

  Algorithm:
  1. Build adjacency list from edges
  2. Get Topological Order using DFS:
   - Track visited nodes
   - After exploring all neighbors, push node to stack
   - Stack gives reverse topological order
   3. Initialize distances:
   - dist[0] = 0, all others = INFINITY
   4. Process nodes in topological order:
   For each node popped from stack:
     If dist[node] is not INF:
       For each neighbor (adjNode, weight):
         dist[adjNode] = min(dist[adjNode], dist[node] + weight)
    5. Convert unreachable nodes (INF) to -1
    6. Return distance array

  TC : O(V+E) & SC: O(V+2E)
 */

import java.util.*;
public class shortestDAG {
    public int[] shortestPath(int V, int E, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], dist = edge[2];
            adj.get(u).add(new int[] { v, dist });
        }

        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(adj, vis, st, i);
            }
        }

        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = (int) 1e9;
        }

        dist[0] = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (dist[node] != (int) 1e9) {
                for (int neighbor[] : adj.get(node)) {
                    int adjNode = neighbor[0], wt = neighbor[1];
                    if (dist[node] + wt < dist[adjNode])
                        dist[adjNode] = dist[node] + wt;
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == (int) 1e9)
                dist[i] = -1;
        }
        return dist;
    }

    public void dfs(List<List<int[]>> adj, boolean vis[], Stack<Integer> st, int node) {
        vis[node] = true;
        for (int neighbor[] : adj.get(node)) {
            if (!vis[neighbor[0]]) {
                dfs(adj, vis, st, neighbor[0]);
            }
        }
        st.push(node);
    }
}
