// GFG: https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1

// TC: O(E log V) & SC: O(V+E)

import java.util.*;
public class dijkstraPQ {
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

        // min heap storing [distance,node] with ordering acc to distance then nodes
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] { 0, src });
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int curr[] = pq.poll();
            int node = curr[1];
            for (int neighbor[] : adj.get(node)) {
                int adjNode = neighbor[0], wt = neighbor[1];
                if (dist[node] + wt < dist[adjNode]) {
                    dist[adjNode] = dist[node] + wt;
                    pq.offer(new int[] { dist[adjNode], adjNode });
                }
            }
        }
        return dist;
    }
}