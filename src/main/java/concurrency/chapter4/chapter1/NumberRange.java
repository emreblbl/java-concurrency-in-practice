package concurrency.chapter4.chapter1;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class NumberRange {
    private final Lock lock = new ReentrantLock();

    @GuardedBy("lock")
    private int lowerBound = 0;
    @GuardedBy("lock")
    private int upperBound = 0;

    public NumberRange(int lower, int upper){
        if(lower > upper){
            throw new IllegalArgumentException("lower can't be greater than upper");
        }
        this.lowerBound = lower;
        this.upperBound = upper;
    }
    public void setLower(int value){
        lock.lock();
        try{
            if(value > upperBound){
                throw new IllegalArgumentException("lower can't be greater than upper");
            }
            lowerBound = value;
        }finally{
            lock.unlock();
        }
    }
    public void setUpper(int value){
        lock.lock();
        try{
            if(value < lowerBound){
                throw new IllegalArgumentException("upper can't be less than lower");
            }
            upperBound = value;
        }finally{
            lock.unlock();
        }
    }

    public boolean isInRange(int value){
        lock.lock();
        try{
            return (value >= lowerBound && value <= upperBound);
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        NumberRange range = new NumberRange(0, 10);
        System.out.println(range.isInRange(5));
        range.setLower(6);
        System.out.println(range.isInRange(5));

    }
}
