// LC-802: https://leetcode.com/problems/find-eventual-safe-states/description/

/*
Detailed Intuition

- The problem asks us to find all nodes in a directed graph from which every possible path eventually ends at a terminal node (a node with no outgoing edges). Such nodes are called safe nodes. A node is unsafe if it can reach a cycle, because once a cycle is reachable, there exists an infinite path that never terminates.

- A direct way to think about this is: instead of trying to detect which nodes lead to cycles, we identify nodes that are guaranteed not to be part of or reach any cycle. Terminal nodes are obviously safe because they have no outgoing edges. Any node whose outgoing edges lead only to safe nodes is also safe. This suggests a reverse dependency process.

- The key insight is to reverse the graph. In the original graph, edges represent “where a node can go.” In the reversed graph, edges represent “who depends on this node.” By reversing edges, we can propagate safety backward from terminal nodes to their predecessors.

- We compute an indegree array where indegree[u] represents the number of outgoing edges of node u in the original graph (because after reversing edges, outgoing edges become incoming ones). Nodes with indegree 0 are terminal nodes — they cannot move anywhere — so they are immediately safe and added to a queue.

- Using a BFS process similar to Kahn’s topological sort, we repeatedly remove safe nodes from the queue. When a node becomes safe, we reduce the indegree of its predecessors (using the reversed adjacency list). Conceptually, this means one unsafe outgoing path has disappeared because it now leads to a confirmed safe node. If a predecessor eventually loses all outgoing edges (its indegree becomes 0), it means every path from that node leads only to safe nodes, so it also becomes safe and is added to the queue.

- This process continues until no more nodes can be marked safe. Nodes that never reach indegree 0 are precisely those involved in or leading to cycles, since they always retain at least one outgoing path into a cycle.

- Finally, we sort the collected safe nodes because the problem requires the result in increasing order.

TC & SC: O(V+E)
*/

import java.util.*;
public class safeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;
        List<List<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            revAdj.add(new ArrayList<>());

        int indegree[] = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : graph[u]) {
                revAdj.get(v).add(u);
                indegree[u]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);

            for (int neighbor : revAdj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
