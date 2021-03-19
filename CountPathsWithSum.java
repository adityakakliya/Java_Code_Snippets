import java.io.*;
import java.util.*;


class Solution {
  public static void main(String[] args) {
    
  }
}

class CountPathsWithSum {
  
  TreeNode root;
  int target;
  int totalCount;
  Map<Integer, Integer> sumToCount = new HashMap<>();
  CountPathsWithSum (TreeNode root, int target) {
    this.root = root;
    this.target = target;
  }
  
  public void countPathsWithSum(TreeNode node, int currentSum) {
    if (node != null) {
      if (currentSum == target) totalCount++;
      countPathsWithSum(node.leftChild, currentSum);
      countPathsWithSum(node.rightChild, currentSum);
      countPathsWithSum(node.leftChild, currentSum + node.value);
      countPathsWithSum(node.rightChild, currentSum + node.value);
    }
  }
  
  public int countPathsWithSum1(TreeNode node, int currentSum) {
    if (node == null) return 0;
    currentSum += node.value;
    int requiredSum = currentSum - target;
    int count = sumToCount.getOrDefault(requiredSum, 0);
    if (currentSum == target) count++;
    sumToCount.put(currentSum, sumToCount.getOrDefault(currentSum, 0) + 1);
    count += countPathsWithSum1(node.leftChild, currentSum);
    count += countPathsWithSum1(node.rightChild, currentSum);
    sumToCount.put(currentSum, sumToCount.getOrDefault(currentSum, 0) - 1);    
    return count;
    
  }
}

class TreeNode {
  int value;
  TreeNode leftChild;
  TreeNode rightChild;
  TreeNode (int value) {
    this.value = value;
  }
}
