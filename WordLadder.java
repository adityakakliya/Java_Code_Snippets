import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    WordsData wordsData = new WordsData();
    wordsData.addWord("hot");
    wordsData.addWord("dot");
    wordsData.addWord("dog");
    wordsData.addWord("lot");
    wordsData.addWord("log");
    wordsData.addWord("cog");
    WordLadder wordLadder = new WordLadder("hot","cog", wordsData);
    System.out.println(wordLadder.isTransformationPossible());
  } 
}

class WordLadder {
  String start;
  String end;
  WordsData wordsData;
  WordLadder(String start, String end, WordsData wordsData) {
    this.start = start;
    this.end = end;
    this.wordsData = wordsData;
  }
  
  public boolean isTransformationPossible() {
    if (start.equals(end)) return true;
    
    BFSData bfsData = new BFSData();
    bfsData.add(start);
    
    while (!bfsData.isEmpty()) {
      int size = bfsData.size();
      while (size-- > 0) {
        String currentWord = bfsData.remove();
        for (String key : wordsData.wordToKey.get(currentWord)) {
          for (String nextWord : wordsData.keyToWord.get(key)) {
            if (nextWord.equals(end)) return true;
            bfsData.add(nextWord);
          }
        }
      }
    }
    return false;
  }
}

@SuppressWarnings("serial")
class BFSData extends LinkedList<String> {
  Set<String> visited = new HashSet<>();
  @Override
  public boolean add(String word) {
    if (visited.add(word)) return super.add(word);
    return false;
  }
}

class WordsData {
  Map<String, List<String>> keyToWord = new HashMap<>();
  Map<String, List<String>> wordToKey = new HashMap<>();
  
  public void addWord(String word) {
    for (int i = 0; i < word.length(); i++) {
      String key = word.substring(0,i) + "*" + word.substring(i+1);
      wordToKey.computeIfAbsent(word, list -> new ArrayList<>()).add(key);
      keyToWord.computeIfAbsent(key, list -> new ArrayList<>()).add(word);
    }
  }
}
  
