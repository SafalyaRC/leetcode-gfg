// LC-152: https://leetcode.com/problems/maximum-product-subarray/description/

//brute:
class Solution {
    public int maxProduct(int[] nums) {
        int prod = 1;
        int maxProd = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prod = 1;
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                maxProd = Math.max(maxProd, prod);
            }
        }
        return maxProd;
    }
}

// optimal-I:
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int prefixProd = 1, suffixProd = 1;
        int maxProd = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (prefixProd == 0)
                prefixProd = 1;
            if (suffixProd == 0)
                suffixProd = 1;

            prefixProd *= nums[i];
            suffixProd *= nums[n - i - 1];

            maxProd = Math.max(maxProd, Math.max(prefixProd, suffixProd));
        }
        return maxProd;
    }
}

// optimal-II (kadane's algo):
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int curMax = 1, curMin = 1;
        int ans = nums[0];

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                curMax = 1;
                curMin = 1;
                ans = Math.max(ans, 0);
                continue;
            }

            int temp = curMax * nums[i];

            curMax = Math.max(nums[i], Math.max(temp, curMin * nums[i]));
            curMin = Math.min(nums[i], Math.min(temp, curMin * nums[i]));

            ans = Math.max(ans, curMax);
        }

        return ans;
    }
}