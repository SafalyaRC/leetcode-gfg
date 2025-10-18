// LC-3: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

// brute-force:

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0)
            return 0;
        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int len = 0;
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}

// optimal:
class mlongestNonRepeatSubstr {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        int l = 0, r = 0, maxLen = 0;
        int hash[] = new int[256];
        Arrays.fill(hash, -1);
        while (r < n) {
            if (hash[s.charAt(r)] != -1) {
                if (hash[s.charAt(r)] >= l) {
                    l = hash[s.charAt(r)] + 1;
                }
            }
            int len = r - l + 1;
            maxLen = Math.max(maxLen, len);
            hash[s.charAt(r)] = r;
            r++;
        }
        return maxLen;
    }
}