// GFG (Topo sort using BFS or Kahns algorithm): https://www.geeksforgeeks.org/problems/topological-sort/1

/*
Intuition:

- Topological sorting using Kahn’s Algorithm (BFS approach) is based on the idea of resolving dependencies step by step. In a directed graph, an edge u → v means that node v depends on node u, so u must appear before v in the final ordering. Therefore, any node that has no incoming edges (indegree = 0) has no pending dependencies and can safely appear first in the ordering.

- The algorithm begins by calculating the indegree of every node, which represents how many prerequisites or incoming edges each node has. A node with indegree 0 means no other node must come before it. These nodes form the starting points of the topological ordering because they are immediately executable or processable.

- We then place all nodes with indegree 0 into a queue. This queue represents nodes whose dependencies have already been satisfied. Using BFS-like processing, we repeatedly remove a node from the queue and add it to the final ordering. Conceptually, this means we are “executing” or “removing” that node from the graph.

- When a node is removed, all of its outgoing edges are also removed logically. This is simulated by decreasing the indegree of its neighboring nodes. If any neighbor’s indegree becomes 0 after this reduction, it means all of its prerequisites have now been processed, so it becomes eligible to be processed next and is added to the queue.

- This process continues until no nodes remain in the queue. If the graph is a Directed Acyclic Graph (DAG), every node will eventually reach indegree 0 and be processed, producing a valid topological order. However, if some nodes never reach indegree 0, it indicates a cycle exists because those nodes are mutually dependent and cannot be resolved.

- Overall, Kahn’s Algorithm works by repeatedly selecting nodes with no remaining dependencies and removing them from the graph, gradually building a valid ordering from independent tasks to dependent tasks.

TC & SC: O(V+E)
*/

import java.util.*;
public class topoSortBFSKahns {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
        }

        int indegree[] = new int[V];
        
        // alternate indegree calculation method: for(int edge[]:edges) indegree[edge[1]]++;

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i))
                indegree[it]++;
        }

        // intital configuration adding all nodes with indeg=0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--; // indegree of 'neighbor' reduces as we poll() it's indegree 'node'
                if (indegree[neighbor] == 0) { // it is safe now to add the neighbor to queue as all it's indegree nodes have been processed already
                    q.offer(neighbor);
                }
            }
        }
        return ans;
    }
}
