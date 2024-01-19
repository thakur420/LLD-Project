public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int balanceFactor;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
    }
}
