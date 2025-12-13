class boundaryTraversal {
    boolean isLeaf(Node node){
        return (node.left==null && node.right==null);
    }
    
    void addLeftBoundary(Node root, ArrayList<Integer> ans){
        Node curr=root.left;
        while(curr!=null){
            // add the value of node only if it's not a leaf
            if(!isLeaf(curr)) ans.add(curr.data);
            // move to left, if left child exists, else move right
            if(curr.left!=null) curr=curr.left;
            else curr=curr.right;
        }
    }
    
    void addRightBoundary(Node root, ArrayList<Integer> ans){
        Node curr=root.right;
        Stack<Integer> st=new Stack<>();  // use a stack since we want the reversed right boundary
        
        while(curr!=null){
            if(!isLeaf(curr)) st.push(curr.data);
            if(curr.right!=null) curr=curr.right;
            else curr=curr.left;
            
        }
        
        while(!st.isEmpty()){
            ans.add(st.pop());
        }
    }
    
    void addLeaves(Node root, ArrayList<Integer> ans){
        if(isLeaf(root)){
            ans.add(root.data);
            return;
        }
        
        // recursively add leaves of left and right subtrees
        if(root.left!=null) addLeaves(root.left,ans);
        if(root.right!=null) addLeaves(root.right,ans);
    }
    
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        
        if(!isLeaf(root)) ans.add(root.data);
        
        addLeftBoundary(root,ans);
        addLeaves(root,ans);
        addRightBoundary(root,ans);
        
        return ans;
    }
}