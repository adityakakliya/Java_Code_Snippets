import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    
    
  } 
}

class WordTransformer {
  public List<String> transform(String start, String end, WordsData wordsData) {
    if (start.equals(end)) return new ArrayList<>();
    BFSData bfsData = new BFSData(new PathNode(start, null));
    
    while (!bfsData.isEmpty()) {
      int size = bfsData.size();
      while (size-- > 0) {
        PathNode node = bfsData.remove();
        for (String key : wordsData.wordToKeys.get(node)) {
          for (String nextWord : wordsData.keyToWords.get(key)) {
            if (nextWord.equals(end)) return getPath(new PathNode(nextWord, node));
            bfsData.addWord(nextWord, new PathNode(nextWord, node));
          }
        }
      }
    }
    return null;
  }

  private List<String> getPath (PathNode node) {
    List<String> list = new LinkedList<>();
    while (node != null) {
      list.addFirst(node.word);
      node = node.previous;
    }
  }
}

class BFSData {
  Queue<PathNode> queue = new LinkedList<>();
  Set<String> isVisited = new HashSet<>();
  BFSData (String string) {
    queue.add(new PathNode(string));
    isVisited.add(string);
  }
  
  public void addWord(String word, PathNode prev) {
    if (isVisited.add(word)) queue.add(new PathNode(word, prev));
  }
  public PathNode remove() {
    return queue.remove();
  }
  public int size() {
    return queue.size();
  }
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}

class PathNode {
  String word;
  PathNode previous;
  PathNode (String word, PathNode prev) {
    this.word = word;
    previous = prev;
  }
}

class WordsData {
  Map<String, List<String>> wordToKeys;
  Map<String, List<String>> keyToWords;
  public void addWord(String word) {
    for (int i = 0; i < word.length(); i++) {
      String key = word.substring(0,i) + "*" + word.substring(i+1);
      wordToKeys.computeIfAbsent(word, list -> new ArrayList<>()).add(key);
      keyToWords.computeIfAbsent(key, list -> new ArrayList<>()).add(word);
    }
  }
}
