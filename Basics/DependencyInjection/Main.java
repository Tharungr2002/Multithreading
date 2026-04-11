package DependencyInjection;

interface Notification {
    void send(String message);
}

class EmailNot implements Notification {
    @Override
    public void send(String message) {
        System.out.println("send by email");
    }
}

class UserService {
    Notification notification;

    UserService(Notification notification) {
        this.notification = notification;
    }

    void sendSerivce(String message) {
        notification.send(message);
    }
}


public class Main {
    public static void main(String[] args) {
        Notification not = new EmailNot();
        UserService obj = new UserService(not);
        obj.sendSerivce("hi");
    }

}
