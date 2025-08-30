// LC-151: https://leetcode.com/problems/reverse-words-in-a-string/description/

class Solution {
    public String reverseWords(String s) {
        String words[] = s.split("\\s+");
        StringBuilder ans = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            ans.append(words[i]);
            if (i != 0)
                ans.append(" ");
        }
        return ans.toString().trim();
    }
}

// alternate approach:
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        String ans = "", temp = "";
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) != ' ') {
                temp += s.charAt(i);
            } else if (s.charAt(i) == ' ') {
                if (s.charAt(i + 1) == ' ')
                    continue;
                if (ans.equals(""))
                    ans = temp;
                else {
                    ans = temp + " " + ans;
                }
                temp = "";
            }
        }

        if (!temp.equals("")) {
            if (ans.equals(""))
                ans = temp;
            else
                ans = temp + " " + ans;
        }

        return ans;
    }
}