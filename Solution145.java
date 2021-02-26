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

left right root

*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> postorderTraversal = new LinkedList<>();
        Stack<TreeNode> stack = new Stack();
        stack.add(root);

        while (!stack.isEmpty() && root != null) {
            TreeNode currentNode = stack.pop();
            postorderTraversal.addFirst(currentNode.val);
            if (currentNode.left != null) {
                stack.add(currentNode.left);
            }
            if (currentNode.right != null) {
                stack.add(currentNode.right);
            }
        }
        return postorderTraversal;
    }
}