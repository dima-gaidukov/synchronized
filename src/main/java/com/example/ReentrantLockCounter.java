package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
    private int count = 0;

    Lock lock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {

        lock.lock();
        try {
            Thread.sleep(100);
            count++;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();


        } finally {
            lock.unlock();
        }


    }

    @Override
    public int getVisitCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
