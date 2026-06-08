
import java.time.LocalDateTime;
import java.util.*;

public class Booking implements ISubject {

	private int bookingId;
	private Customer customer;
	private LocalDateTime bookingDate;
	private double totalAmount;
	private BookingStatus status;
	private String promoCode;
	private String note;
	private List<IObserver> observers;
	private List<BookingDetail> bookingDetails;

	public Booking(int bookingId, Customer customer, double totalAmount, String promoCode, String note) {
		this.bookingId = bookingId;
		this.customer = customer;
		this.bookingDate = LocalDateTime.now();
		this.totalAmount = totalAmount;
		this.status = BookingStatus.PENDING;
		this.promoCode = promoCode;
		this.note = note;
		this.observers = new ArrayList<IObserver>();
		this.bookingDetails = new ArrayList<BookingDetail>();
	}

	// Them chi tiet dat phong
	public void addBookingDetail(BookingDetail detail) {
		this.bookingDetails.add(detail);
		calculateTotalAmount();
	}

	// Tinh tong tien dua tren subtotal cua tat ca cac chi tiet phong
	public double calculateTotalAmount() {
		this.totalAmount = 0;
		for (BookingDetail bookingDetail : bookingDetails) {
			this.totalAmount += bookingDetail.calculateSubTotal();
		}
		return this.totalAmount;
	}

	// Them observer vao danh sach theo doi
	@Override
	public void attach(IObserver obs) {
		observers.add(obs);
	}

	// Xoa observer khoi danh sach theo doi
	@Override
	public void detach(IObserver obs) {
		observers.remove(obs);
	}

	// Thong bao cho tat ca cac observer khi co thay doi
	@Override
	public void notifyObservers() {
		String message = "ID phòng: " + bookingId + " - Trạng thái: " + status;
		for (IObserver observer : observers) {
			observer.update(message);
		}
	}

	// Huy booking
	public boolean cancel(String reason) {
		if (!isEligibleForCancellation()) {
			return false;
		}
		this.note = reason;
		this.status = BookingStatus.CANCELLED;
		notifyObservers();
		return true;
	}

	// Tinh tien hoan lai khi huy phong
	public double getRefundAmount() {
		if (!isEligibleForCancellation()) {
			return 0.0;
		}
		if (status == BookingStatus.PENDING) {
			return totalAmount;
		} else {
			return totalAmount * 0.5;
		}
	}

	// Cap nhat trang thai booking va thong bao cho observer
	public boolean updateBookingStatus(BookingStatus newStatus) {
		this.status = newStatus;
		notifyObservers();
		return true;
	}

	// Kiem tra booking co du dieu kien huy khong
	public boolean isEligibleForCancellation() {
		return status == BookingStatus.PENDING || status == BookingStatus.CONFIRMED;
	}

	// Tinh phan tram tien hoan lai
	public double getAvailableRefundPercentage() {
		if (!isEligibleForCancellation()) {
			return 0.0; // Khong the huy → 0%
		}
		if (status == BookingStatus.PENDING) {
			return 100.0; // Hoan 100%
		}
		return 50.0; // Hoan 50%
	}

	public int getBookingId() {
		return bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public String getNote() {
		return note;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void setNote(String note) {
		this.note = note;
	}

}