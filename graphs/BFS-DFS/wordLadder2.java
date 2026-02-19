// LC-126: https://leetcode.com/problems/word-ladder-ii/description/


// suboptimal:
import java.util.*;
public class wordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // store dictionary words for O(1) lookup
        Set<String> dict = new HashSet<>(wordList);

        Queue<List<String>> q = new LinkedList<>();
        List<String> startPath = new ArrayList<>();
        startPath.add(beginWord);
        q.offer(startPath);

        // words used at the current level:
        List<String> currWords = new ArrayList<>();
        currWords.add(beginWord);

        int lvl = 1;
        List<List<String>> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            List<String> path = q.poll();

            // when we move to next level
            if (path.size() > lvl) {
                lvl++;

                // remove words at current level from dictionary
                for (String word : currWords) {
                    dict.remove(word);
                }
                currWords.clear();
            }

            String lastWord = path.get(path.size() - 1);

            // if target reached:
            if (lastWord.equals(endWord)) {
                if (ans.size() == 0)
                    ans.add(path); // the first sequence is bound to be the shortest

                // make sure to only use the shortest sequence
                else if (ans.get(0).size() == path.size()) {
                    ans.add(path);
                }
                continue;
            }

            // try all tranformations
            for (int i = 0; i < lastWord.length(); i++) {
                char wordChars[] = lastWord.toCharArray();

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wordChars[i] = ch;
                    String newWord = new String(wordChars);

                    if (dict.contains(newWord)) {
                        path.add(newWord);

                        List<String> copyPath = new ArrayList<>(path); // copy of the path to add to our queue
                        q.offer(copyPath);
                        currWords.add(newWord);

                        path.remove(path.size() - 1); // backtrack;
                    }
                }
            }
        }
        return ans;
    }
}


