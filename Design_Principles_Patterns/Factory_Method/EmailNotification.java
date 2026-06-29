public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Email notification sent.");
    }
}