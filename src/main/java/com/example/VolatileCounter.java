package com.example;

public class VolatileCounter implements SiteVisitCounter {

    private volatile  int count = 0;


    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        count++;
    }

    @Override
    public int getVisitCount() {
        return count;
    }
}
