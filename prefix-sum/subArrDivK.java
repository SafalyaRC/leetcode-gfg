// LC-974: https://leetcode.com/problems/subarray-sums-divisible-by-k/description/

import java.util.*;
public class subArrDivK {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int count = 0, sum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int mod = (k == 0) ? sum : sum % k;

            if (mod < 0)
                mod += k;

            count += map.getOrDefault(mod, 0);
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return count;
    }
}
