package com.example;

public class Main {
    public static void main(String[] args) {


        AtomicIntegerCounter atomicIntegerCounter = new AtomicIntegerCounter();
        VolatileCounter volatileCounter = new VolatileCounter();
        UnsynchronizedCounter unsynchronizedCounter = new UnsynchronizedCounter();
        SynchronizedBlockCounter synchronizedBlockCounter = new SynchronizedBlockCounter();
        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();



        //Скорость: умная система(быстрое переключение) от того и быстрая скорость
        //Корректность: AtomicInteger делает операцию атомарной, что не дает прокникнуть в процесс другим потокам
        MultithreadingSiteVisitor multithreadingSiteVisitor = new MultithreadingSiteVisitor(atomicIntegerCounter);
        multithreadingSiteVisitor.visitMultithread(100);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println("atomicIntegerCounter 10(100) потоков - Время:  " + multithreadingSiteVisitor.getTotalTimeOfHandling() +
                " Счетчик " + atomicIntegerCounter.getVisitCount());

        System.out.println("\n");

        //Скорость: Быстрая,так как синхронизации нет
        //Корректность: volatileCounter просто делает переменную видимой,атомарности нет(точности нет)
        multithreadingSiteVisitor = new MultithreadingSiteVisitor(volatileCounter);
        multithreadingSiteVisitor.visitMultithread(100);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println("volatileCounter 10(100) потоков - Время:  " + multithreadingSiteVisitor.getTotalTimeOfHandling() +
                " Счетчик " + volatileCounter.getVisitCount());

        System.out.println("\n");


        //Скорость: Быстрая,никакой защиты нет
        //Корректность:unsynchronizedCounter защиты никакой нет( любой поток может вмешаться) точность плохая
        multithreadingSiteVisitor = new MultithreadingSiteVisitor(unsynchronizedCounter);
        multithreadingSiteVisitor.visitMultithread(100);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println("unsynchronizedCounter 10(100) потоков - Время:  " + multithreadingSiteVisitor.getTotalTimeOfHandling() +
                " Счетчик " + unsynchronizedCounter.getVisitCount());

        System.out.println("\n");


        //Скорость: Средняя так как по очередно идет проверка
        //Корректность:reentrantLockCounter максимальная защита,операция атомарна и каждая блокируется проверяется и разблокируется
        multithreadingSiteVisitor = new MultithreadingSiteVisitor(reentrantLockCounter);
        multithreadingSiteVisitor.visitMultithread(100);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println("reentrantLockCounter 10(100) потоков - Время:  " + multithreadingSiteVisitor.getTotalTimeOfHandling() +
                " Счетчик " + reentrantLockCounter.getVisitCount());

        System.out.println("\n");


        //Скорость: средняя и дет так же по очередная проверка(по списку)
        //Корректность:synchronizedBlockCounter хорошая защита,атомарность есть
        multithreadingSiteVisitor = new MultithreadingSiteVisitor(synchronizedBlockCounter);
        multithreadingSiteVisitor.visitMultithread(100);
        multithreadingSiteVisitor.waitUntilAllVisited();
        System.out.println("synchronizedBlockCounter 10(100) потоков - Время:  " + multithreadingSiteVisitor.getTotalTimeOfHandling() +
                " Счетчик " + synchronizedBlockCounter.getVisitCount());


    }
}