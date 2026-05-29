package coure_code;

public class EmailService implements IObserver {
    public void update(String message) {
        System.out.println("Email sent: " + message);
    }
}
