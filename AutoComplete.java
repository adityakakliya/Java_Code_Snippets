import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    Trie trie = new Trie(2);
    AutoComplete instance = new AutoComplete(trie, 2);
    instance.addData(List.of(
      new Data("ab", 4),
      new Data("ad", 5),
      new Data("abc", 10)
    ));
    System.out.println(instance.findAutocompleteResults("ab"));
  }
}

class AutoComplete {
  Trie trie;
  int autocompleteResultsCount;
  AutoComplete (Trie trie, int autocompleteResultsCount) {
    this.trie = trie;
    this.autocompleteResultsCount = autocompleteResultsCount;
  }
  
  public void addData(List<Data> data) {
    data.stream()
      .forEach(trie.root::addData);
  }
  
  public PriorityQueue<Data> findAutocompleteResults(String string) {
    return trie.root.findAutocompleteResults(string);
  }
}

class Trie {
  TrieNode root;
  Trie (int a) {
    root = new TrieNode('*', a);
  }
}

class TrieNode {
  char value;
  Map<Character, TrieNode> childNodes; 
  PriorityQueue<Data> mostFrequentData = new PriorityQueue<>((a,b) -> a.freq - b.freq);
  int autocompleteResultsCount;
  TrieNode (char value, int autocompleteResultsCount) {
    this.value = value;
    childNodes = new HashMap<>();
    this.autocompleteResultsCount = autocompleteResultsCount;
  }
  
  public TrieNode getOrSetChildNode(char child) {
    childNodes.putIfAbsent(child, new TrieNode(child, autocompleteResultsCount));
    return childNodes.get(child);
  }
  
  public void addData(Data data) {
    addData(data, 0);
  }
  
  public PriorityQueue<Data> findAutocompleteResults (String string) {
    return findAutocompleteResults (string, 0);
  }
  
  public PriorityQueue<Data> findAutocompleteResults (String string, int index) {
    if (string.length() == index) return mostFrequentData;
    return getOrSetChildNode(string.charAt(index)).findAutocompleteResults(string, index+1);
  }
  
  public void addData(Data data, int index) {
    if (data.sentence.length() == index) {
      getOrSetChildNode('#');
      mostFrequentData.add(data);
    } else {
      getOrSetChildNode(data.sentence.charAt(index)).addData(data, index+1);
      // TODO: efficient algo to merge k collections into top autocompleteResultsCount
      for (Data d : getOrSetChildNode(data.sentence.charAt(index)).mostFrequentData) {
            mostFrequentData.add(d);
            if (mostFrequentData.size() > autocompleteResultsCount) mostFrequentData.remove(); 
      }
    }    
  }
}

class Data {
  String sentence;
  int freq;
  
  Data (String sentence, int freq) {
    this.sentence = sentence;
    this.freq = freq;
  }
  public String toString() {
    return "(" + sentence + "-" + freq + ")";
  }
}

class MyComparator implements Comparator<Data> {
  @Override
  public int compare(Data data1, Data data2) {
    return Integer.compare(data1.freq, data2.freq);
  }
}
