package RunnableEg;

class SMS implements Runnable {
    public void run() {
        System.out.println("sms");
    }
}

class Email implements Runnable {
    public void run() {
        System.out.println("email");
    }
}

public class Main {
    public static void main(String[] args) {
        SMS sms = new SMS();
        Email email = new Email();

        Thread smsThread = new Thread(sms);
        Thread Emailthread = new Thread(email);
        smsThread.start();
        Emailthread.start();

    }
}
