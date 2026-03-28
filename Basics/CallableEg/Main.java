package CallableEg;

import java.util.concurrent.*;

class SMS implements Runnable {
    public void run() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sms");
    }
}

class Email implements Runnable {
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("email");
    }
}

class ETA implements Callable<String> {
    public String call() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("ETA is running");
        return "ETA is 25 minutes";
    }
}

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        SMS sms = new SMS();
        Email email = new Email();
        ETA eta = new ETA();

        Future<String> future = executorService.submit(eta);
        executorService.submit(sms);
        executorService.submit(email);
        String ans = future.get();
        System.out.println(ans);
    }
}
