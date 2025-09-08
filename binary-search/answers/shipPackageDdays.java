// LC-1011: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/

//brute-force:
class Solution {
    public int totalDays(int weights[], int capacity) {
        int sumDays = 1, sumWeights = 0;
        for (int i = 0; i < weights.length; i++) {
            sumWeights += weights[i];
            if (sumWeights > capacity) {
                sumDays++;
                sumWeights = weights[i];
            }
        }
        return sumDays;
    }

    public int shipWithinDays(int[] weights, int days) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : weights) {
            sum += num;
            max = Math.max(max, num);
        }

        for (int i = max; i <= sum; i++) {
            if (totalDays(weights, i) <= days)
                return i;
        }
        return -1;
    }
}

// optimal:
class Solution {
    public int totalDays(int weights[], int mid) {
        int sumDays = 1, sumWeights = 0;
        for (int i = 0; i < weights.length; i++) {
            sumWeights += weights[i];
            if (sumWeights > mid) {
                sumDays++;
                sumWeights = weights[i];
            }
        }
        return sumDays;
    }

    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : weights) {
            sum += num;
            max = Math.max(max, num);
        }

        int low = max, high = sum;
        int ans = -1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (totalDays(weights, mid) <= days) {
                ans = mid;
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return ans;
    }
}
