// LC-137: https://leetcode.com/problems/single-number-ii/description/

/*
The key observation is rooted in the problem constraint: Every element appears exactly 3 times, except one element that appears once. Instead of tracking numbers directly, we exploit bit-level frequency aggregation.

Think column-wise in binary: 
- For each bit position (0 → 31), count how many numbers have that bit set.
- Since most numbers appear three times, their contribution to any bit count will be a multiple of 3.
- The only disturbance in this pattern comes from the unique number.

So after counting:
- If count % 3 == 0 → all contributions came from triplets.
- If count % 3 == 1 → that bit belongs to the single number.
- Thus, we reconstruct the answer bit by bit.

TC: O(32*n) ~ O(n) & SC: O(1)
*/

class singleNumberII {
    public int singleNumber(int[] nums) {
        int n = nums.length, ans = 0;
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if ((nums[i] & (1 << bitIndex)) != 0) { // check if the bitIndex bit is set for all nums[] elements
                    count++;
                }
            }
            if (count % 3 == 1)
                ans |= (1 << bitIndex); // set the bitIndex bit if that bit belongs to the number appearing once
        }
        return ans;
    }
}