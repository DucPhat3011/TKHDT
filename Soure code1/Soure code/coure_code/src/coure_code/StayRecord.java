import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StayRecord {
	private int recordId;
	private Date actualCheckIn;
	private Date actualCheckOut;
	private BookingDetail bookingDetail;
	private String checkInNote;
	private List<ServiceUsage> serviceUsages;

	public StayRecord(int recordId, Date actualCheckIn, Date actualCheckOut, BookingDetail bookingDetail,
			String checkInNote) {
		this.recordId = recordId;
		this.actualCheckIn = actualCheckIn;
		this.actualCheckOut = actualCheckOut;
		this.bookingDetail = bookingDetail;
		this.checkInNote = checkInNote;
		this.serviceUsages = new ArrayList<>();
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

	// Them dich vu vao ky luu tru cua khach hang
	public void addService(Services service, int quantity) {
		ServiceUsage usage = new ServiceUsage(serviceUsages.size() + 1, quantity, service, quantity, this);
        serviceUsages.add(usage);
        System.out.println("Da cap nhat dich vu: " + service.getServiceName() );
	}

	// Xoa dich vu
	public void removeService(int serviceId) {
		serviceUsages.removeIf(usage -> usage.getService().getServiceId() == serviceId);
	}

	// Tong chi phi dich vu
	public double calculateTotalServiceCost() {
		double total = 0;
        for (ServiceUsage usage : serviceUsages) {
            total += usage.calculateServiceCharge();
        }
        return total;
	}

	// Cap nhat thoi gian check-out thuc te
	public void updateActualCheckOut(Date time) {
		this.actualCheckOut = time;
	}

	// Them khoan phu thu vao hoa don
	public void applyExtraCharges(String description, double amount) {
		System.out.println("Phu Thu: " + description + " - " + amount);
	}

	// Tao hoa don thanh toan cho khach hang
	public Invoice generateInvoice() {
		System.out.println("Dang Xuat Hoa Don");
        return null;
	}

}