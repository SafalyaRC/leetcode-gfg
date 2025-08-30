// gfg problem: https://www.geeksforgeeks.org/problems/frequency-of-elements--111353/1
/*Given an array arr[] of positive integers which may contain duplicate elements, return the frequency of each distinct element.

Examples:
Input: arr[] = [1, 2, 2, 3, 3, 5]
Output: [[1, 1], [2, 2], [3, 2], [5, 1]] (return a nested arraylist)
Explaination: Here element 1 and 5 occur 1 times, 2 and 3 occur 2 times.*/

// without using hashing:
import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> countFreq(int[] arr) {
        int n = arr.length;

        boolean visited[] = new boolean[n];
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (visited[i] == true)
                continue;
            int count = 1;

            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    visited[j] = true;
                    count++;
                }
            }
            ArrayList<Integer> pair = new ArrayList<>();
            pair.add(arr[i]);
            pair.add(count);
            result.add(pair);
        }

        return result;

    }
}

// using hashmaps:
class Solution {
    public ArrayList<ArrayList<Integer>> countFreq(int[] arr) {
        // code here
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(new ArrayList<>(Arrays.asList(entry.getKey(), entry.getValue())));
        }

        return result;
    }
}
