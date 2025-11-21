// LC-50: https://leetcode.com/problems/powx-n/

public class powxn {
    public double myPow(double x, int n) {
        double ans = 1.0;
        long exp = n;
        if (n < 0)
            exp = -1 * exp;
        while (exp > 0) {
            if (exp % 2 == 1) {
                ans *= x;
                exp--;
            } else {
                exp = exp / 2;
                x = x * x;
            }
        }
        if (n < 0)
            ans = 1.0 / ans;
        return ans;
    }
}
