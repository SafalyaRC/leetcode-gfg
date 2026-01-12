// LC-455: https://leetcode.com/problems/assign-cookies/description/

/* the solution can be found using the greedy approach, where we sort both the g and s arrays since:
    we need s[j]>=g[i], we want to assign the min. needed values for g[i] from s[j] since we keep the larger
    values of s[j] reserved for bigger values of g[i]. We are being greedy here as we only assign as much as
    much as reqd. not more than that
*/

import java.util.*;
class assignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int l = 0, r = 0; // use 'l' ptr for g and 'r' ptr for s
        int children = 0;
        while (l < g.length && r < s.length) {
            if (s[r] >= g[l]) {
                l++;
                r++;
                children++;
            } else {
                r++;
            }
        }

        return children;
    }
}