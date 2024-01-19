public class AVLTree {
    private TreeNode root;

    public AVLTree() {
        this.root = null;
    }
    public void insert(int x){
        TreeNode newNode = new TreeNode(x);
        TreeNode parent = searchParent(root,x,null);
        linkNewNode(parent,newNode);
        reBalanceTree();
    }

    private TreeNode searchParent(TreeNode rt,int x,TreeNode parent) {
        if(rt == null)
            return parent;
        if(rt.val < x)
            return searchParent(rt.right,x,rt);
        return searchParent(rt.left,x,rt);
    }
    private void linkNewNode(TreeNode parent, TreeNode newNode) {
        if(parent == null){
            this.root = newNode;
            return;
        }
        if(parent.val > newNode.val){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
    }
    private void reBalanceTree() {
        calculateBalanceFactor(root,null);
    }

    private int calculateBalanceFactor(TreeNode rt,TreeNode parent) {
        if(rt == null)
            return  0;
        int lst_height = calculateBalanceFactor(rt.left,rt);
        int rst_height = calculateBalanceFactor(rt.right,rt);
        rt.balanceFactor = lst_height - rst_height;
        if(rt.balanceFactor < -1){
            applyNodeRotation(rt,parent);
            rst_height -= 1;
        }else if(rt.balanceFactor > 1){
            applyNodeRotation(rt,parent);
            lst_height -= 1;
        }
        return Math.max(lst_height,rst_height) + 1;
    }

    private void applyNodeRotation(TreeNode rt,TreeNode parent) {
        TreeNode node = null;
        if(rt.balanceFactor < 0){
            if(rt.right.balanceFactor < 0){
                node = applyRRRotation(rt);
                linkNewNode(parent,node);
            }
            else{
                node = applyRLRotation(rt);
                linkNewNode(parent,node);
            }
        }else{
            if(rt.left.balanceFactor < 0) {
                node = applyLRRotation(rt);
                linkNewNode(parent, node);
            }
            else{
                node = applyLLRotation(rt);

                linkNewNode(parent,node);
            }
        }
    }
    private TreeNode applyLLRotation(TreeNode rt) {
        TreeNode leftNode = rt.left;
        TreeNode leftRightNode = leftNode.right;
        leftNode.right = rt;
        rt.left = leftRightNode;
        return leftNode;
    }
    private TreeNode applyLRRotation(TreeNode rt) {
        rt.left = applyRRRotation(rt.left);
        return applyLLRotation(rt);
    }
    private TreeNode applyRLRotation(TreeNode rt) {
        rt.right = applyLLRotation(rt.right);
        return applyRRRotation(rt);
    }
    private TreeNode applyRRRotation(TreeNode rt) {
        TreeNode rightNode = rt.right;
        TreeNode rightLeftNode = rightNode.left;
        rightNode.left = rt;
        rt.right = rightLeftNode;
        return rightNode;
    }
    public void inOrder(TreeNode rt){
        if(rt == null)
            return;
        inOrder(rt.left);
        System.out.print(rt.val + " ");
        inOrder(rt.right);
    }
    public TreeNode getRoot() {
        return this.root;
    }
}
