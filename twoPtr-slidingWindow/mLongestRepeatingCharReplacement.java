// LC-424: https://leetcode.com/problems/longest-repeating-character-replacement/description/

// brute-force(TLE):
class brute {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int hashChar[] = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                hashChar[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, hashChar[s.charAt(j) - 'A']);
                int charReplacements = (j - i + 1) - maxFreq;
                if (charReplacements <= k) {
                    int len = j - i + 1;
                    maxLen = Math.max(maxLen, len);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}

// better:
class better {
    // use of len-maxFreq formula as we only need to replace the minFreq characters
    // for our answer
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int l = 0, r = 0, maxLen = 0, maxFreq = 0;
        int hashChar[] = new int[26];

        while (r < n) {
            hashChar[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hashChar[s.charAt(r) - 'A']);
            while ((r - l + 1) - maxFreq > k) {
                hashChar[s.charAt(l) - 'A']--;
                maxFreq = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq = Math.max(maxFreq, hashChar[i]);
                }
                l++;
            }
            if ((r - l + 1) - maxFreq <= k) {
                maxLen = Math.max(maxLen, (r - l + 1));
            }
            r++;
        }
        return maxLen;
    }
}

// optimal:
// no need of looping for changing maxFreq as it wont be of use
public class mLongestRepeatingCharReplacement {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int l = 0, r = 0, maxFreq = 0, maxLen = 0;
        int hash[] = new int[26];
        while (r < n) {
            hash[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);

            while ((r - l + 1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--;
                l++;
            }
            if ((r - l + 1) - maxFreq <= k) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }
}
