package examprep1_producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable{

    private BlockingQueue<Long> s1;
    private BlockingQueue<Long> s2;

    public Producer(BlockingQueue<Long> s1, BlockingQueue<Long> s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    
    @Override
    public void run() {
        long num;
        while(true){
            try{
                num = s1.poll();
            }catch(NullPointerException ex){
                break;
            }
            
            try {
                s2.put(fib(num));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
