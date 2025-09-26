// LC-42: https://leetcode.com/problems/trapping-rain-water/

// better:
class Solution {
    public int trap(int[] height) {
        int totalWaterTrapped = 0;
        int n = height.length;
        int prefix[] = new int[n];
        int suffix[] = new int[n];

        prefix[0] = height[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }
        suffix[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            totalWaterTrapped += Math.min(prefix[i], suffix[i]) - height[i];
        }
        return totalWaterTrapped;
    }
}

// optimal:
public class trappingRainwater {
    public int trap(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int lMax = 0, rMax = 0;
        int trappedWater = 0;

        while (l < r) {
            if (height[l] <= height[r]) {
                if (lMax > height[l])
                    trappedWater += lMax - height[l];
                else
                    lMax = height[l];
                l++;
            } else {
                if (rMax > height[r])
                    trappedWater += rMax - height[r];
                else
                    rMax = height[r];
                r--;
            }
        }
        return trappedWater;
    }
}
