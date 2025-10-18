// LC-992: https://leetcode.com/problems/subarrays-with-k-different-integers/description/

// brute-force(TLE):
import java.util.*;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() > k)
                    break;
                else if (set.size() == k)
                    count++;
            }
        }
        return count;
    }
}

// optimal:
public class hGoodSubarrays {
    public int subArrayLessEqualKDistinct(int nums[], int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        int count = 0;

        while (r < n) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.size() > k) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0)
                    map.remove(nums[l]);
                l++;
            }
            if (map.size() <= k) {
                count += (r - l + 1);
            }
            r++;
        }
        return count;
    }

    // for slidingWindow-twoPtr problems, use the below formula whenever you encounter "exactly equal" conditions
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subArrayLessEqualKDistinct(nums, k) - subArrayLessEqualKDistinct(nums, k - 1);
    }
}
