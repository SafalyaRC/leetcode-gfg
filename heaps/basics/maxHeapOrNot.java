// GFG: https://www.geeksforgeeks.org/problems/does-array-represent-heap4345/1

public class maxHeapOrNot {
    public boolean countSub(long arr[], long n) {
        for (int i = (int) (n / 2) - 1; i >= 0; i--) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && arr[left] > arr[i])
                return false;
            if (right < n && arr[right] > arr[i])
                return false;
        }
        return true;
    }
}
