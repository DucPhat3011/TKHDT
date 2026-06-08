
public interface ISubject {
    //Thêm
    void attach(IObserver obs);
    //xóa
    void detach(IObserver obs);
    //Thông báo
    void notifyObservers();
}
