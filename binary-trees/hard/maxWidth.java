// LC-662: https://leetcode.com/problems/maximum-width-of-binary-tree/description/

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class maxWidth {
    class Pair {
        TreeNode node;
        int ind;

        public Pair(TreeNode node, int ind) {
            this.node = node;
            this.ind = ind;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;

        Queue<Pair> q=new LinkedList<>();
        q.offer(new Pair(root,0));
        int maxWidth=0;
        while(!q.isEmpty()){
            int size=q.size();
            int minIndex=q.peek().ind; // get the min. index of the current level
            int first=0,last=0; // to store the first and last indices

            for(int i=0;i<size;i++){
                Pair p=q.poll();
                TreeNode curr=p.node;
                int modifiedIndex=p.ind-minIndex; // we modify the index 'i' using i-minIndex to prevent overflow 
                // update the first and last indices
                if(i==0) first=modifiedIndex;
                if(i==size-1) last=modifiedIndex;

                // push the left and right child along with their indices, if they exist
                if(curr.left!=null) q.offer(new Pair(curr.left,2*modifiedIndex+1));
                if(curr.right!=null) q.offer(new Pair(curr.right,2*modifiedIndex+2)); 
            }
            maxWidth=Math.max(maxWidth,last-first+1);  // calculate the max. width for each level
        }
        return maxWidth;
}
