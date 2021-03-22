import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Solution {
  public static void main(String[] args) {
      ExecutorService executorService = Executors.newFixedThreadPool(2);
    Bank instance = new Bank();
    executorService.submit(() -> instance.first());
    executorService.submit(() -> instance.second());
    executorService.shutdown();
    try {executorService.awaitTermination(2, TimeUnit.MINUTES);} catch (InterruptedException ignored) {}
    instance.finish();
  } 
}


class Bank {
  Account a1 = new Account();
  Account a2 = new Account();
  ReentrantLock lock1 = new ReentrantLock();
  ReentrantLock lock2 = new ReentrantLock();
  Random random = new Random();
  public void first() {
    for (int i = 0; i < 10000; i++) {
      lock1.lock();
      lock2.lock();
      Account.transfer(a1, a2, random.nextInt(100));
      lock1.unlock();
      lock2.unlock();
    }
  }
  
  public void second() {
    for (int i = 0; i < 10000; i++) {
      lock1.lock();
      lock2.lock();
      Account.transfer(a2, a1, random.nextInt(100));
      lock1.unlock();
      lock2.unlock();
    }
  }
  
  public void finish() {
    System.out.println((a1.balance + a2.balance) == 2000);
  }
}

class Account {
  int balance = 1000;
  public void add(int val) {
    balance += val;
  }
  public void withdraw(int val) {
    balance -= val;
  }
  public static void transfer (Account from, Account to, int val) {
    from.withdraw(val);
    to.add(val);
  }
}


  
