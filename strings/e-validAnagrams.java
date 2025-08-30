
// brute-force:
class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        char sArray[] = s.toCharArray();
        char tArray[] = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        for (int i = 0; i < s.length(); i++) {
            if (sArray[i] != tArray[i])
                return false;
        }
        return true;
    }
}

// optimal-1:
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        for (char ch : s.toCharArray()) {
            if ((!tMap.containsKey(ch)) || (!sMap.get(ch).equals(tMap.get(ch))))
                return false;
        }
        return true;
    }
}

// optimal-2:
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        return sMap.equals(tMap);
    }
}

// optimal-3:
class Solution {
    public boolean isAnagram(String s, String t) {
        /*
         * the basic intuition here is to have a hash array and if two strings are valid
         * anagrams, then the frequency of characters in s string will increment and
         * that in t string will decrement, so in the end the frequency of characters in
         * both the strings will cancel each other out.
         */

        if (s.length() != t.length())
            return false;

        int char_count[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char_count[s.charAt(i) - 'a']++;
            char_count[t.charAt(i) - 'a']--;
        }

        for (int count : char_count) {
            if (count != 0)
                return false;
        }
        return true;
    }
}
