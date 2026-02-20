// LC-210: https://leetcode.com/problems/course-schedule-ii/description/

/*
This solution models the course prerequisite structure as a directed graph and applies Kahn’s Algorithm (BFS-based Topological Sort) to determine whether a valid course ordering exists. By tracking indegrees, the algorithm processes courses only when all their prerequisites are satisfied, effectively simulating dependency resolution. If all courses are processed, the graph is acyclic and a valid ordering exists; otherwise, remaining nodes indicate a cycle caused by circular dependencies. The solution runs in optimal O(V + E) time and is a standard industry approach for scheduling and dependency problems.

TC & SC: O(V+E)
*/

import java.util.*;
public class courseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        int indegree[] = new int[numCourses];
        for (int edge[] : prerequisites) {
            int u = edge[0], v = edge[1];
            adj.get(v).add(u);
            indegree[u]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int topoSort[] = new int[numCourses];
        int ind = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topoSort[ind++] = node;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    q.offer(neighbor);
            }
        }

        if (ind != numCourses)
            return new int[] {};
        return topoSort;
    }
}
