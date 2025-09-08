// GFG: https://www.geeksforgeeks.org/problems/rotation4723/1

// brute-force:
class Solution {
    public int findKRotation(int arr[]) {
        // Code here
        for(int i=arr.length-2;i>=0;i--)
        {
            if(arr[i]>arr[i+1]) return i+1;
        }
        return 0;
    }
}

// optimal:
