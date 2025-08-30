class Solution {

    public static void insertionSort(int n, int i, int[] arr) {
        i = 0;

        if (i == n)
            return; // base case
        int j = i;

        while (j > 0 && arr[j - 1] > arr[j]) {
            int temp = arr[j - 1];
            arr[j - 1] = arr[j];
            arr[j] = temp;
            j--;
        }

        insertionSort(n, i + 1, arr);
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n) (due to recursion stack)