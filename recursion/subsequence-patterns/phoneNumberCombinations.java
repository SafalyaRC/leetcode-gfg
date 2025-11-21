// LC-17: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

// TC: O(n*4^n), as each digit can map upto 4 characters of each length n and worst-case we get digits="99", making it 4^n
// SC: O(n) recursive stack depth

class Solution {
    String map[];

    // predefine a map for all digits->letter:
    public Solution() {
        map = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    }

    public void backtrack(String digits, List<String> ans, String current, int index) {
        // base case:
        if (index == digits.length()) {
            ans.add(current);
            return;
        }
        // get the letters according to the specified digit
        String letters = map[digits.charAt(index) - '0'];
        // loop through for adding the combinations to current string
        for (int i = 0; i < letters.length(); i++) {
            backtrack(digits, ans, current + letters.charAt(i), index + 1); // recursive call
            // no need to remove last letter of string, as strings are immutable (cant modify, only create new)
            // we usually revert state after recursive calls only when we modify a shared,
            // mutable structure like a list or stringbuilder
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0)
            return ans;
        backtrack(digits, ans, "", 0);
        return ans;
    }
}