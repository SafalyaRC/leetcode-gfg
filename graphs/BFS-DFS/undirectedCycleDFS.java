// GFG: https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

/*
How DFS detects a cycle: 
1. While exploring neighbors of a node:
 -If the neighbor is unvisited: Recurse deeper with the current node as its parent.
 - If any recursive call detects a cycle, propagate true.
 - If the neighbor is already visited: If it is not the parent, then this neighbor must have been reached earlier via a different path.
 - This forms a closed loop → cycle detected.

2. Why parent tracking is mandatory
- Without tracking the parent:
 -Every undirected edge would falsely appear as a cycle
 - Because each node always “sees” the node it came from
- Parent tracking filters out this false positive.

3. Handling disconnected graphs
- The graph may consist of multiple components. So we start DFS from every unvisited vertex to ensure all components are checked.

Algorithm (step-by-step)
- Build adjacency list
- Convert the edge list into an undirected adjacency list.
- Initialize visited array: Tracks whether a node has already been explored.
- Traverse all vertices
- For each unvisited node, start DFS with parent -1.
- DFS logic: 
  - Mark the current node as visited.
  - For each neighbor:
    - If unvisited → recurse.
    - Else if visited and neighbor ≠ parent → cycle found.
  - Return result
  - If any DFS call returns true, the graph contains a cycle.
  - Otherwise, the graph is acyclic.

TC & SC: O(V+E)
*/

import java.util.*;
public class undirectedCycleDFS {
    public boolean dfs(int node, int parentNode, List<List<Integer>> adj, boolean visited[]) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            // if neighbor not visited, explore by recursing further:
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, adj, visited))
                    return true;
            } else if (neighbor != parentNode)
                return true;
        }
        return false; // no cycle found from this path
    }

    public boolean isCycle(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, adj, visited))
                    return true;
            }
        }
        return false;
    }
}
