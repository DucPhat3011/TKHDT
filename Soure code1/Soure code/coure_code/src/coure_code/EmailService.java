
import java.util.List;

public class EmailService implements IObserver {


    @Override
    public void update(String message) {
        System.out.println("[EmailService] Nhận thông báo: " + message);
    }
}