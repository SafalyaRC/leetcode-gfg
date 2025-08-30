// GFG : https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1

// brute force : 
class Solution {
    static int inversionCount(int arr[]) {
        // Code Here
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j])
                    count++;
            }
        }
        return count;
    }
}

// optimal (merge sort):
class Solution {
    static int merge(int arr[], int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        List<Integer> temp = new ArrayList<>();
        int count = 0;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right])
                temp.add(arr[left++]);
            else {
                count += (mid - left + 1);
                temp.add(arr[right++]);
            }
        }
        while (left <= mid)
            temp.add(arr[left++]);
        while (right <= high)
            temp.add(arr[right++]);

        for (int i = low; i <= high; i++)
            arr[i] = temp.get(i - low);

        return count;
    }

    static int mergeSort(int arr[], int low, int high) {
        int mid = (low + high) / 2;
        int count = 0;

        if (low >= high)
            return count;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += merge(arr, low, mid, high);

        return count;
    }

    static int inversionCount(int arr[]) {
        // Code Here
        return mergeSort(arr, 0, arr.length - 1);
    }
}
