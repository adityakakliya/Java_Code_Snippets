import java.io.*;
import java.util.*;


class Solution {
  public static void main(String[] args) {
    
  }
}

class FirstCommonAncestor {
  
  TreeNode<Integer> root;
  FirstCommonAncestor (TreeNode<Integer> root) {
    this.root = root; 
  }
  
  public TreeNode<Integer> findCommonAncestor (TreeNode<Integer> p, TreeNode<Integer> q) {
    if (p == null || q == null) return null;
    LinkToRoot linkToRootP = findLinkToRoot(p);
    LinkToRoot linkToRootQ = findLinkToRoot(q);
    if (linkToRootP.root != linkToRootQ.root) return null;
    if (linkToRootP.depth > linkToRootQ.depth) {
      p = moveUp (p, linkToRootP.depth - linkToRootQ.depth);
    } else {
      q = moveUp (q, linkToRootQ.depth - linkToRootP.depth);
    }

    while (p != q) {
      p = p.parent;
      q = q.parent;
    }
    return p;
  }
  
  private TreeNode<Integer> moveUp(TreeNode<Integer> p, int depth) {
    while (depth-- > 0) {
      p = p.parent;
    }
    return p;
  } 
  
  private LinkToRoot findLinkToRoot (TreeNode<Integer> p) {
    int depth = 0;
    while (p.parent != null) {
      depth++;
      p = p.parent;
    }
    return new LinkToRoot(depth, p);
  }
  
  private static class LinkToRoot {
    int depth;
    TreeNode<Integer> root;
    
    LinkToRoot (int depth, TreeNode<Integer> root) {
      this.depth = depth;
      this.root = root;
    }
  }
}

class TreeNode<T> {
  T value;
  TreeNode<T> parent;
  TreeNode (T value) {
    this.value = value;
  }
}
