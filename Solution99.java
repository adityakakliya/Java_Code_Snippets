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
    public void recoverTree(TreeNode root) {
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;
        
        Stack<TreeNode> stack = new Stack();
        TreeNode currentNode = root;
        
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            if (prev != null && currentNode.val < prev.val) {
                if (first == null) first = prev;
                second = currentNode;
            }
            prev = currentNode;
            currentNode = currentNode.right;
        }
        int saved = first.val;
        first.val = second.val;
        second.val = saved;
    }
}
