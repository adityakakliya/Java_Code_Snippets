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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return treeToString(s).indexOf(treeToString(t)) != -1;
    }
    
    private String treeToString(TreeNode s) {
        if (s == null) return "X";
        List<String> list = List.of(String.valueOf(s.val),
                                   treeToString(s.left),
                                   treeToString(s.right));
        return "|" + String.join(",", list);
    }
}
