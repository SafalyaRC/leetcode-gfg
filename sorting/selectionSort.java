class Solution {
    void selectionSort(int[] arr) {
        // code here
        int n = arr.length;

        for (int i = 0; i <= n - 2; i++) // till n-2 because the last element is already sorted if the rest are
        {
            int min = i;
            for (int j = i; j < n; j++) // we use j loop to find minimum and swap it with ith index for sorting
            {
                if (arr[j] < arr[min])
                    min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }
}

// SC: O(1)
// TC: O(N^2) in best, average and worst case, because if we carefully observe, we can notice that the outer loop, say i, is running from 0 to n-2 i.e. n-1 times, and for each i, the inner loop j runs from i to n-1. For, i = 0, the inner loop runs n-1 times, for i = 1, the inner loop runs n-2 times, and so on. So, the total steps will be approximately the following: (n-1) + (n-2) + (n-3) + ……..+ 3 + 2 + 1. The summation is approximately the sum of the first n natural numbers i.e. (n*(n+1))/2. The precise time complexity will be O(n2/2 + n/2)