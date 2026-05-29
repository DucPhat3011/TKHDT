package coure_code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StayRecord {
	private int recordId;
    private Date actualCheckIn;
    private Date actualCheckOut;
    private String checkInNote;
    private BookingDetail bookingDetail;
    private List<ServiceUsage> serviceUsages;

    public StayRecord(int recordId, Date actualCheckIn, Date actualCheckOut, String checkInNote,
BookingDetail bookingDetail, List<ServiceUsage> serviceUsages) {
		this.recordId = recordId;
		this.actualCheckIn = actualCheckIn;
		this.actualCheckOut = actualCheckOut;
		this.checkInNote = checkInNote;
		this.bookingDetail = bookingDetail;
		this.serviceUsages = serviceUsages;
		this.serviceUsages = new ArrayList<>();
	}

	public void updateActualTime(Date in, Date out) {
        this.actualCheckIn = in;
        this.actualCheckOut = out;
    }

    public int getDuration() {
        if (actualCheckIn != null && actualCheckOut != null) {
            long diff = actualCheckOut.getTime() - actualCheckIn.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24));
        }
        return 0;
    }

    public List<ServiceUsage> getUnpaidServices() {
        return this.serviceUsages;
    }
}
