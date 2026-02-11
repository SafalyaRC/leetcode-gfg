// LC-1331: https://leetcode.com/problems/rank-transform-of-an-array/description/

import java.util.*;
public class rankTransform {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int temp[] = arr.clone();
        Arrays.sort(temp);

        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(temp[i])) {
                map.put(temp[i], rank++);
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}
