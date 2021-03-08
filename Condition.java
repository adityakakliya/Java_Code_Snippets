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
    
    Runner1 runner = new Runner1();
    Thread t1 = new Thread(runner::first);
    Thread t2 = new Thread(runner::second);
    
    t1.start();
    t2.start();
    
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException ie) {}
    finally {
      runner.finish();  
    }
  }
}


class Runner1 {
  
  private int count;
  private Lock lock = new ReentrantLock();
  private Condition cond = lock.newCondition();
  
  public void increment() {
    System.out.println("from " + Thread.currentThread().getName());
    count++;
    System.out.println("from " + Thread.currentThread().getName() + " count " + count);
  }

  public void first() {
    for (int i = 0; i < 10000; i++) {
      lock.lock();
      try {cond.await();} catch (InterruptedException ie) {};
      increment();
      lock.unlock();
    };
  }

  public void second() {
    for (int i = 0; i < 10000; i++) {
      lock.lock();
      new Scanner(System.in).nextLine();
      cond.signal();
      increment();
      lock.unlock();
    };
  }

  public void finish() {
    System.out.println(count);
  }
}
