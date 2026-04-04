package ReadWriteLocks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedData {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    int value =0;
    public void readData() {
        lock.readLock().lock();
        try{
            System.out.println("lock the read");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.readLock().unlock();
        }
    }
    public void writeData() {
        lock.writeLock().lock();
        try {
            value++;
            System.out.println("lock the write"+ value);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
         finally {
            lock.writeLock().unlock();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Runnable reader = ()-> sharedData.readData();
        Runnable writer = ()-> sharedData.writeData();

        Thread t1 = new Thread(reader);
        Thread t2 = new Thread(reader);
        Thread t3 = new Thread(writer);

        t1.start();
        t2.start();
        t3.start();

    }
}
