

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


class Solution {
  public static void main(String[] args) {
    Graph graph = new Graph();
    
    List<List<String>> lists = new ArrayList<>() {{
      add(List.of("a","b"));
      add(List.of("b","c"));           
    }};
    
    List<Double> values = List.of(3.0, 4.0);
    
    IntStream.range(0, lists.size())
      .forEach(i -> graph.connectNodes(graph.getOrCreateNode(lists.get(i).get(0)),
                         graph.getOrCreateNode(lists.get(i).get(1)),
                         values.get(i)));
    
    System.out.println(EvaluateDivision.evaluateDivision(graph.getOrCreateNode("c"),graph.getOrCreateNode("a"))); 
  }
}

class EvaluateDivision {

  public static Double evaluateDivision(Node source, Node destination) {
    source.updateParent();
    destination.updateParent();
    source.string();
    destination.string();
    
    if (source.parent != destination.parent) return -1.0;
    return source.factor / destination.factor;
  }
}

class Graph {
  Map<String, Node> nodes;
  
  Graph() {
    this.nodes = new HashMap<>();
  }
  
  public Node getOrCreateNode(String str) {
    nodes.putIfAbsent(str, new Node(str));
    return nodes.get(str);
  }
  
  public void connectNodes(Node source, Node destination, Double factor) {
    source.updateParent();
    destination.updateParent();
    if (destination.parent != source.parent) {
      destination.parent.factor = (source.factor * factor) / destination.factor;       
      destination.parent.parent = source.parent;
    }
  }
}

class Node {
  String str;
  Double factor;
  Node parent;
  
  Node(String str) {
    this.str = str;
    parent = this;
    factor = 1.0;
  }

  public void string() {
    System.out.println(str + " factor " + Double.toString(factor) + " parent " + parent.str);
  }
  
  public void updateParent() {
    if (this.parent != this) {
      this.parent.updateParent();
      factor = this.parent.factor * factor;
      this.parent = this.parent.parent;
    }
  
  }
}


