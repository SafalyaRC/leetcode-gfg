// GFG: https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1

// TC: O(V+E) & SC: O(V)

import java.util.*;
public class DFS {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean visited[] = new boolean[adj.size()];
        dfs(ans, visited, adj, 0);
        return ans;
    }

    public void dfs(ArrayList<Integer> ans, boolean visited[], ArrayList<ArrayList<Integer>> adj, int node) {
        // mark the nodes as visited
        visited[node] = true;
        ans.add(node);

        // getting neighbour nodes
        for (int it : adj.get(node)) {
            if (!visited[it]) {
                dfs(ans, visited, adj, it);
            }
        }
    }
}
