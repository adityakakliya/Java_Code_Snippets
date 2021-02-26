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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagLevelOrder = new ArrayList<>();

        int level = 0;

        Stack<TreeNode> stack = new Stack();

        stack.add(root);

        while (!stack.isEmpty() && root != null) {
            List<TreeNode> childNodes = new ArrayList<>();
            List<Integer> currentNodes = new ArrayList<>();

            while (!stack.isEmpty()) {
                TreeNode currentNode = stack.pop();
                currentNodes.add(currentNode.val);
                if (level % 2 == 1) {
                    if (currentNode.right != null) {
                        childNodes.add(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        childNodes.add(currentNode.left);
                    }
                } else {
                    if (currentNode.left != null) {
                        childNodes.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        childNodes.add(currentNode.right);
                    }
                }
            }
            zigzagLevelOrder.add(currentNodes);
            stack.addAll(childNodes);
            level++;
        }
        return zigzagLevelOrder;
    }
}