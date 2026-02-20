// GFG: https://www.geeksforgeeks.org/problems/topological-sort/1

/*
Intuition:

- Topological sorting is used for Directed Acyclic Graphs (DAGs) to produce a linear ordering of vertices such that for every directed edge u → v, node u appears before node v in the ordering. In simpler terms, a node must come before all the nodes that depend on it. This is commonly seen in dependency problems such as course scheduling, task execution order, or build systems.

- The key observation behind this DFS-based solution is that a node should only be placed in the final ordering after all of its outgoing neighbors have been processed. If a node points to other nodes, those nodes must appear later in the ordering. Therefore, we delay adding a node to the result until all nodes reachable from it have already been explored.

- Depth First Search naturally supports this idea through recursion. When DFS visits a node, it first explores all its neighbors recursively. Each neighbor represents a dependency that must be resolved before the current node can be finalized. Only after visiting every reachable neighbor do we push the current node onto a stack. This ensures that deeper dependencies are stored before their prerequisites.

- The stack plays an important role here. Nodes are pushed onto the stack after their DFS traversal finishes (postorder). This means nodes with no outgoing edges are pushed first, while nodes that depend on others are pushed later. When we finally pop elements from the stack, the order reverses, producing a valid topological ordering where prerequisites appear before dependent nodes.

- Since the graph may contain multiple disconnected components, we run DFS from every unvisited node. This guarantees that all vertices are included in the ordering, even if they belong to separate subgraphs. The visited array ensures that each node is processed exactly once, preventing redundant traversals.

- Overall, the algorithm works by exploring dependencies deeply using DFS, storing nodes after their work is complete, and then reversing that completion order using a stack to obtain a valid topological sequence.

TC & SC: O(V+E)
*/

import java.util.*;
public class topoSortDFS {
    public void dfs(List<List<Integer>> adj, Stack<Integer> st, boolean vis[], int node) {
        vis[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!vis[neighbor])
                dfs(adj, st, vis, neighbor);
        }
        st.push(node);
    }

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
        }

        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i])
                dfs(adj, st, vis, i);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty())
            ans.add(st.pop());

        return ans;
    }
}