import java.util.Date;

public class BookingDetail {
    private int detailId;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private double priceAtBooking;
    private double subTotal;
    private int numberOfGuests;

    public BookingDetail(int detailId, Room room, Date checkInDate, Date checkOutDate, double priceAtBooking,
			double subTotal, int numberOfGuests) {
		this.detailId = detailId;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.priceAtBooking = priceAtBooking;
		this.subTotal = subTotal;
		this.numberOfGuests = numberOfGuests;
	}

	public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getPriceAtBooking() {
        return priceAtBooking;
    }

    public void setPriceAtBooking(double priceAtBooking) {
        this.priceAtBooking = priceAtBooking;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
    
    public double calculateSubTotal() {
        return 0.0;
    }

    public void updateStayDates(Date in, Date out) {
    }

}