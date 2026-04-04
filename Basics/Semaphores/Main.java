package Semaphores;

import java.util.concurrent.Semaphore;
import java.util.stream.Gatherer;

class ParkingLot {
    Semaphore semaphore = new Semaphore(3);

    public void park(String name ) {
        try{
            semaphore.acquire();
            System.out.println(name + "is parked");
            System.out.println(name + "is leaving");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            semaphore.release();

        }

    }

}

class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        for(int i=0; i<6; i++) {
            int id =i;
            new Thread(()-> parkingLot.park("car"+ id)).start();
        }
    }
}