package coure_code;

import java.util.Date;

public class BookingDetail {
	private int detailId;
	private Room room;
	private Date checkInDate;
	private Date checkOutDate;
	private double priceAtBooking;
	private double subTotal;
	private int numberOfGuests;

	public double calculateSubTotal() {
		long diff = checkOutDate.getTime() - checkInDate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);

		if (days <= 0)
			days = 1;

		this.subTotal = days * priceAtBooking;
		return this.subTotal;
	}

	public void updateStayDates(Date checkIn, Date checkOut) {
		this.checkInDate = checkIn;
		this.checkOutDate = checkOut;
		calculateSubTotal();
	}
}
