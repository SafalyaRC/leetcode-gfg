// LC-8: https://leetcode.com/problems/string-to-integer-atoi/

public class myAtoiRecursive {
    public int helper(String s, int i, int num, int sign) {
        // base case:
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
            return (int) (sign * num);
        }
        // overflow check:
        if (num > (Integer.MAX_VALUE - (s.charAt(i) - '0')) / 10) {
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        num = num * 10 + (s.charAt(i) - '0');
        return helper(s, i + 1, num, sign);
    }

    public int myAtoi(String s) {
        s = s.trim();
        int i = 0, sign = 1;
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }
        return helper(s, i, 0, sign);
    }
}
