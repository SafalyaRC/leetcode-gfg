// GFG: https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1

// TC & SC: O(V+E)

import java.util.ArrayList;
import java.util.List;

public class connectedComponents {
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        // step-1: create the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // step-2: visited array
        boolean visited[] = new boolean[V];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        // step-3: DFS for each unvisited vertex
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    public void dfs(int node, List<List<Integer>> adj, boolean visited[], ArrayList<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }
}
