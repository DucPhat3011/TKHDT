
import java.util.Date;

public class BookingDetail {
	private int detailId;
	private Room room;
	private Date checkInDate;
	private Date checkOutDate;
	private double priceAtBooking;
	private double subTotal;
	private int numberOfGuests;

	// Tinh tien chi tiet dat phong
	public double calculateSubTotal() {
		long diff = checkOutDate.getTime() - checkInDate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);

		if (days <= 0)
			days = 1;

		this.subTotal = days * priceAtBooking;
		return this.subTotal;
	}

	// Cap nhat ngay nhan/tra
	public void updateStayDates(Date checkIn, Date checkOut) {
		this.checkInDate = checkIn;
		this.checkOutDate = checkOut;
		calculateSubTotal();
	}

	public int getDetailId() {
		return detailId;
	}

	public Room getRoom() {
		return room;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public double getPriceAtBooking() {
		return priceAtBooking;
	}

	public double getSubTotal() {
		return subTotal;

	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;

	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;

	}

	public void setPriceAtBooking(double priceAtBooking) {
		this.priceAtBooking = priceAtBooking;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;

	}

}
