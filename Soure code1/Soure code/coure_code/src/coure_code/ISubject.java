
public interface ISubject {
	// Them
	void attach(IObserver obs);

	// Xoa
	void detach(IObserver obs);

	// Thong bao
	void notifyObservers();
}
