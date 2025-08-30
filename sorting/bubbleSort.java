class Solution {
    public void bubbleSort(int[] arr) {
        // code here
        int n = arr.length;
        boolean swapped = false; // for optimization

        for (int i = 0; i <= n - 2; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // optimization: if no two elements sorted in a pass, break out of the loop as
            // the elements are already sorted
            if (swapped == false)
                break;
        }
    }
}

// Time Complexity: O(N^2) in worst and average case, O(N) in best case (when array is already sorted but still takes a complete pass to figure whether it is or not)
// Space Complexity: O(1) as no extra space is used