package com.example;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter siteVisitCounter;

    private long startTime;

    private final List<Thread> threads = new ArrayList<>();


    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {

        startTime = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i++) {

            Thread thread = new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();

        }

    }

    public void waitUntilAllVisited() {

        for (Thread thread : threads)
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }

    public long getTotalTimeOfHandling() {

        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
}
