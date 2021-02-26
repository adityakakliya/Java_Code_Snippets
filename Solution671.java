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
    int min1;
    long ans = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs (root);
        return ans == Long.MAX_VALUE ? -1 : (int)ans;
    }
    private void dfs (TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.val < min1) {
            min1 = root.val;
        } else if (root.val > min1 && ans > root.val) {
            ans = root.val;
        }

        dfs(root.left);
        dfs(root.right);
    }
}