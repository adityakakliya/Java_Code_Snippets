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
    int index = 0;
    Map<Integer, Integer> map = new HashMap<>();    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        index = postorder.length - 1;
        return buildTree(inorder, postorder, 0, index);
    }
    
    private TreeNode buildTree(int[] inorder, int[] postorder, int start, int end) {
        if (start > end) return null;
        int val = postorder[index];
        TreeNode node = new TreeNode(val);
        index--;
        node.right = buildTree(inorder, postorder, map.get(val) + 1, end);
        node.left = buildTree(inorder, postorder, start, map.get(val) - 1);
        return node;
    }
}
