// LC-525: https://leetcode.com/problems/contiguous-array/description/

// we need equal 1s=0s so it's more of a balancing problem rather than a sum tracking one (read note for details)
// hence we treat 0s as -1 because we need to balance em out

/*
1️⃣ What the problem really asks Find the longest subarray with: #0s == #1s
That’s a balance condition, not a sum condition.

2️⃣ The Transformation (Critical Insight)
We convert the array:
0 → -1  
1 → +1

Now the condition:
#1s == #0s
becomes: sum of subarray = 0. Because each 1 adds +1 and each 0 adds −1 → they cancel.

3️⃣ What sum Represents

sum is the balance:

sum > 0 → more 1s  
sum < 0 → more 0s  
sum = 0 → equal number

4️⃣ Why Same Prefix Sum Means Valid Subarray

Let:
prefix[j] = sum(0→j)
prefix[i] = sum(0→i)

If:
prefix[j] == prefix[i]

Then:
prefix[j] − prefix[i] = 0

So subarray (i+1 … j) has sum 0 → equal 0s and 1s.

5️⃣ Role of the HashMap
Map<balance, earliest index>
We store the first time each balance occurred.

Why first?
Earlier index → longer subarray.

6️⃣ Why map.put(0, -1) is Needed

This handles subarrays starting from index 0.

Example:
nums = [0,1]
converted → [-1,+1]
prefix sums: [-1,0]

At index 1, sum = 0.
Length = 1 - (-1) = 2
Without (0, -1) we would miss it.

7️⃣ What the Loop Does
For each index: Update balance
If balance seen before → subarray between previous index and now has equal 0s and 1s, Update max length

8️⃣ Example
nums = [0,1,0]
converted → [-1,+1,-1]
prefix sums → [-1,0,-1]

Balance -1 repeats at i=0 and i=2:
Subarray (1..2) → [1,0] → equal.

9️⃣ Complexity
Metric	Value
Time	O(n)
Space	O(n)

🔟 Interview Intuition (Core): Convert the problem of equal 0s and 1s into finding the longest subarray with sum zero by mapping 0→−1 and 1→+1, then use a hashmap to track the first occurrence of each prefix sum balance.
*/

import java.util.*;

class contiguous01Subarr {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 0;

        int sum = 0, maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // so that when we get a sum=0 at index i, we dont miss the subarrays starting at index 0 as length becomes i-map.get(0) = i-(-1) = i+1

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                sum--; // we treat 0s as -1
            else
                sum++; // we treat 1s as it is, so in the end we cancel +1-1 and we just track k=0 in hashmap

            if (map.containsKey(sum)) {
                int len = i - map.get(sum);
                maxLen = Math.max(maxLen, len);
            } else
                map.put(sum, i);
        }
        return maxLen;
    }
}