// LC-260: https://leetcode.com/problems/single-number-iii/description/

/*
All numbers appear twice except two unique numbers. The strategy is to leverage XOR cancellation + bit partitioning.
First, XOR all elements: xor = a ^ b. Because duplicate numbers cancel out (x ^ x = 0), the final XOR is effectively the XOR of the two unique numbers only.

Now the key insight: If a ≠ b, then xor must have at least one bit set. That set bit represents a position where a and b differ.

Why find the rightmost set bit?: rightMost = (xor & (xor - 1)) ^ xor 
This isolates the lowest differing bit between the two unique numbers.

Meaning, At this bit position:
- One unique number has bit = 1
- The other has bit = 0
- This gives a perfect partition rule.

So we split the array into:
- Bucket 1 → numbers with that bit set
- Bucket 2 → numbers without that bit set
- Now duplicates still cancel inside their respective buckets, but:
 - a goes to one bucket
 - b goes to the other
- Thus XOR inside each bucket gives the two answers.

TC: O(n) & SC: O(1)
*/

public class singleNumberIII {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums)
            xor ^= num;

        int rightMost = (xor & xor - 1) ^ xor; // number which has rightmost bit set as 1 to differentiate b/w buckets
        int bucket1 = 0, bucket2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & rightMost) != 0)
                bucket1 ^= nums[i]; // to figure out if the rightmost bit of nums[i] is set?
            else
                bucket2 ^= nums[i];
        }
        return new int[] { bucket1, bucket2 };
    }
}
