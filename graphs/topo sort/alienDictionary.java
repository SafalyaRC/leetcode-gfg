// GFG: https://www.geeksforgeeks.org/problems/alien-dictionary/1

/*
Intuition:

- The Alien Dictionary problem can be viewed as discovering an unknown alphabetical order from a sorted list of words. Since the words are already sorted according to alien lexicographic rules, every pair of adjacent words provides information about how certain characters must be ordered relative to each other. The goal is therefore to extract these ordering constraints and construct a valid global character ordering that satisfies all of them.

- The first step is identifying all unique characters present in the dictionary. Unlike typical graph problems where the number of vertices is explicitly given, here the characters themselves form the nodes of the graph, and we do not know beforehand how many distinct characters exist. Therefore, we dynamically create graph nodes using maps, ensuring every character appearing in the input is represented, even if it has no explicit ordering relation with others. The indegree map tracks how many prerequisites each character has.

- Next, we build the directed graph by comparing adjacent words in the dictionary. Because the dictionary is already sorted, the first position where two consecutive words differ reveals the ordering rule between those two characters. For example, if "abc" appears before "axd", then 'b' must come before 'x' in the alien alphabet. Only the first differing character matters because lexicographic comparison stops at that point. Additionally, we check an invalid scenario where a longer word appears before its own prefix (e.g., "abcd" before "ab"), which makes any ordering impossible.

- Once the graph is built, the problem becomes a standard topological sorting task. We apply Kahn’s Algorithm (BFS-based topological sort) by first inserting all characters with indegree 0 into a queue. These characters have no prerequisites and can appear first in the alien alphabet. As we process each character, we append it to the answer and reduce the indegree of all characters dependent on it, effectively simulating the removal of satisfied dependencies.

- Whenever a character’s indegree becomes zero, it means all ordering constraints for that character have been resolved, so it becomes eligible to appear next in the ordering and is added to the queue. This process continues until no more characters can be processed, gradually constructing a valid ordering consistent with all derived rules.

- Finally, we verify whether a complete ordering was produced. If the number of characters in the result is smaller than the total number of unique characters, it indicates a cycle in the dependency graph. A cycle means conflicting ordering constraints exist, making it impossible to determine a valid alien alphabet. In such cases, we return an empty string; otherwise, the constructed sequence represents one valid ordering of the alien language.

TC & SC: O(V+E)
*/

import java.util.*;
public class alienDictionary {
    public String findOrder(String[] words) {
        // step-1: collect unique characters, must use a map as no V is given to us and we dont know anything for certaim
        Map<Character, List<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                adj.putIfAbsent(ch, new ArrayList<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        // step-2: build the graph using adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];

            // invalid case:
            if (w1.length() > w2.length() && w1.startsWith(w2))
                return "";

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    char u = w1.charAt(j);
                    char v = w2.charAt(j);

                    adj.get(u).add(v);
                    indegree.put(v, indegree.get(v) + 1);
                    break; // only first differing characters
                }
            }
        }

        // step-3: Kahn's BFS Topo Sort to get that order of letters
        Queue<Character> q = new LinkedList<>();

        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0)
                q.offer(ch);
        }

        StringBuilder ans = new StringBuilder();

        while (!q.isEmpty()) {
            char node = q.poll();
            ans.append(node);

            for (char neighbor : adj.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0)
                    q.offer(neighbor);
            }
        }

        // step-4: check for cycle:
        if (ans.length() != indegree.size())
            return "";
        return ans.toString();
    }
}
