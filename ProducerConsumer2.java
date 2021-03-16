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
    ProducerConsumer producerConsumer = new ProducerConsumer();
    Thread t1 = new Thread(producerConsumer::producer);
    Thread t2 = new Thread(producerConsumer::consumer);
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (Exception e) {}
  }
}


class ProducerConsumer {
  private Queue<Integer> queue = new LinkedList<>();
  private Object lock = new Object();
  private final int size = 10;
  int value;
  public void producer() {
    while (true) {
      synchronized(lock) {
        while (queue.size() == size) {
          try {lock.wait();} catch (Exception ignored){}
        }
        int newValue = value++;
        queue.add(newValue);
        lock.notify();
        System.out.println("produced " + newValue);
      }
    }
  }
  public void consumer() {
    while (true) {
      synchronized(lock) {
        while (queue.size() == 0) 
          try {lock.wait();} catch (Exception ignored){}
        int value = queue.remove();
        System.out.println("consumed " + value);
        lock.notify();
      }
      try {Thread.sleep(1000);} catch (Exception ignored){}
    }
  }

}
