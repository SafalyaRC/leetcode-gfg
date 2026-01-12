// GFG: https://www.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1

public class minimumCoins {
    public int findMin(int n) {
        int coins[] = { 10, 5, 2, 1 };
        int i = 0, minCoins = 0;

        while (n > 0) {
            if (i == 5)
                return -1;
            int diff = n - coins[i];
            if (diff >= 0) {
                n = diff;
                minCoins++;
            } else
                i++;
        }
        return minCoins;
    }
}
