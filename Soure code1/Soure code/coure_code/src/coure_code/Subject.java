package coure_code;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<IObserver> observers = new ArrayList<>();

    // Thêm người quan sát vào danh sách
    public void attach(IObserver o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    // Xóa người quan sát khỏi danh sách (Sơ đồ có phương thức detach)
    public void detach(IObserver o) {
        observers.remove(o);
    }

    // Thông báo cho tất cả người quan sát khi có thay đổi
    public void notifyAllObservers(String msg) {
        for (IObserver observer : observers) {
            observer.update(msg);
        }
    }
}
