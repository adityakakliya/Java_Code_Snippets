
import java.io.*;
import java.util.*;



class Solution {
  public static void main(String[] args) {
    Producer producer = new Producer();
    Thread produceProcess = new Thread(producer::produce);
    Thread consumeProcess = new Thread(producer::consume);
    produceProcess.start();
    consumeProcess.start();
  }
}


class Producer {

  private LinkedList<Integer> list = new LinkedList<>();
  private final int MAX_SIZE = 10;
  Random random = new Random();
  
  private Object lock = new Object();
  
  public void produce() {
    while (true) {
      synchronized(lock) {
        while (list.size() == MAX_SIZE) {
          try {lock.wait();} catch (Exception e) {}
        } 
        int val = random.nextInt();
        System.out.println("produced -> " + val);
        list.add(val); 
        lock.notify();
      }
    }
  }
  
  public void consume() {
    while (true) {
      synchronized(lock) {
        while (list.size() == 0) {
          try {lock.wait();} catch (Exception e) {}
        } 
        System.out.println("consumed -> " + list.removeFirst());
        lock.notify();
      }
      try {
        Thread.sleep(5000);
      } catch (Exception e) {}
    }
  }
  

}
