import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    ListNode node = new ListNode(13);
    node.appendToTail(3);
    System.out.println(node);
    
    ListNode sorted = MergeSort.doMergeSort(node);
    System.out.println(sorted);
  } 
}

class MergeSort {
  
  public static ListNode doMergeSort(ListNode head) {
    if (head == null || head.nextNode == null) return head;
    ListNode firstHalf = head;
    ListNode secondHalf = findHalf(head);
    return merge(firstHalf, secondHalf);
  }
  
  public static ListNode findHalf(ListNode head) {
    ListNode slowRef = head;
    ListNode fastRef = head;
    ListNode prev = null;
    while (fastRef != null && fastRef.nextNode != null) {
      prev = slowRef;
      slowRef = slowRef.nextNode;
      fastRef = fastRef.nextNode.nextNode;
    }
    
    ListNode secondHalf = slowRef;
    prev.nextNode = null;
    return secondHalf;
  }
  
  public static ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val > l2.val) {
      l2.nextNode = merge(l1, l2.nextNode);
      return l2;
    }
    l1.nextNode = merge(l1.nextNode, l2);
    return l1;
  }
}

class ListNode {
  int val;
  ListNode nextNode;
  ListNode (int val) {
    this.val = val;
    nextNode = null;
  }
  
  public void appendToTail(int val) {
    ListNode node = this;
    while (node.nextNode != null) node = node.nextNode;
    node.nextNode = new ListNode(val);
  }
  
  public String toString() {
    StringJoiner sj = new StringJoiner(" -> ");
    ListNode node = this;
    while (node != null) {
      sj.add("" + node.val);
      node = node.nextNode;
    }
    sj.add("null");
    return sj.toString();
  }
}
