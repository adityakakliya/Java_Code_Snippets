
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;


class Solution {
    public static void main(String[] args) throws InterruptedException {
      
      SimilarStrings similarStrings = new SimilarStrings(
        List.of("ab","ba","ed","E","e"));
      
      Thread generateKeyThread = new Thread(() -> similarStrings.generateKey());
      Thread groupStringThread = new Thread(() -> similarStrings.groupString());
      
      long startTime = System.currentTimeMillis();
      
      
      generateKeyThread.start();
      groupStringThread.start();
      
      generateKeyThread.join();
      groupStringThread.join();
      
      long endTime = System.currentTimeMillis();
      System.out.println(endTime - startTime);
      System.out.println(similarStrings.getGroupedStrings());
      
    }
}

class SimilarStrings {
  volatile int key = -1;
  volatile int index;
  private List<String> strings;
  private Map<Integer, List<String>> groupedStrings = new HashMap<>();
  private Object lock = new Object();
  
  SimilarStrings(List<String> strings) {
    this.strings = strings;
  }
  
  public void generateKey() {
  
    while (index < strings.size()) {
      synchronized(lock) {
        while (key != -1) {
          try {lock.wait();} catch(Exception ignored) {};
        }
        if (index >= strings.size()) break;
        key = getKey(strings.get(index));
        lock.notify();
      }
    }
  }
  
  public void groupString() {
    while (index < strings.size()) {
      synchronized(lock) {
          while (key == -1) {
          try {lock.wait();} catch(Exception ignored) {};
        }
        groupedStrings.computeIfAbsent(key, list -> new ArrayList<>()).add(strings.get(index));
        key = -1;
        index = index + 1;
        lock.notify();
      }
    }
  }
  
  private int getKey(String str) {
    return IntStream.range(0, str.length())
      .parallel()
      .map(i -> 1 << (Character.toLowerCase(str.charAt(i) - 'a')))
      .reduce(0, (currentKey, mappedIndex) -> currentKey | mappedIndex);
  }
  
  public Map<Integer, List<String>> getGroupedStrings() {
    return this.groupedStrings;
  }
}
