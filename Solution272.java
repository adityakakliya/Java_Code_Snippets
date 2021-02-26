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

2  4  6  8  90  100  12  16
target 7

lowerbound -> 6 / upperbound

pop ->
target < top element of stack -> whenever this happens -> last element popped

*/


class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stack = new Stack();
        Stack<TreeNode> smallerValuesStack = new Stack();
        List<Integer> closestKValues = new ArrayList<>();
        LinkedList<TreeNode> largerValues = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= target) {
                smallerValuesStack.add(root);
            } else {
                largerValues.add(root);
            }
            root = root.right;
        }

        smallerValuesStack.forEach((p) -> {
            System.out.print(p.val + " ");
        });
        System.out.println();
        largerValues.forEach((p) -> {
            System.out.print(p.val + " ");
        });

        while (k-- > 0) {
            double val1 = smallerValuesStack.isEmpty() ? Double.MAX_VALUE : target - smallerValuesStack.peek().val;
            double val2 = largerValues.size() == 0 ? Double.MAX_VALUE : - target + largerValues.getFirst().val;
            System.out.println(val1 + " - " + val2);
            if (val1 < val2) {
                closestKValues.add(smallerValuesStack.pop().val);
            } else {
                closestKValues.add(largerValues.getFirst().val);
                largerValues.removeFirst();
            }
        }

        return closestKValues;
    }
}