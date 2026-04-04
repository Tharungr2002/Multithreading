package Executors;

import java.util.concurrent.*;

class EmailService {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    static class Email implements Runnable {
        public void run() {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("email");
        }
    }

    public static void sendEmail(String recipient) {
        executor.execute(new Email());
    }

    public static void main(String[] args) {
        for(int i=1; i<25; i++) {
            sendEmail("user" + i);
        }
        executor.shutdown();
    }
}


class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(1000);
            return 77;
        });

        System.out.println("Doing other work...");

        Integer result = future.get();
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}



