// GFG: https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

/*
A directed graph contains a cycle if there exists a path that starts from a node and eventually leads back to the same node while following the direction of edges. The core idea of this solution is to explore the graph using Depth First Search (DFS) and detect whether such a “backward connection” appears during traversal.

The graph is first represented using an adjacency list so that for every node we know which nodes it points to. Since a graph may contain multiple disconnected components, we cannot assume traversal from a single node will cover the entire graph. Therefore, we attempt DFS from every node that has not yet been visited, ensuring that all components are checked.

During DFS, we maintain two types of visitation tracking. The visited[] array records whether a node has ever been explored at any time during the overall traversal. This prevents redundant work by ensuring each node is processed only once. However, detecting cycles in directed graphs requires more than just knowing whether a node was visited before — we must know whether it is part of the current DFS path.

This is where pathVisited[] comes into play. It represents nodes that are currently active in the recursion stack (the ongoing DFS path). When we enter a node during DFS, we mark it in both visited and pathVisited. As we explore neighbors, if we encounter a neighbor that is already marked in pathVisited, it means we have reached a node that is still in the current traversal chain. This indicates a back edge, which is the defining property of a cycle in a directed graph.

If a neighbor has not been visited yet, we recursively explore it. If any recursive call detects a cycle, we immediately propagate true upward since the existence of even one cycle is sufficient to conclude that the graph is cyclic.

After all neighbors of a node have been explored, we unmark it from pathVisited. This step represents backtracking — the node is no longer part of the active DFS path. This is crucial because the node should not be considered part of future traversal paths originating from different branches or components. Without removing it from pathVisited, later traversals could incorrectly interpret a normal edge as a cycle.

Overall, the algorithm works by simulating traversal paths and checking whether any edge points back into the current path. If such a situation occurs, a cycle exists; otherwise, if all DFS traversals finish without conflicts, the directed graph is acyclic.

TC & SC: O(V+E)
*/

import java.util.*;
public class directedCycleDFS {
    public boolean isCyclic(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
        }

        boolean visited[] = new boolean[V]; // for all nodes in the graph
        boolean pathVisited[] = new boolean[V]; // for current path nodes in the graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(adj, visited, pathVisited, i))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(List<List<Integer>> adj, boolean visited[], boolean pathVisited[], int node) {
        visited[node] = true;
        pathVisited[node] = true;

        // traverse adjacent nodes:
        for (int neighbor : adj.get(node)) {

            // when the adjacent node isnt visited yet, recurse
            if (!visited[neighbor]) {
                if (dfs(adj, visited, pathVisited, neighbor))
                    return true;
            }

            // IMP: if a node is previously visited and it's also on the same path, we got a cycle
            else if (pathVisited[neighbor])
                return true;
        }
        pathVisited[node] = false; // unmark the node and backtrack, this is especially important for graph with multiple components, because unmarking on pathVisited makes sure that the current node is no longer under consideration and we can safely backtrack, without this step we may falsely detect an edge when none exists
        return false;
    }
}
