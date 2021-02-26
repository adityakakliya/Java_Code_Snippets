package com.aditya;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class LockATM {
    int balance;
    Lock lock;

    LockATM(int balance) {
        this.balance = balance;
        lock = new ReentrantLock();
    }
    public static void main (String[] args) throws InterruptedException {
        LockATM lockATM = new LockATM(300);
        Thread t1 = new Thread(() -> {
            try {
                lockATM.deposit(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lockATM.withdraw(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(lockATM.balance);
    }

    private int deposit(int amount) throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        balance += amount;
        lock.unlock();
        return balance;
    }
    private int withdraw(int amount) throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        balance -= amount;
        lock.unlock();
        return balance;
    }
}