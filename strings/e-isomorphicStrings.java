// LC-205: https://leetcode.com/problems/isomorphic-strings/description/

// sol-1:
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i))
                    return false;
            } else {
                if (!map.containsValue(t.charAt(i)))
                    map.put(s.charAt(i), t.charAt(i));
                else
                    return false;
            }
        }
        return true;
    }
}

// sol-2:
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (!sMap.containsKey(sChar))
                sMap.put(sChar, i);
            if (!tMap.containsKey(tChar))
                tMap.put(tChar, i);

            if (sMap.get(sChar) != tMap.get(tChar))
                return false;
        }
        return true;
    }
}