// LC-1922: https://leetcode.com/problems/count-good-numbers/description/

class countGoodNumbers {
    public long helper(long x, long n, long mod) {
        long ans = 1;
        while (n > 0) {
            if (n % 2 != 0) {
                ans = (ans * x) % mod;
                n--;
            } else {
                x = (x * x) % mod;
                n = n / 2;
            }
        }
        return ans;
    }

    public int countGoodNumbers(long n) {
        if (n == 1)
            return 5;
        long even = (n + 1) / 2;
        long odd = (n / 2);
        long mod = 1000000007;
        return (int) (helper(5, even, mod) * helper(4, odd, mod) % mod);
    }
}
