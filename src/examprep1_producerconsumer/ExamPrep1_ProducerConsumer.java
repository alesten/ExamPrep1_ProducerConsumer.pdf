/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examprep1_producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
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

        Producer p1 = new Producer(s1, s2);
        Producer p2 = new Producer(s1, s2);
        Producer p3 = new Producer(s1, s2);
        Producer p4 = new Producer(s1, s2);

        Consumer c1 = new Consumer(s2);

        Thread tp1 = new Thread(p1);
        Thread tp2 = new Thread(p2);
        Thread tp3 = new Thread(p3);
        Thread tp4 = new Thread(p4);

        tp1.start();
        tp2.start();
        tp3.start();
        tp4.start();

        Thread tc1 = new Thread(c1);

        tc1.start();
        try {
            tp1.join();
            tp2.join();
            tp3.join();
            tp4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExamPrep1_ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }

        tc1.interrupt();
        
        System.out.println("SUM: " + c1.getSum());
    }
}
