// GFG: https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1

// TC: O(V+E) & SC: O(V)

import java.util.*;
public class BFS {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size(); // no. of vertices
        ArrayList<Integer> ans = new ArrayList<>();
        boolean visited[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        int source = 0;
        q.offer(source);
        visited[source] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            ans.add(curr);

            for (int neighbour : adj.get(curr)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
        return ans;
    }
}