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
    //Semaphore sem = new Semaphore(0);
    //try {sem.acquire();} catch (InterruptedException ignored){}
    
    Connection.getInstance().connect();
    
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 200; i++) {
      executorService.submit(() -> Connection.getInstance().connect());
    }
    
    long start = System.currentTimeMillis();
    executorService.shutdown();
    try {executorService.awaitTermination(1, TimeUnit.HOURS);} catch (InterruptedException ignored) {}
    long end = System.currentTimeMillis();
    System.out.println("total time taken - " + (end-start));
  }
}


class Connection {
  private static Connection instance = new Connection();
  int connections = 0;
  private Semaphore sem = new Semaphore(10);
  
  private Connection() {}
  public static Connection getInstance() {return instance;}
  
  public void connect() {
    try {sem.acquire();} catch (InterruptedException ignored) {}
    synchronized(this) {
      connections++;
      System.out.println(connections);
    }
    
    try {Thread.sleep(2000);} catch (Exception e){}    // simulating some work, e.g. writing/reading from file
    synchronized(this) {
      connections--;
    }
    sem.release();
    
  }
  
  

}
