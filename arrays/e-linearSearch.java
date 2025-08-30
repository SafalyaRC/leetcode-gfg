// gfg problem: https://www.geeksforgeeks.org/problems/search-an-element-in-an-array-1587115621/1

class Solution {
    public int search(int arr[], int key) {
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                pos = i;
                break;
            }
        }

        return pos;

    }
}