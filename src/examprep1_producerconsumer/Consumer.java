package examprep1_producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private BlockingQueue<Long> s2;
    private long sum = 0;

    public Consumer(BlockingQueue<Long> s2) {
        this.s2 = s2;
    }

    @Override
    public void run() {
        long num = 0;
        while (true) {
            try {
                num = s2.take();
            } catch (InterruptedException ex) {
                break;
            }
            System.out.println(num);
            sum += num;
        }
    }

    public long getSum() {
        return sum;
    }

}
