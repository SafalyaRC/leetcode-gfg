// LC-2220: https://leetcode.com/problems/minimum-bit-flips-to-convert-number/

/*
The core idea is that a bit flip is only required where the binary representations of start and goal differ. Using XOR directly isolates those differences: start ^ goal produces a number whose bits are 1 exactly at positions where the two inputs disagree. Therefore, the problem reduces to counting how many 1s exist in this XOR result — each 1 corresponds to one required flip.

After computing the XOR, the loop simply counts set bits. The expression (xorBits & 1) checks whether the current least-significant bit is 1, and right shifting moves through all bit positions. Conceptually, this is equivalent to computing the Hamming distance between the two integers’ binary forms. Thus, the algorithm transforms a “bit transformation” question into a “count differing bits” problem, which is linear in the number of bits.

TC: O(32) ~ O(1) & SC: O(1)
*/

class minBitFlips {
    public int minBitFlipss(int start, int goal) {
        int xorBits = start ^ goal;
        int count = 0;
        while (xorBits > 0) {
            count += (xorBits & 1);
            xorBits = xorBits >> 1;
        }
        return count;
    }
}