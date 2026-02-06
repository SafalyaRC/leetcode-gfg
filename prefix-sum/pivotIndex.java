// LC-724: https://leetcode.com/problems/find-pivot-index/description/

public class pivotIndex {
    public int pivotIndexx(int[] nums) {
        int n=nums.length;
        int prefix[]=new int[n];
        prefix[0]=nums[0];
        for(int i=1;i<n;i++) prefix[i]=prefix[i-1]+nums[i];

        int total=prefix[n-1];
        if(total-prefix[0]==0) return 0;

        for(int i=1;i<n;i++){
            if(total-prefix[i]==prefix[i-1]) return i;
        }
        return -1;
    }
}
