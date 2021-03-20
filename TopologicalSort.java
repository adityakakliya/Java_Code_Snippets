import java.io.*;
import java.util.*;


class Solution {
  public static void main(String[] args) {
    
  }
}

class Graph {
  public Map<String, GraphNode> keyToNode = new HashMap<>();
  public GraphNode getOrCreateNode (String key) {
    keyToNode.putIfAbsent(key, new GraphNode(key));
    return keyToNode.get(key);
  }
  public void addEdge(String key1, String key2) {
    getOrCreateNode(key1).addDependentNode(getOrCreateNode(key2));
  }
  public List<GraphNode> getNodes() {
    return (List<GraphNode>) keyToNode.values();
  }
}

class GraphNode {
      String key;
      List<GraphNode> dependentNodes;
      int dependency;
      boolean isVisited;
      GraphNode (String key) {
        this.key = key;
        dependentNodes = new ArrayList<>();
        dependency = 0;
        isVisited = false;
      }
  public void addDependentNode(GraphNode dependentNode) {
      dependentNodes.add(dependentNode);
    }
}

class TopologicalSort {
  Graph graph;
  int orderIndex = 0;
  GraphNode[] order;
  TopologicalSort (Graph graph) {
    this.graph = graph;
    order = new GraphNode[graph.getNodes().size()];
  }
  
  private void visitNonDependentNodes(Queue<GraphNode> nonDependentNodes) {
    graph.getNodes()
      .stream()
      .filter(node -> node.dependency == 0)
      .forEach (node -> {
        nonDependentNodes.add(node);
        node.isVisited = true;
        });
  }
  public GraphNode[] getOrder() {
    Queue<GraphNode> nonDependentNodes = new LinkedList<>();
    visitNonDependentNodes(nonDependentNodes);
    while (!nonDependentNodes.isEmpty()) {
      int size = nonDependentNodes.size();
      while (size -- > 0) {
        GraphNode independentNode = nonDependentNodes.remove();
        for (GraphNode dependentNode : independentNode.dependentNodes) {
          dependentNode.dependency--;
          if (dependentNode.dependency == 0 && !dependentNode.isVisited) {
            nonDependentNodes.add(dependentNode);
            dependentNode.isVisited = true;
            order[orderIndex] = dependentNode;
            orderIndex++;
          }
        }
      }
    }
    return (orderIndex == graph.keyToNode.size()) ? order : null;
  }
}
