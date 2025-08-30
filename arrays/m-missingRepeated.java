// GFG: https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1

// better:
class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int missing = -1, repeating = -1;

        for (int i = 1; i <= n; i++) {
            // First, check if the key exists at all.
            if (map.containsKey(i)) {
                // If it exists, *then* check its value.
                if (map.get(i) > 1) {
                    repeating = i;
                }
            }
            // If the key does not exist, it must be the missing number.
            else {
                missing = i;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(repeating);
        ans.add(missing);
        return ans;
    }
}

// optimal:
class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        int n = arr.length;
        int sum = 0, sumSquare = 0, sumArray = 0, sumArraySquare = 0;
        int diff1 = -1, diff2 = -1;
        int a = -1, b = -1;

        for (int num : arr) {
            sumArray += num;
            sumArraySquare += (num * num);
        }

        for (int i = 1; i <= n; i++) {
            sum += i;
            sumSquare += (i * i);
        }

        diff1 = sumArray - sum;
        diff2 = sumArraySquare - sumSquare;

        int aPlusb = diff2 / diff1;

        b = (aPlusb + diff1) / 2;
        a = aPlusb - b;

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(b);
        ans.add(a);
        return ans;
    }
}
