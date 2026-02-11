// GFG: https://www.geeksforgeeks.org/problems/convert-min-heap-to-max-heap-1666385109/1

/*
You do NOT convert a Min Heap to a Max Heap by “reversing” or doing local swaps.You rebuild the heap using Max-Heap heapify, starting from the last internal node, exactly like building a heap from an array.

Why this works: A heap is defined by its heap property, not by how it was previously arranged. Any array can be turned into a Max Heap in O(N) using bottom-up heapify.

Correct Approach (High-Level):
- Start from the last non-leaf node → (N/2) - 1
- Apply max-heap heapify at each index
- Move upwards to the root

This ensures: Children are fixed before parents and Heap property is satisfied globally
*/

public class minToMaxHeap {
    static void convertMinToMaxHeap(int N, int arr[]) {
        for (int i = (N / 2) - 1; i >= 0; i--) { // start from the last internal node at (N/2)-1 th index
            maxHeapifyDown(arr, i, N);
        }
    }

    // heapify down function according to max heap
    static void maxHeapifyDown(int arr[], int i, int size) {
        while (true) {
            int largest = i;
            int l = 2 * i + 1, r = 2 * i + 2;

            if (l < size && arr[l] > arr[largest])
                largest = l;
            if (r < size && arr[r] > arr[largest])
                largest = r;

            if (largest == i)
                break;

            swap(arr, i, largest);
            i = largest;
        }
    }

    static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
