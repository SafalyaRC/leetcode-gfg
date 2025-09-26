// LC-735: https://leetcode.com/problems/asteroid-collision/description/

// brute-force:
import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int num : asteroids) {
            ans.add(num);
        }

        for (int i = 0; i < ans.size() - 1; i++) {
            if (ans.get(i) > 0 && ans.get(i + 1) < 0) {
                if (Math.abs(ans.get(i + 1)) > ans.get(i)) {
                    ans.remove(i); // remove left
                    i = Math.max(-1, i - 2); // step back safely
                } else if (Math.abs(ans.get(i + 1)) < ans.get(i)) {
                    ans.remove(i + 1); // remove right
                    i = Math.max(-1, i - 1);
                } else {
                    ans.remove(i + 1);
                    ans.remove(i);
                    i = Math.max(-1, i - 2); // both gone
                }
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

// test case: 4,7,1,1,2,-3,-7,17,15,16    edge case:4,7,1,1,2,-3,-7,17,15,-18,-19
// optimal:
public class asteroidCollision {
    public int[] asteroidCollision1(int[] asteroids) {
        // keep traversing and pushing for +ve elements and stop when -ve encountered
        // for -ve element check if top of stack is +ve and smaller than curr element
        // if current element<top then no need of doing anything as negative element not relevant to our ans and top of stack stays as it is
        // if -ve current element = +ve top size then pop() 
        // when stack is empty, then push -ve asteroid or if both current element and top -ve push current -ve, dont pop anything (edge case)

        Stack<Integer> st=new Stack<>();
        for(int asteroid:asteroids){
            if(asteroid>0) st.push(asteroid);
            else{
                while(!st.isEmpty() && st.peek()>0 && Math.abs(asteroid)>st.peek()){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek()==Math.abs(asteroid)){
                    st.pop();
                } else if(st.isEmpty() || st.peek()<0){
                    st.push(asteroid);
                }
            }
        }

        int ans[]=new int[st.size()];
        for(int i=st.size()-1;i>=0;i--){
            ans[i]=st.pop();
        }
        return ans;
    }
}
