// GFG: https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1

// TC: O(V+E) & SC: O(V)

import java.util.*;
public class shortestUnitDAG {
    public int[] shortestPath(int V, int[][] edges, int src) {
        List<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<V;i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int edge[]:edges) {
            int u=edge[0], v=edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        int dist[]=new int[V];
        Arrays.fill(dist,(int)1e9);
        
        dist[src]=0;
        Queue<Integer> q=new LinkedList<>();
        q.offer(src);
        
        while(!q.isEmpty()) {
            int node=q.poll();
            for(int neighbor:adj.get(node)) {
                if(dist[node]+1<dist[neighbor]) {
                    dist[neighbor]=dist[node]+1;
                    q.offer(neighbor);
                }
            }
        }
        
        for(int i=0;i<V;i++) {
            if(dist[i]==(int)1e9) dist[i]=-1;
        }
        return dist;
    }
}