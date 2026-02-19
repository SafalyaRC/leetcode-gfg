// LC-785: https://leetcode.com/problems/is-graph-bipartite/description/

/*
A graph is said to be bipartite if its vertices can be divided into two separate groups such that no two adjacent nodes belong to the same group. In simpler terms, every node must be placed in a different set or color than all of its neighbors. The problem therefore reduces to checking whether such a two-group assignment is possible for the entire graph.

We approach this by imagining that each node must be colored using only two colors (or placed into two groups). When we start from any node, we arbitrarily assign it one color (say Group A). Since neighbors cannot share the same group, all its adjacent nodes must belong to the opposite group (Group B). Then, the neighbors of those nodes must again switch back to Group A, and this alternating assignment continues throughout the graph.

Breadth First Search (BFS) helps propagate this coloring level by level. As BFS explores neighbors, it assigns each uncolored neighbor the opposite color of the current node. This ensures that every edge connects nodes of opposite colors. While traversing, if we ever encounter a situation where a node and its neighbor already have the same color, it means the graph violates the bipartite condition, and therefore it cannot be divided into two valid groups.

Since graphs may contain multiple disconnected components, we repeat this BFS coloring process for every uncolored node. Each BFS attempt tries to color one connected component consistently. If all components can be colored without conflict, the graph is bipartite; otherwise, the presence of even a single coloring conflict proves it is not.

Conceptually, the algorithm checks whether the graph can maintain an alternating pattern of colors along every edge. If such alternating assignment is possible globally, the graph forms two independent sets; if not, some cycle forces two adjacent nodes into the same group, breaking the bipartite property.

TC: O(V+E) & SC: O(V)
*/

import java.util.*;
public class bipartiteOrNot {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int color[] = new int[n]; // 0->uncoloured +1-> Group-A/Color-A -1-> Group B/Color-B

        for (int i = 0; i < n; i++) {
            // if a node is not coloured:
            if (color[i] == 0) {
                if (!bfs(graph, color, i))
                    return false;
            }
        }
        return true;
    }

    public boolean bfs(int graph[][], int color[], int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        color[start] = 1; // assign first color

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : graph[node]) {
                // if same color for a node and its adjacent
                if (color[neighbor] == color[node])
                    return false;

                // neighbor not visited
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node]; // put neighbor in different group so as to maintain the cond.
                    q.offer(neighbor);
                }
            }
        }
        return true;
    }
}

// DFS Approach:

class Solution {
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, graph, color))
                    return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int currColor,
            int[][] graph, int[] color) {

        color[node] = currColor;

        for (int neighbor : graph[node]) {

            // already colored
            if (color[neighbor] != 0) {
                if (color[neighbor] == currColor)
                    return false;
            }
            // color recursively
            else {
                if (!dfs(neighbor, -currColor, graph, color))
                    return false;
            }
        }
        return true;
    }
}
