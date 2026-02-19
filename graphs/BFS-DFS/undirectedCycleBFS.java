// GFG: https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

/*
We are given an undirected graph and must determine whether any cycle exists, even across disconnected components.
Key property of undirected graphs, In an undirected graph: Every edge appears twice in the adjacency list (u ↔ v). So when traversing, we will always “see” the node we came from again. Therefore, seeing a visited neighbor alone is NOT enough to conclude a cycle. The crucial insight is: A cycle exists only if we encounter a visited neighbor that is NOT the node we came from (parent).

Why BFS + Parent Tracking?
During BFS:
- Nodes are explored level by level.
- Each node remembers who discovered it (its parent).
- This allows us to differentiate between:
  - A legitimate back-edge to parent (normal in undirected graphs)
  - A true cycle edge (visited node that is not the parent)
- Hence, we store (currentNode, parentNode) in a Pair.

Cycle detection logic (core insight)
- For every neighbor of the current node:
- If the neighbor is unvisited
  - Mark it visited
  - Push (neighbor, currentNode) into the queue
- If the neighbor is already visited
  - And neighbor ≠ parentNode: Then this neighbor must have been reached earlier via a different path -> That implies a cycle
This is the heart of the algorithm.

Why this correctly detects cycles
- Parent edges are ignored → avoids false positives
- Any cross-edge in BFS traversal indicates a loop
- Works for: Simple cycles, Large cycles, Disconnected graphs

TC & SC: O(V+E)
*/

import java.util.*;
public class undirectedCycleBFS {
    class Pair {
        int currentNode, parentNode;

        public Pair(int currentNode, int parentNode) {
            this.currentNode = currentNode;
            this.parentNode = parentNode;
        }
    }

    public boolean checkCycle(int src, List<List<Integer>> adj, boolean visited[]) {
        visited[src] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1)); // source node inital state coming from dummy parent (-1)

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int node = curr.currentNode;
            int parent = curr.parentNode;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(new Pair(neighbor, node));
                } else if (neighbor != parent) { // imp: if a neighbor that is not it's parent has already been visited, it implies some other node explored it before you got there, hence it confirms presence of a cycle
                    return true;
                }
            }
        }
        return false;

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
                if (checkCycle(i, adj, visited))
                    return true;
            }
        }
        return false;
    }
}
