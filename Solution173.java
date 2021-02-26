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

*/
class BSTIterator {
    private LinkedList<Integer> list = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        list.add(-1);
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            list.addLast(root.val);
            root = root.right;
        }
    }

    /** @return the next smallest number */
    public int next() {
        list.removeFirst();
        return list.getFirst();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return list.size() > 1;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */