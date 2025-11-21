// LC-282: https://leetcode.com/problems/expression-add-operators/description/

import java.util.*;

class expressionAddOperators {
    public void backtrack(String num, List<String> ans, int target, long currVal, long lastVal, int i, String curr) {
        // base case:
        if (i == num.length()) {
            if (currVal == target) {
                ans.add(curr);
            }
            return;
        }

        // loop through current index till all characters of num to apply all possible
        // operators:
        for (int ind = i; ind < num.length(); ind++) {
            if (ind > i && num.charAt(i) == '0')
                return; // skip leading 0s

            // get the current number
            String curr_num = num.substring(i, ind + 1);
            long curr_num_val = Long.parseLong(curr_num);

            // set currVal as curr_num_val for first number
            if (i == 0) {
                backtrack(num, ans, target, curr_num_val, curr_num_val, ind + 1, curr_num);
            } else {
                // perform '+':
                backtrack(num, ans, target, currVal + curr_num_val, curr_num_val, ind + 1, curr + '+' + curr_num);

                // perform '-':
                backtrack(num, ans, target, currVal - curr_num_val, -curr_num_val, ind + 1, curr + '-' + curr_num);

                // perform '*':
                // + and - work left-to-right, but * must override the previous operation
                // because multiplication has higher precedence. So for multiplication, you must
                // “undo” the last operation and rewrite it correctly as multiplication only
                // affects the most recent number, not the entire expression.
                backtrack(num, ans, target, currVal - lastVal + lastVal * curr_num_val, lastVal * curr_num_val, ind + 1,
                        curr + '*' + curr_num);
            }
        }

    }

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        backtrack(num, ans, target, 0, 0, 0, "");
        return ans;
    }
}