// LC-69: https://leetcode.com/problems/sqrtx/description/

// brute-force:
class Solution {
    public int mySqrt(int x) {
        long ans = -1;
        for (long i = 0; i <= (long) x; i++) {
            if (i * i <= x)
                ans = i;
            else
                break;
        }
        return (int) ans;
    }
}

// optimal:
class Solution {
    public int mySqrt(int x) {
        long low = 0, high = x;
        long ans = -1;

        while (low <= high) {
            long mid = low + ((high - low) / 2);
            if (mid * mid <= (long) x) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return (int) ans;
    }
}