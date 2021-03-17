import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    l1.appendToTail(1);
    RemoveDuplicateFromLinkedList removeDuplicateFromLinkedList = new RemoveDuplicateFromLinkedList(l1);
    removeDuplicateFromLinkedList.removeDuplicates();
    System.out.println(l1);
  }
}


class RemoveDuplicateFromLinkedList {
  ListNode head;
  RemoveDuplicateFromLinkedList(ListNode head) {
    this.head = head;
  }
  
  public void removeDuplicates() {
    Set<Integer> set = new HashSet<>();
    ListNode prev = null;
    while (head != null) {
      if (set.add(head.val)) {
        prev = head;
      } else {
        prev.nextNode = head.nextNode;
      }
      head = head.nextNode;
    }
  }
  public void removeDuplicates1() {
    while (head != null) {
      ListNode nextNode = head;
      while (nextNode.nextNode != null) {
        if (nextNode.nextNode.val == head.val) {
          nextNode.nextNode = nextNode.nextNode.nextNode;
        } else {
          nextNode = nextNode.nextNode;
        }
      }
      head = head.nextNode;
    }
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
    ListNode currentNode = this;
    while (currentNode.nextNode != null) currentNode = currentNode.nextNode;
    currentNode.nextNode = new ListNode(val);
  }
  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner("->");
    ListNode currentNode = this;
    while (currentNode != null) {
      stringJoiner.add(String.valueOf(currentNode.val));
      currentNode = currentNode.nextNode;
    }
    return stringJoiner.toString();
    
  }
}
