// LC-875: https://leetcode.com/problems/koko-eating-bananas/description/

// brute-force:
class Solution {
    public int bananaPerHour(int piles[], int k) {
        int sum = 0;
        for (int num : piles) {
            sum += Math.ceil((double) num / (double) k);
        }
        return sum;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = Integer.MIN_VALUE;
        for (int num : piles) {
            max = Math.max(max, num);
        }
        for (int i = 1; i <= max; i++) {
            if (bananaPerHour(piles, i) <= h)
                return i;
        }
        return -1;
    }
}

// optimal:
class Solution {
    public int requiredHours(int piles[], long mid) {
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += Math.ceil((double) piles[i] / (double) mid);
        }
        return sum;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = Integer.MIN_VALUE;
        for (int num : piles) {
            max = Math.max(max, num);
        }
        int low = 1, high = max;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (requiredHours(piles, mid) <= h) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}