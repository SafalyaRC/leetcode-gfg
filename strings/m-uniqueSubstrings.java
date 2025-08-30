// GFG: https://www.geeksforgeeks.org/problems/count-number-of-substrings4528/1

// brute-force:
class Solution {
    public int countSubstr(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> uniqueSet = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                uniqueSet.add(s.charAt(j));
                if (uniqueSet.size() == k)
                    count++;
                else if (uniqueSet.size() > k)
                    break;
            }
        }
        return count;
    }
}

// optimal (sliding window approach to be covered):