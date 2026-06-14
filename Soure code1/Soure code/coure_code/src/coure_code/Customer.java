import java.util.List;
import java.util.ArrayList;
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

	// Dang ky tai khoan moi
	public void register() {
		this.memberSince = new Date();
        this.loyaltyPoints = 0;
        System.out.println("Khach hang da dang ky thanh vien thanh cong.");
	}

	// Lay lich su booking cua khach hang
	public List<Booking> getBookingHistory() {
		System.out.println("Dang truy xuat lich su dat phong...");
        return new ArrayList<>(); 
	}

	// Cap nhat diem tich luy
	public void updateLoyaltyPoints(int points) {
		this.loyaltyPoints += points;
        System.out.println("Diem tich luy hien tai: " + this.loyaltyPoints);
	}

	// Lay danh sach booking con hieu luc
	public List<Booking> getActiveBookings() {
		 System.out.println("Dang lay cac don dat phong hien tai...");
	     return new ArrayList<>();
	}

	// Customer - NotificationService
	public String getName() {
		return getFullName();
	}
}