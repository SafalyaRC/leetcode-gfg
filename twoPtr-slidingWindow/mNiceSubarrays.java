// LC-1248: https://leetcode.com/problems/count-number-of-nice-subarrays/

// brute-force:
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 != 0)
                    count++;
                if (count == k) {
                    oddCount++;
                } else if (count > k)
                    break;
            }
        }
        return oddCount;
    }
}

// optimal:
public class mNiceSubarrays {
    public int lessThanEqualKOdd(int nums[], int k){
        int n=nums.length;
        int l=0,r=0,count=0;
        while(r<n){
            if(nums[r]%2==1) k--;
            while(k<0){
                if(nums[l]%2!=0) k++;
                l++;
            }
            count+=(r-l+1);
            r++;
        }
        return count;
    }
    public int numberOfSubarrays(int[] nums, int k) {
        return lessThanEqualKOdd(nums,k)-lessThanEqualKOdd(nums,k-1);
    }
}
