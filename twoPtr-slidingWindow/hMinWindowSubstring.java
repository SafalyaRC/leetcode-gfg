// LC-76: https://leetcode.com/problems/minimum-window-substring/description/

// brute-force(TLE):
class Solution {
    public String minWindow(String s, String t) {
        int start = 0, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int hash[] = new int[256]; // hash array for pre-insertion
            int count = 0; // count variable to store how many characters we have picked from t
            for (int j = 0; j < t.length(); j++) {
                hash[t.charAt(j)]++; // pre-insert t characters
            }
            for (int j = i; j < s.length(); j++) {
                if (hash[s.charAt(j)] > 0)
                    count++; // increment when we find pre-inserted characters of t in s substring
                hash[s.charAt(j)]--; // insert into map and decrement
                if (count == t.length()) { // when we find all letters of t in s, check whether the substring of s is minimum or not
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        start = i;
                    }
                    break;
                }
            }
        }
        if (minLen == Integer.MAX_VALUE)
            return "";
        return s.substring(start, start + minLen);
    }
}

// optimal:
public class hMinWindowSubstring {
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        int l = 0, r = 0, start = 0, count = 0;
        int minLen = Integer.MAX_VALUE;
        int hash[] = new int[256];

        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)]++;
        }

        while (r < n) {
            if (hash[s.charAt(r)] > 0)
                count++;
            hash[s.charAt(r)]--;
            while (count == m) {
                if ((r - l + 1) < minLen) {
                    start = l;
                    minLen = r - l + 1;
                }
                hash[s.charAt(l)]++; // when we are shrinking the window size
                // below condition so that after shrinking we dont lose a character from 't':
                if (hash[s.charAt(l)] > 0)
                    count--; // After removing this character, did I lose a character that was actually required for t? (eg: ddaaabbca)
                l++;
            }
            r++;
        }
        if (minLen == Integer.MAX_VALUE)  // edge case
            return "";
        return s.substring(start, start + minLen);
    }
}

// When we remove a char from the left, we “give it back” to the need list (hash++). If that makes it positive, it means we’re missing a required char → reduce count. Charcters not in t, will be increased to zero in while loop not more than that 
