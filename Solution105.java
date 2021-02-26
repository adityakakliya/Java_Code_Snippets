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
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> map;
    int index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inorder = inorder;
        this.preorder = preorder;
        this.map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return tree(0, inorder.length-1);
    }

    private TreeNode tree(int l, int r) {
        if (l > r) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[index]);
        int val = node.val;
        index++;
        node.left = tree(l, map.get(val) - 1);
        node.right = tree(map.get(val) + 1, r);
        return node;
    }
}