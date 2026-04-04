package ScheduledLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {

    ReentrantLock lock = new ReentrantLock();

    public void bookTicket() throws InterruptedException {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            Thread.sleep(5000);
            try {
                System.out.println("Lock acquired");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock in time");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem tb = new TicketBookingSystem();

        Thread t1 = new Thread(() -> {
            try {
                tb.bookTicket();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                tb.bookTicket();
            } catch (InterruptedException e) {}
        });


        t1.start();
        t2.start();

        try{
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}





