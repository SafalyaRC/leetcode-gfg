class Solution {
    public void quickSort(int[] arr, int low, int high) {
        // code here
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {

        int pivot = arr[low];
        int i = low;
        int j = high;

        while (i < j) {
            while (i <= high && arr[i] <= pivot)
                i++;
            while (j >= low && arr[j] > pivot)
                j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }
}

// TC: O(n log n) on average, O(n^2) in the worst case when the pivot is the smallest or largest element consistently (array sorted already). Instead of splitting the problem in half at each step (the ideal scenario), we are only reducing the problem size by one element.The total amount of work done is the sum of the work at each step: N+(N−1)+(N−2)+...+1=N(N+1)/2=O(N^2)

// SC: O(log n) due to recursion stack