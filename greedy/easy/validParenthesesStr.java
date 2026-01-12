// LC-678: https://leetcode.com/problems/valid-parenthesis-string/description/

// test-cases: "()*)*()" , "(**(" , edge case: ")"

public class validParenthesesStr {
    // note: "*" can either contribute -1 with ')' or +0 with empty "" or +1 with '('
    public boolean checkValidString(String s) {
        int minOpen = 0, maxOpen = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') { // increases both ends of the range
                minOpen++;
                maxOpen++;
            } else if (ch == ')') { // decreases both ends of the range
                minOpen--;
                maxOpen--;
            } else { // when "*" is encountered:
                minOpen--; // can either decrease the range by -1
                maxOpen++; // or increase the range by +1
            }

            if (minOpen < 0)
                minOpen = 0; // if the min goes -ve at any instant, we reinitliaze with 0 as we simplycontribute an empty string (+0) instead of ')' (-1) as we cant have -veunmatched '('

            // it immediately becomes invalid as maxOpen in the range becoming -ve signifies too many ')' even after treating all '*' as '(' {eg: edge case - ")" }
            if (maxOpen < 0)
                return false;
        }
        return (minOpen == 0); // must be 0 for a valid configuration as all '(' must be matched with ')', meaning there must exist atleast one way to assign "*" s.t. all '(' are closed
    }
}
