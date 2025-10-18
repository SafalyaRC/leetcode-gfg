// LC-1358: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/

// brute-force(TLE):
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int countSubstring = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() == 3) {
                    countSubstring += (n - j);
                    break;
                }
            }
        }
        return countSubstring;
    }
}

// optimal:
public class mSubstringContainsAll3 {
    public int numberOfSubstrings(String s) {
        int lastSeen[] = { -1, -1, -1 }; // which letter is seen last at which index
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if (lastSeen[0] != -1 || lastSeen[1] != -1 || lastSeen[2] != -1) {
                count += (Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])) + 1);
            }
        }
        return count;
    }
}
