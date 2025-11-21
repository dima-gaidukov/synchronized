package com.example;

public class SynchronizedBlockCounter implements SiteVisitCounter{

    private Integer count = 0;

    @Override
    public void incrementVisitCount() {

        synchronized (this) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            count++;
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (this) {
            return count;
        }

    }
}
