// second largest element in an array: https://www.geeksforgeeks.org/problems/second-largest3735/1

// brute-force:
class Solution {
    public int getSecondLargest(int[] arr) {
        
        int n=arr.length;
        Arrays.sort(arr);
        int max=arr[n-1];
        int sMax=-1;
        
        for(int i=n-2;i>=0;i--)
        {
            if(arr[i]!=max){
                sMax=arr[i];
                break;
            }
        }
        return sMax;    
    }
}

// better:
class Solution {
    public int getSecondLargest(int[] arr) {
        
        int n=arr.length;
        int max=Integer.MIN_VALUE;
        int sMax=-1;
        
        for(int i=0;i<n;i++)
        {
            if(arr[i]>max) max=arr[i];
        }
        
        for(int i=0;i<n;i++)
        {
            if(arr[i]>sMax && arr[i]!=max){
                sMax=arr[i];
            }
        }
        
        return sMax;
    }
}

// optimal:
class Solution {
    public int getSecondLargest(int[] arr) {
        
        if (arr.length < 2) {
            return -1; 
        }
        
        int n=arr.length;
        int max=Integer.MIN_VALUE;
        int sMax=Integer.MIN_VALUE;
        
        for(int i=0;i<n;i++)
        {
            if(arr[i]>max) max=arr[i];
        }
        
        for(int i=0;i<n;i++)
        {
            if(arr[i]>max){
                sMax=max;
                max=arr[i];
            }
            else if(arr[i]>sMax && arr[i]!=max){
                sMax=arr[i];
            } 
        }
        
        if(sMax==Integer.MIN_VALUE) sMax=-1;
        
        return sMax;
    }
}