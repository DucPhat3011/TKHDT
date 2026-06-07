import java.util.Date;

public class Booking {
    private int bookingId;
    private Customer customer;
    private Date bookingDate;
    private double totalAmount;
    private BookingStatus status;
    private String promoCode;
    private String note;

    public Booking(int bookingId, Customer customer, Date bookingDate, double totalAmount, BookingStatus status,
			String promoCode, String note) {
		this.bookingId = bookingId;
		this.customer = customer;
		this.bookingDate = bookingDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.promoCode = promoCode;
		this.note = note;
	}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public boolean cancel(String reason) {
        return false;
    }

    public double getRefundAmount() {
        return 0.0;
    }

    public void updateBookingStatus(BookingStatus status) {
    }

    public boolean isEligibleForCancellation() {
        return false;
    }

    public double getAvailableRefundPercentage() {
        return 0.0;
    }
}