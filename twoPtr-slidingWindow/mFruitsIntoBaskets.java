// LC-904: https://leetcode.com/problems/fruit-into-baskets/description/

// brute-force (TLE):

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class brute {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(fruits[j]);
                if (set.size() > 2)
                    break;
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}

// better:
class better {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int l = 0, r = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (r < n) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            if (map.size() > 2) {
                while (map.size() > 2) {
                    map.put(fruits[l], map.get(fruits[l]) - 1);
                    if (map.get(fruits[l]) == 0)
                        map.remove(fruits[l]);
                    l++;
                }
            }
            if (map.size() <= 2) {
                int len = r - l + 1;
                maxLen = Math.max(maxLen, len);
            }
            r++;
        }
        return maxLen;
    }
}

// optimal:
class mFruitsIntoBaskets {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int l = 0, r = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (r < n) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            if (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0)
                    map.remove(fruits[l]);
                l++;
            }
            if (map.size() <= 2) {
                int len = r - l + 1;
                maxLen = Math.max(maxLen, len);
            }
            r++;
        }
        return maxLen;
    }
}