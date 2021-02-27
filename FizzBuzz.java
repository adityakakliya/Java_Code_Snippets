

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Solution {
    public static void main(String[] args) {
        int max = 300;
        Thread[] threads = {
                new FizzBuzz(true, false, "Fizz", max),
                new FizzBuzz(false, true, "Buzz", max),
                new FizzBuzz(true, true, "FizzBuzz", max),
                new FizzBuzz(false, false, "Counter", max)
        };

        for (int i = 0; i < threads.length; i++) threads[i].start();
    }
}

class FizzBuzz extends Thread {
    static int counter = 1;
    boolean isDivisibleByFive;
    boolean isDivisibleByThree;
    static int max;
    static Lock lock = new ReentrantLock();

    FizzBuzz(boolean isDivisibleByThree, boolean isDivisibleByFive, String threadName, int max) {
        this.isDivisibleByThree = isDivisibleByThree;
        this.isDivisibleByFive = isDivisibleByFive;
        FizzBuzz.max = max;
        this.setName(threadName);
    }

    public void run() {
        while (true) {
            //synchronized (lock) {
            lock.lock();
            if (counter > max) return;
            if ((counter % 3 == 0) == false && (counter % 5 == 0) == false) {
                print();
            }else if ((counter % 3 == 0) == isDivisibleByThree && (counter % 5 == 0) == isDivisibleByFive) {
                print(this.getName());
            }
            lock.unlock();
            //}
        }
    }


    private void print(String name) {
        System.out.println(name);
        counter++;
    }
    private void print() {
        System.out.println(counter);
        counter++;
    }
}
