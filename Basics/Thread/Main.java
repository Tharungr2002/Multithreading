package Thread;

class SMS extends Thread {
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("SMS running: ");
    }
}

class Email extends Thread {
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email running: ");
    }
}

public class Main {
    public static void main(String[] args) {
        SMS myThread = new SMS();
        Email email = new Email();
        myThread.start();
        System.out.println("Task 1 ongoing...");
        email.start();
        System.out.println("Task 2 ongoing...");

        //wait for all to finish
        try{
            myThread.join();
            email.join();
            System.out.println("finish");
        }
        catch(Exception e) {

        }
    }
}
