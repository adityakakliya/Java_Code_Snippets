/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.concurrent.BlockingQueue;
import java.util.Scanner;
import java.util.concurrent.locks.*;

class Solution {
  public static void main(String[] args) {
    ListNode l1 = new ListNode();
    ListNode l2 = new ListNode();
    ListNode result = addLists(l1, l2, 0);
    System.out.println(result);
  }
  
  public static ListNode addLists (ListNode l1, ListNode l2, int carry) {
    if (l1 == null && l2 == null && carry == 0) return null;
    
    ListNode result = new ListNode(carry);

    if (l1 != null) result.val += l1.val;
    if (l2 != null) result.val += l2.val;
    
    result.nextNode = addLists(l1 == null ? l1 : l1.nextNode,
                               l2 == null ? l2 : l2.nextNode,
                               result.val / 10);
    result.val = result.val % 10;
    return result;
  }
}


class ListNode {
  int val;
  ListNode nextNode;

  ListNode(int val) {
    this.val = val;
    nextNode = null;
  }
  
  public void appendToTail(int val) {
    ListNode node = this;
    while (node.nextNode != null) node = node.nextNode;
    node.nextNode = new ListNode(val);
  }
  
  public String toString() {
    List<String> list = new ArrayList<>();
    ListNode node = this;
    while (node != null) {
      list.add(String.valueOf(node.val));
    }
    return String.join("->", list);
  }
}
