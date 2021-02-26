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

/*
root left right

*/


class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();

        stack.add(root);

        while (!stack.isEmpty() && root != null) {
            TreeNode currentNode = stack.pop();
            preorderTraversal.add(currentNode.val);
            if (currentNode.right != null) {
                stack.add(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.add(currentNode.left);
            }
        }
        return preorderTraversal;
    }
}