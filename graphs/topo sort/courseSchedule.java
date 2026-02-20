// LC-207: https://leetcode.com/problems/course-schedule/description/

// we simply visualize this problem as means of directed graph cyclicity check, hence we use the topo sort approach here (Note: We can also use BFS/DFS)

/*
Intuition:

- The problem can be understood as a dependency resolution task where each course may require completing another course beforehand. This naturally forms a directed graph, where every course is a node and each prerequisite pair [a, b] represents a directed edge b → a, meaning course b must be completed before course a. The question then reduces to determining whether it is possible to complete all courses without encountering circular dependencies.

- A key observation is that if the prerequisite structure contains a cycle, it becomes impossible to finish all courses. In a cycle, each course depends (directly or indirectly) on itself, creating an infinite dependency loop. Therefore, instead of explicitly searching for cycles, we attempt to perform a topological sort. A valid topological ordering exists only if the graph is a Directed Acyclic Graph (DAG), so successfully constructing such an order implicitly proves that no cycle exists.

- To achieve this, we compute the indegree of every node, where indegree represents how many prerequisites a course still has. Courses with indegree 0 have no pending prerequisites and can be taken immediately. These courses act as starting points and are inserted into a queue. Conceptually, they represent tasks that are currently executable.

- We then process courses using a BFS-like approach (Kahn’s Algorithm). Each time we remove a course from the queue, we treat it as completed and reduce the indegree of all courses that depend on it. This simulates removing the completed course and its outgoing edges from the graph. Whenever a dependent course’s indegree becomes 0, it means all its prerequisites are satisfied, so it becomes eligible to be taken and is added to the queue.

- As this process continues, we count how many courses have been successfully processed. If the graph has no cycles, every course will eventually have its dependencies resolved and will be processed exactly once. However, if a cycle exists, the courses involved in the cycle will never reach indegree 0, because they keep depending on each other. As a result, the queue becomes empty before all courses are processed.

- Finally, we compare the number of processed courses with the total number of courses. If both are equal, a full topological ordering was possible and all courses can be finished. Otherwise, some courses were locked inside a cyclic dependency, making completion impossible.

TC & SC: O(V+E)
*/

import java.util.*;
public class courseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        int indegree[] = new int[numCourses];
        for (int edge[] : prerequisites) {
            int u = edge[0], v = edge[1];
            // the next 2 lines below are strictly according to condition provided for [Ai,Bi] in the question
            adj.get(v).add(u);  
            indegree[u]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
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

        return processed == numCourses;
    }
}
