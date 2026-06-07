import java.util.Date;

public class StayRecord {
	private int recordId;
	private Date actualCheckIn;
	private Date actualCheckOut;
	private BookingDetail bookingDetail;
	private String checkInNote;

	public StayRecord(int recordId, Date actualCheckIn, Date actualCheckOut, BookingDetail bookingDetail,
			String checkInNote) {
		this.recordId = recordId;
		this.actualCheckIn = actualCheckIn;
		this.actualCheckOut = actualCheckOut;
		this.bookingDetail = bookingDetail;
		this.checkInNote = checkInNote;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Date getActualCheckIn() {
		return actualCheckIn;
	}

	public void setActualCheckIn(Date actualCheckIn) {
		this.actualCheckIn = actualCheckIn;
	}

	public Date getActualCheckOut() {
		return actualCheckOut;
	}

	public void setActualCheckOut(Date actualCheckOut) {
		this.actualCheckOut = actualCheckOut;
	}

	public BookingDetail getBookingDetail() {
		return bookingDetail;
	}

	public void setBookingDetail(BookingDetail bookingDetail) {
		this.bookingDetail = bookingDetail;
	}

	public String getCheckInNote() {
		return checkInNote;
	}

	public void setCheckInNote(String checkInNote) {
		this.checkInNote = checkInNote;
	}

	public void addService(Services service, int quantity) {
	}

	public void removeService(int serviceId) {
	}

	public double calculateTotalServiceCost() {
		return 0.0;
	}

	public void updateActualCheckOut(Date time) {
	}

	public void applyExtraCharges(String description, double amount) {
	}

	public Invoice generateInvoice() {
		return null;
	}

}