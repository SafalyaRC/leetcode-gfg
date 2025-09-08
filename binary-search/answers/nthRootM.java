// link: https://www.naukri.com/code360/problems/nth-root-of-m_1062679

// brute-force:
class Solution {
    public static int NthRoot(int n, int m) {
        for (int i = 0; i <= m; i++) {
            if (Math.pow(i, n) == m)
                return i;
        }
        return -1;
    }
}

// optimal-I:
class Solution {
    public static int NthRoot(int n, int m) {
        long low = 1, high = m;
        while (low <= high) {
            long mid = low + ((high - low) / 2);
            if ((long) Math.pow(mid, n) == (long) m)
                return (int) mid;
            else if ((long) Math.pow(mid, n) < (long) m)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}

// optimal-II:
class Solution {
    public static int f(int mid, int n, int x) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= mid;
            if (ans > x)
                return 2;
        }
        if (ans == x)
            return 1;
        return 0;
    }

    public static int NthRoot(int n, int m) {
        int low = 1, high = m;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (f(mid, n, m) == 1)
                return mid;
            else if (f(mid, n, m) == 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
