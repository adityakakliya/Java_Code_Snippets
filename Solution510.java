/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node leftNode = node.right;
            while (leftNode.left != null) {
                leftNode = leftNode.left;
            }
            return leftNode;
        }

        Node currentNode = node;
        Node parentNode = node.parent;

        while (parentNode != null && parentNode.left != currentNode) {
            currentNode = parentNode;
            parentNode = currentNode.parent;
        }

        return parentNode;
    }
}