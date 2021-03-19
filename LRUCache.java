import java.io.*;
import java.util.*;


class Solution {
  public static void main(String[] args) {
    LRUCache lruCacheInstance = new LRUCache();
    lruCacheInstance.getOrSetKey("1","one");
    System.out.println(lruCacheInstance);
    lruCacheInstance.getOrSetKey("2","two");
    System.out.println(lruCacheInstance);
    lruCacheInstance.getOrSetKey("3","three");
    System.out.println(lruCacheInstance);
    lruCacheInstance.getOrSetKey("4","four");
    System.out.println(lruCacheInstance);
    lruCacheInstance.getOrSetKey("4","FOUR");
    System.out.println(lruCacheInstance);
    
  }
}

class LRUCache {
  private static final int MAX_SIZE = 3;
  private Map<String, LinkedListNode> keyToNode;
  private LinkedListNode head;
  private LinkedListNode tail;
  LRUCache () {
    head = null;
    tail = null;
    keyToNode = new HashMap<>();
  }
  
  public LinkedListNode getOrSetKey(String key, String value) {
    if (!keyToNode.containsKey(key)) {
      if (keyToNode.size() == MAX_SIZE) {
        keyToNode.remove(tail.key);
        removeNode(tail);
      }
      keyToNode.put(key, new LinkedListNode(key, value));
    } else {
      keyToNode.get(key).value = value;  
    }
    return addNodeToFront(keyToNode.get(key));
  }
  
  private LinkedListNode addNodeToFront(LinkedListNode node) {
    if (node == head) return node;
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.nextNode = head;
      head.prevNode = node;
      head = node;
    }
    return head;
  }
  
  private void removeNode(LinkedListNode node) {
    if (node == null) return;
    if (head == node) head = node.nextNode;
    if (tail == node) tail = node.prevNode;
    if (node.prevNode != null) node.prevNode.nextNode = node.nextNode;
    if (node.nextNode != null) node.nextNode.prevNode = node.prevNode;
  }
  
  @Override
  public String toString() {
    StringJoiner stringJoiner = new StringJoiner("<->");
    LinkedListNode node = head;
    while (node != null) {
      stringJoiner.add("(" + node.key + "-" + node.value + ")");
      node = node.nextNode;
    }
    return stringJoiner.toString();
  }
 
  static class LinkedListNode {
    String key;
    String value;
    LinkedListNode nextNode;
    LinkedListNode prevNode;
    LinkedListNode (String key, String value) {
      this.key = key;
      this.value = value;
      nextNode = null;
      prevNode = null;
    }
  }
}
