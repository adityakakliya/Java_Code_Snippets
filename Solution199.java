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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        doDFS (root, rightSideView, 0);
        return rightSideView;
    }
    
    private void doDFS (TreeNode root, List<Integer> rightSideView, int level) {
        if (root == null) return;
        if (rightSideView.size() == level) {
            rightSideView.add(root.val);
        } else {
            rightSideView.set(level, root.val);
        }
        
        doDFS (root.left, rightSideView, level + 1);
        doDFS (root.right, rightSideView, level + 1);
    }
}
