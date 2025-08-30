class Solution {
    // Please change the array in-place
    public void insertionSort(int arr[]) {
        // code here
        int n=arr.length;
        
        for(int i=0;i<n;i++)
        {
            int j=i;
            while(j>0 && arr[j-1]>arr[j])
            {
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
        }
    }
}

// Time Complexity: O(N^2) in worst and average case, O(N) in best case (when array is already sorted). If we carefully observe, we can notice that the outer loop, say i, is running from 0 to n-1 i.e. n times, and for each i, the inner loop j runs from i to 1 i.e. i times. For, i = 1, the inner loop runs 1 time, for i = 2, the inner loop runs 2 times, and so on. So, the total steps will be approximately the following: 1 + 2 + 3 +......+ (n-2) + (n-1). The summation is approximately the sum of the first n natural numbers i.e. (n*(n+1))/2. The precise time complexity will be O(n2/2 + n/2). 