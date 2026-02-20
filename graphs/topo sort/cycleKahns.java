// GFG: https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

/*
Why Topological Sort is Valid Only for DAGs: Topological sorting is possible only for Directed Acyclic Graphs (DAGs) because it requires arranging nodes in a linear order such that for every directed edge u → v, node u appears before node v. If a directed cycle exists, this requirement becomes impossible to satisfy. In a cycle, each node indirectly depends on itself through a chain of dependencies (for example, A → B → C → A). This creates a contradiction: A must come before B, B before C, and C before A, meaning no valid starting point exists. Therefore, the existence of even one directed cycle prevents a valid ordering, making topological sorting meaningful only when the graph has no cycles — i.e., when it is a DAG.

Intuition:

- This solution detects cycles in a directed graph using Kahn’s Algorithm, which is based on the principles of topological sorting using BFS. Instead of explicitly searching for a cycle, the algorithm attempts to construct a valid topological order. If such an ordering cannot include all vertices, a cycle must exist.

- The algorithm begins by building an adjacency list representation of the graph and computing the indegree of every node. The indegree represents how many incoming edges a node has, or equivalently, how many prerequisites must be completed before that node can be processed. Nodes with indegree 0 have no dependencies and are safe starting points.

- All nodes with indegree 0 are added to a queue. These nodes can appear first in a topological order because nothing needs to come before them. The algorithm then repeatedly removes a node from the queue and considers it processed. Conceptually, this means removing that node and its outgoing edges from the graph.

- When a node is processed, the indegree of all its neighbors is reduced because one of their dependencies has been resolved. If any neighbor’s indegree becomes 0, it means all of its prerequisites have now been satisfied, so it is added to the queue and becomes eligible for processing next. This process continues layer by layer, gradually removing dependency constraints from the graph.

- If the graph is acyclic, every node will eventually reach indegree 0 and be processed exactly once, producing a topological ordering containing all V nodes. However, if a cycle exists, the nodes involved in the cycle will always have at least one incoming edge from another node within the same cycle. As a result, their indegree never becomes 0, they never enter the queue, and the algorithm stops early.

- Finally, the algorithm checks whether the number of processed nodes equals the total number of vertices. If fewer nodes were processed (topoSort.size() != V), it means some nodes were trapped in cyclic dependencies, proving that a cycle exists in the graph.

TC & SC: O(V+E)
*/

import java.util.*;
public class cycleKahns {
    public boolean isCyclic(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        int indegree[] = new int[V];
        for (int edge[] : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int processed = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            processed++;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }

        return processed != V; // if we get the topo sort ordering size<V, it implies the topo sort wasnt possible since there was a cycle that was present
    }
}
