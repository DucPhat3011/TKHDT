package coure_code;

import java.util.*;

public class Booking {
    private int bookingId;
    private Customer customer;
    private Date bookingDate;
    private double totalAmount;
    private BookingStatus status;
    private String promoCode;
    private String note;
    private List<BookingDetail> details = new ArrayList<>();

    public double calculateTotalAmount() {
        this.totalAmount = details.stream()
                .mapToDouble(BookingDetail::calculateSubTotal)
                .sum();
        return this.totalAmount;
    }

    public void confirmBooking() {
        this.status = BookingStatus.CONFIRMED;
        System.out.println("Đơn hàng đã được xác nhận!");
    }

    public boolean cancelBooking() {
        if (this.status != BookingStatus.COMPLETED) {
            this.status = BookingStatus.CANCELLED;
            return true;
        }
        return false;
    }

    public int getTotalNights() {
        int totalNights = 0;
        for (BookingDetail detail : details) {
            // totalNights += detail.getNights(); 
        }
        return totalNights; 
    }

	public String getBookingId() {
		// TODO Auto-generated method stub
		return null;
	}
}