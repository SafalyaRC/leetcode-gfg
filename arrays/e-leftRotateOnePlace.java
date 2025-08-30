class Solution {
    void leftShift(int arr[]) {
        int n = arr.length;
        int temp = arr[i];
        for (int i = 1; i <= n - 1; i++) {
            arr[i - 1] = arr[i];
        }
        arr[n - 1] = temp;
    }
}