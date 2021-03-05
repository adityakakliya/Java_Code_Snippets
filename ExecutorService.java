
import java.io.*;
import java.util.*;
import java.util.concurrent.*;


class Solution {
    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Thread t1 = new Thread (() -> {
            try {
                runner.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
       Thread t2 = new Thread (() -> {
           try {
               runner.add();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });
        long start = System.currentTimeMillis();
        // t1.start();
        // t2.start();
        // t1.join();
        // t2.join();
       for (int i = 0; i < 4; i++) {
           executorService.submit(() -> {
               try {runner.add();} catch (Exception ignored) {}
           });
       }
       executorService.shutdown();
       executorService.awaitTermination(1, TimeUnit.DAYS);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(runner.list1.size());
        System.out.println(runner.list2.size());
    }
}

class Runner {
    int counter = 1;
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();


    public void add() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            add1();
            add2();
        }
    }
    public void add1() throws InterruptedException {
        synchronized (lock1) {
            writeToFile();
            list1.add(1);
        }
    }

    public void add2() throws InterruptedException {
        synchronized (lock2) {
            writeToFile();
            list2.add(1);
        }
    }

    public void writeToFile() throws InterruptedException {
      System.out.println(Thread.currentThread().getName());
        Thread.sleep(1);
    }
}
