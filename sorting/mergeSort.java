import java.util.*;

class Main {

    // Block 1: The main method
    public static void main(String[] args) {
        int arr[] = { 5, 4, 3, 2, 1, 9, 0, 6 };
        Main ob = new Main();

        // Start the sorting process on the entire array (from index 0 to length - 1)
        ob.mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    // Block 2: The mergeSort method (recursive)
    public void mergeSort(int arr[], int low, int high) {
        // Base case: if the segment has 0 or 1 elements, it's already sorted
        if (low >= high) {
            return;
        }

        // Find the middle point to divide the array into two halves
        int mid = low + (high - low) / 2;

        // Recursively sort the first and second halves
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        // Merge the sorted halves
        merge(arr, low, mid, high);
    }

    // Block 3: The merge method
    public void merge(int arr[], int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low; // Starting index of the first subarray
        int right = mid + 1; // Starting index of the second subarray

        // Compare elements from both subarrays and add the smaller one to temp
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // Copy any remaining elements from the left subarray
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        // Copy any remaining elements from the right subarray
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // Copy the sorted elements from temp back into the original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }
}

// Time Complexity: O(n log n) in all cases (best, average, worst)
// Space Complexity: O(n) due to the the arraylist used for merging