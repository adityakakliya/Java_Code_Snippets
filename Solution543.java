/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxConnectedNodes(root);
        return Math.max(0,ans -1);
    }
    
    private int maxConnectedNodes (TreeNode root) {
        if (root == null) return 0;
        int totalLeftNodes = maxConnectedNodes(root.left);
        int totalRightNodes = maxConnectedNodes(root.right);
        
        int totalNodes = totalLeftNodes + totalRightNodes + 1;
        ans = Math.max(totalNodes, ans);
        return Math.max(totalLeftNodes, totalRightNodes) + 1;
    }
}
