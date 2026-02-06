// LC-523: https://leetcode.com/problems/continuous-subarray-sum/description/

/*
If two prefix sums leave the same remainder when divided by k, the subarray between them has a sum divisible by k; we store the earliest index of each remainder and check if any repeat occurs with a gap of at least 2. (read note for the proof)

(0, -1) represents a virtual prefix sum before the array begins, enabling detection of subarrays starting at index 0, also the k == 0 case replaces modulo logic with repeated-prefix detection because only sum 0 can be divisible by 0.`
*/

/*
Let:
prefix[j] = sum(0 → j)
prefix[i] = sum(0 → i)

Subarray sum from i+1 → j: subSum = prefix[j] − prefix[i]

Key property of modulo: If two numbers leave the same remainder when divided by k:

prefix[j] % k = prefix[i] % k

Then:
prefix[j] = a·k + r
prefix[i] = b·k + r

Subtract:
prefix[j] − prefix[i] = (a−b)·k, Which is a multiple of k.

Meaning in array terms: Same remainder ⇒ their difference is divisible by k ⇒ the subarray between them has sum divisible by k.
*/

import java.util.*;
class continuosSubArraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2)
            return false;

        Map<Integer, Integer> map = new HashMap<>(); // <cumulative sum, it's earliest index>
        map.put(0, -1); // to check subarrays starting from 0th index
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int mod = (k == 0) ? sum : sum % k; // k==0 we just need to track the sum as k=0 will be completely divisble
            if (mod < 0)
                mod += k; // this line normalizes the remainder so all equivalent modular values map to
                          // the same bucket.

            if (map.containsKey(mod)) {
                int len = i - map.get(mod);
                if (len >= 2)
                    return true;
            } else
                map.put(mod, i);
        }
        return false;
    }
}