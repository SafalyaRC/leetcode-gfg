// LC-268 Missing Number : https://leetcode.com/problems/missing-number/description/

// brute-force:
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, i;
        Arrays.sort(nums);
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                break;
            }
        }
        return i;
    }
}

// optimal-I: sum of first 'n' numbers-sum of array elements
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, s = 0;
        for (int i = 0; i < n; i++) {
            s += nums[i];
        }
        int nsum = (n * (n + 1)) / 2;
        return (nsum - s);
    }
}

// optimal-II: xor of first 'n' numbers-xor of array elements
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            xor ^= i;
            xor ^= nums[i];
        }
        xor ^= n;
        return xor;
    }
}