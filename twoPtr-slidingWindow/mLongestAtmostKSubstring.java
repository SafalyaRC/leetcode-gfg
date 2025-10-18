// link: https://www.naukri.com/code360/problems/distinct-characters_2221410?leftPanelTabValue=PROBLEM

// brute-force:
import java.util.*;

public class Solution {

    public static int kDistinctChars(int k, String str) {
        int maxCount = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            int count = 0;
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(str.charAt(j));
                if (set.size() > k)
                    break;
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

}

// optimal:
public class mLongestAtmostKSubstring {
    public static int kDistinctChars(int k, String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, maxLen = 0;
        while (r < str.length()) {
            map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);
            while (map.size() > k) {
                map.put(str.charAt(l), map.get(str.charAt(l)) - 1);
                if (map.get(str.charAt(l)) == 0)
                    map.remove(str.charAt(l));
                l++;
            }
            if (map.size() <= k) {
                maxLen = Math.max(maxLen, (r - l + 1));
            }
            r++;
        }
        return maxLen;
    }
}
