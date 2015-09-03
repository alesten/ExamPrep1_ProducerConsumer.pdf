/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprep1_producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlexanderSteen
 */
public class ExamPrep1_ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long start = System.nanoTime();

        start(3);

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start));
    }
    
    /*
        1 - 3844366134
        2 - 3692953734
        3 - 3611679796
        4 - 3567365285
        
        Man kan se det går stille og roligt hurtigere
        med flere tråde. Men eftersom det er det sidste
        tal der tager længst tid at regne ud er de
        meget tæt på hindanden i hastighed.
    */

    private static void start(int num) {
        BlockingQueue<Long> s1 = new ArrayBlockingQueue(11);
        BlockingQueue<Long> s2 = new ArrayBlockingQueue(11);
        s1.add(4l);
        s1.add(5l);
        s1.add(8l);
        s1.add(12l);
        s1.add(21l);
        s1.add(22l);
        s1.add(34l);
        s1.add(35l);
        s1.add(36l);
        s1.add(37l);
        s1.add(42l);

        Consumer c1 = new Consumer(s2);
        Thread tc1 = new Thread(c1);
        tc1.start();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < num; i++) {
            Runnable producer = new Producer(s1, s2);
            executor.execute(producer);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        tc1.interrupt();

        System.out.println("SUM: " + c1.getSum());
    }
}

class WorkerThread implements Runnable {

    private String command;

    public WorkerThread(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
