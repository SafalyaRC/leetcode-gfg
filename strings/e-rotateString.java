// LC-796: https://leetcode.com/problems/rotate-string/

//brute-force:
class Solution {
    public boolean rotateString(String s, String goal) {
        char words[] = s.toCharArray();

        for (int k = 0; k < words.length; k++) {
            if (rotateKPlaces(Arrays.copyOf(words, words.length), k).equals(goal))
                return true;
        }
        return false;
    }

    public String rotateKPlaces(char words[], int k) {
        int n = words.length;

        k = k % n;
        reverse(words, 0, k - 1);
        reverse(words, k, n - 1);
        reverse(words, 0, n - 1);

        return new String(words);
    }

    public void reverse(char words[], int left, int right) {
        while (left <= right) {
            char temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
    }
}

// optimal:
class Solution {
    public boolean rotateString(String s, String goal) {
        return (s.length() == goal.length() && (s + s).contains(goal));
    }
}