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

left root right

1 3 2

left root right

right, work with root,

*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderTraversal = new ArrayList<>();

        Stack<TreeNode> stack = new Stack();

        TreeNode currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.add(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            inorderTraversal.add(currentNode.val);
            currentNode = currentNode.right;
        }
        return inorderTraversal;
    }
}