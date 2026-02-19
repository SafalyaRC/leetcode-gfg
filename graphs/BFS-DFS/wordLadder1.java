// LC-127: https://leetcode.com/problems/word-ladder/description/

/*
Detailed Intuition: 
This problem is fundamentally a shortest path problem in an unweighted graph, but the graph is not given explicitly. Each word represents a node. There is an edge between two words if they differ by exactly one character. We are asked to compute: The minimum number of transformations needed to convert beginWord to endWord.

Since:
- Each transformation changes exactly one letter.
- Each step costs exactly 1.
- We want the minimum number of steps.
This immediately implies: Use BFS (because BFS guarantees shortest path in unweighted graphs).

The Core Difficulty: If we try to build the graph naively, For each word:
- Compare with every other word.
- Check if they differ by exactly one character.
- That costs: O(N² × L)
- Where: N = number of words, L = length of each word
This is too slow for large inputs.

Optimization: Pattern Mapping Trick~
Instead of comparing every pair of words, we use a pattern transformation technique.For each word of length L, generate L intermediate patterns:
Example: "hot"
*ot
h*t
ho*

Words sharing the same pattern differ by exactly one character. Example: *ot → hot, dot, lot. So these words are neighbors. This allows us to construct an implicit adjacency list efficiently.

Why BFS Works Here, BFS explores:
- Level 1 → words 1 transformation away
- Level 2 → words 2 transformations away
- Level k → words k transformations away
The first time we reach endWord, we are guaranteed it is via the shortest sequence. DFS cannot guarantee this.


ALGORITHM:
Step 1: Validate Input: If endWord is not in wordList, return 0. Because we are only allowed to transform using words in wordList.

Step 2: Build Pattern Map
- Add beginWord to wordList.
- Let len = beginWord.length().
- For each word in wordList:
  - For each position i from 0 to len-1: Create pattern: word.substring(0, i) + "*" + word.substring(i + 1)
  - Add word to patternMap[pattern].
  - This builds a mapping: Pattern → List of words matching it.This acts as an adjacency structure.

Step 3: BFS Initialization
- Queue ← beginWord
- Visited ← beginWord
- Level = 1

Step 4: BFS Traversal
While queue not empty:
- For all nodes in current level: Pop word.
- If word equals endWord → return level.
- Generate all patterns of word.
  - For each neighbor in patternMap:
   - If not visited: Mark visited, Add to queue
- Increment level.

Step 5: If BFS Ends Without Finding endWord: Return 0.

TC: O(N*L^2), N = number of words, L = length of each word  & SC: O(N*L)
*/

import java.util.*;
public class wordLadder1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;

        Map<String, List<String>> patternMap = new HashMap<>(); // adjacency list for all patterns
        wordList.add(beginWord);
        int len = beginWord.length(); // note: all words in wordlist are of equal length and equal to beginword

        // step-1: build the pattern map
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1); // create the pattern
                patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word); // add pattern to our map
            }
        }

        // step-2: perform BFS
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int level = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();

                if (word.equals(endWord))
                    return level; // reached the end word

                for (int j = 0; j < len; j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1); // generate patterns

                    // explore its neighbors
                    for (String neighbor : patternMap.getOrDefault(pattern, new ArrayList<>())) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            q.offer(neighbor);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}