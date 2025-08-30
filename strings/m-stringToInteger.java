// LC-8: https://leetcode.com/problems/string-to-integer-atoi/

class Solution {
    public int myAtoi(String s) {
        s=s.trim();
        long ans=0;
        int i=0,sign=1;

        if(i<s.length() && (s.charAt(i)=='+' || s.charAt(i)=='-') )
        {
            sign=(s.charAt(i)=='+')?1:-1;
            i++;
        }

        while(i<s.length() && Character.isDigit(s.charAt(i)))
        {
            int digit=s.charAt(i)-'0';

            if(ans>(Integer.MAX_VALUE-digit)/10)
            {
                if(sign==1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            ans=ans*10+digit;
            i++;
        }
        return sign*(int)ans;
    }
}