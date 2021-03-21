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
    List<List<Integer>> leaves = new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        findLeavesHelper(root);
        return leaves;
    }
    private int findLeavesHelper (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHt = findLeavesHelper(root.left);
        int rightHt = findLeavesHelper(root.right);
        int index = Math.max(leftHt, rightHt);
        if (leaves.size() == index) {
            leaves.add(new ArrayList());
        }
        leaves.get(index).add(root.val);
        return index+1;
    }
}
