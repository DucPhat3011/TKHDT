import java.util.List;
import java.util.Date;

public class Customer extends User {
	private String identityCard;
	private String nationality;
	private int loyaltyPoints;
	private Date memberSince;

	public Customer(int id, String userName, String password, String email, String fullName, String phone,
			String address, String identityCard, String nationality, int loyaltyPoints, Date memberSince) {
		super(id, userName, password, email, fullName, phone, address);
		this.identityCard = identityCard;
		this.nationality = nationality;
		this.loyaltyPoints = loyaltyPoints;
		this.memberSince = memberSince;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public void register() {
	}

	public List<Booking> getBookingHistory() {
		return null;
	}

	public void updateLoyaltyPoints(int points) {
	}

	public List<Booking> getActiveBookings() {
		return null;
	}

	// Hàm bổ sung để tương thích với NotificationService
	public String getName() {
		return getFullName();
	}
}