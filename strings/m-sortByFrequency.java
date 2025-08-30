// LC-451: https://leetcode.com/problems/sort-characters-by-frequency/

// brute-force (only works for pure lowercase strings/uppercase strings but not mixed ones):
class Solution {
    public String frequencySort(String s) {
        char words[] = s.toCharArray();
        Arrays.sort(words);
        return new String(words);
    }
}

// optimal:
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder ans = new StringBuilder();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<Character> sortedMapKeys = new ArrayList<>(map.keySet()); // store map keys in a list and sort them acc to
                                                                       // freq
        sortedMapKeys.sort((a, b) -> map.get(b) - map.get(a));
        for (char ch : sortedMapKeys) {
            for (int i = 0; i < map.get(ch); i++)
                ans.append(ch);
        }
        return ans.toString();
    }
}
