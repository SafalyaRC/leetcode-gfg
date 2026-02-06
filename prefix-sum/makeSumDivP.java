// LC-1590: https://leetcode.com/problems/make-sum-divisible-by-p/description/

// the maths for the modulo arithmetic used is given below:

/*
1️⃣ Restate the objective You can remove one subarray. After removal: ( total sum − subarray sum ) m o d 𝑝 = 0 (total sum−subarray sum)modp=0 

2️⃣ Rearrange equation Let total sum = S . We need: ( 𝑆 − 𝑠 𝑢 𝑏 ) % 𝑝 = 0 ⇒ 𝑠 𝑢 𝑏 % 𝑝 = 𝑆 % 𝑝 
Call:  target=S%p 
So we need the shortest subarray whose sum % p = target. 

3️⃣ Use Prefix Sums Modulo Let: 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑖 ] = ( 𝑛 𝑢 𝑚 𝑠 [ 0 ] + . . . + 𝑛 𝑢 𝑚 𝑠 [ 𝑖 ] ) % 𝑝 prefix[i]
Subarray sum from (j+1 … i): ( 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑖 ] − 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑗 ] ) % 𝑝
We need: ( 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑖 ] − 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑗 ] ) % 𝑝 = 𝑡 𝑎 𝑟 𝑔 𝑒 𝑡 

4️⃣ Rearranged lookup 𝑝𝑟𝑒𝑓𝑖𝑥 [ 𝑗 ] = ( 𝑝 𝑟 𝑒 𝑓 𝑖 𝑥 [ 𝑖 ] − 𝑡 𝑎 𝑟 𝑔 𝑒 𝑡 + 𝑝 ) % 𝑝 . So for each i, we need a previous prefix remainder equal to that. 

5️⃣ Data structure Use a HashMap: remainder → index Store the latest index to minimize subarray length. 

6️⃣ Edge Case If target == 0, return 0 (already divisible).

7️⃣ Important Java detail Always normalize modulo: ((x % p) + p) % p Because prefix differences can go negative. 

8️⃣ Complexity Target O(n) time, O(p) space. 

9️⃣ Pattern Recognition This is: Shortest subarray with sum ≡ K (mod p) Prefix sum + hashmap, like "subarray sum = K" but under modulo arithmetic.
*/

import java.util.*;
class makeSumDivP {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0;
        for (int num : nums)
            total += num;

        int needed = (int) (total % p);
        if (needed == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // we need prefix sum before 0th index
        long sum = 0;
        int minLen = n; // we initialize using n and not INT_MAX because we search the shortest array to remove and removing the whole array (len=n) is illegal as stated in the question

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int curr = (int) (sum % p); // current subarray sum % p
            int target = (curr - needed + p) % p; // logic explained in notes

            if (map.containsKey(target)) {
                int len = i - map.get(target);
                minLen = Math.min(minLen, len);
            }
            map.put(curr, i); // no need for the if(!map.containsKey(curr)) as we need the min sub array not max, so we keep updating curr accordingly with updated index
        }
        return (minLen == n) ? -1 : minLen;
    }
}