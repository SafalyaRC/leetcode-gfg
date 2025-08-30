class Solution {
    
    public static void bubbleSort(int[] arr, int n) {   
        
        if(n==1) return; // base case
        for(int j=0;j<=n-2;j++)
        {
            if(arr[j]>arr[j+1])
            {
                int temp=arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
            }
        }
        bubbleSort(arr,n-1);
    }

}

// TC: O(n^2). Reason: If we carefully observe, we can notice that the recursion call, is occurring for n times, and for each recursion call, the loop j runs from 0 to n-2. For, the range of size n, the inner loop runs n-1 times, for the range of size n-1, the inner loop runs n-2 times, and so on. So, the total steps will be approximately the following: (n-1) + (n-2) + (n-3) + ……..+ 3 + 2 + 1. The summation is approximately the sum of the first n natural numbers i.e. (n*(n+1))/2. The precise time complexity will be O(n2/2 + n/2)
// SC: O(n) because of recursive stack space.