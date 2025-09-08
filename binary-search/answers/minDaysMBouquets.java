// LC-1482: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/

// brute force:
class Solution {
    public int totalBouquetsFormed(int bloomDay[], int days, int k) {
        int count = 0, sumBouquets = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (days >= bloomDay[i])
                count++;
            else {
                sumBouquets += count / k;
                count = 0;
            }
        }
        sumBouquets += count / k;
        return sumBouquets;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k <= n) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int num : bloomDay) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }

            for (int i = min; i <= max; i++) {
                if (totalBouquetsFormed(bloomDay, i, k) >= m)
                    return i;
            }
        }
        return -1;
    }
}

// optimal:
class Solution {
    public int bouquetsFormed(int bloomDay[], int minDays, int k) {
        int count = 0, totalBouquets = 0;
        for (int num : bloomDay) {
            if (minDays >= num)
                count++;
            else {
                totalBouquets += count / k;
                count = 0;
            }
        }
        totalBouquets += count / k;
        return totalBouquets;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n)
            return -1;

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE, ans = -1;
        for (int num : bloomDay) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (bouquetsFormed(bloomDay, mid, k) >= m) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}