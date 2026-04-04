package Lock;

import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {

    ReentrantLock lock = new ReentrantLock();

    public void bookTicket() {
        lock.lock();
        try{
            System.out.println("booking ticket");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("unlocking ");
            lock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem tb = new TicketBookingSystem();

        Thread t1 = new Thread(() -> tb.bookTicket());
        Thread t2 = new Thread(() -> tb.bookTicket());

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



