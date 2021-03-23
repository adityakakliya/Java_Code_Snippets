import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    
  } 
}

class BuildTree {
  int[] preorder;
  int[] postorder;
    
  public TreeNode getTree(int preStart, int preEnd, int postStart, int postEnd) {
    if (preStart > preEnd) return null;
    TreeNode node = new TreeNode(preorder[preStart]);
    if (preStart == preEnd) return node;
    int postIndex = 0;
    while (postorder[postIndex] != preorder[preStart+1]) postIndex++;
    int len = postIndex - postStart + 1;
    node.left = getTree(preStart+1, preStart + len, postStart, postIndex);
    node.right = getTree(preStart + len + 1, preEnd, postIndex+1, postEnd-1);
    return node;  
  }

}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode (int val) {
    this.val = val;
  }
}
