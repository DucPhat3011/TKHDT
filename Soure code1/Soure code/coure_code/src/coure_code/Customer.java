package coure_code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User {
	private String identityCard;
	private String nationality;
	private int loyaltyPoints;
	private Date memberSince;

	public void register() {
		System.out.println("Khách hàng " + this.getFullName() + " đã đăng ký thành công.");
	}

	public List<Booking> getBookingHistory() {
		return new ArrayList<Booking>();
	}

	public void updateLoyaltyPoints(int points) {
		this.loyaltyPoints += points;
	}

	public List<Booking> getActiveBookings() {
		return new ArrayList<Booking>();
	}
}
