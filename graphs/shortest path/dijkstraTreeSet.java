// GFG: https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1

// TC: O(E log V) & SC: O(V+E)

import java.util.*;
public class dijkstraTreeSet {
    public int[] dijkstra(int V, int[][] edges, int src) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1], wt = edge[2];
            adj.get(u).add(new int[] { v, wt });
            adj.get(v).add(new int[] { u, wt });
        }

        int dist[] = new int[V];
        Arrays.fill(dist, (int) 1e9);

        // treeset comparator: sort according to ascening order of distances, if equal, sort according to nodes
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        set.add(new int[] { 0, src });
        dist[src] = 0;

        while (!set.isEmpty()) {
            int curr[] = set.pollFirst(); // poll the first lowest element (similar to min heap)
            int node = curr[1];

            for (int neighbor[] : adj.get(node)) {
                int adjNode = neighbor[0], wt = neighbor[1];
                if (dist[node] + wt < dist[adjNode]) {
                    set.remove(new int[] { dist[adjNode], adjNode }); // remove old entry if present to avoid stale data

                    // update distance and add new entry:
                    dist[adjNode] = dist[node] + wt;
                    set.add(new int[] { dist[adjNode], adjNode });
                }
            }
        }
        return dist;
    }
}
