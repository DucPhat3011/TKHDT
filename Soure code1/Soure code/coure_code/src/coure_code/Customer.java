package coure_code;
import java.util.*;

public class Customer extends User {
    private String identityCard;
    private String nationality;
    private int loyaltyPoints;
    private Date memberSince;

    public Customer(int id, String userName, String password, String email, String fullName, String phone, String address, String identityCard, String nationality) {
        super(id, userName, password, email, fullName, phone, address);
        this.identityCard = identityCard;
        this.nationality = nationality;
        this.loyaltyPoints = 0;
        this.memberSince = new Date();
    }
    public void register() {
        this.memberSince = new Date();
        this.loyaltyPoints = 0;
        System.out.println("Khách hàng đã đăng ký thành viên thành công.");
    }

    public List<Booking> getBookingHistory() {
        System.out.println("Đang truy xuất lịch sử đặt phòng...");
        return new ArrayList<>(); // Trả về danh sách rỗng để không bị lỗi
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
        System.out.println("Điểm tích lũy hiện tại: " + this.loyaltyPoints);
    }

    public List<Booking> getActiveBookings() {
        System.out.println("Đang lấy các đơn đặt phòng hiện tại...");
        return new ArrayList<>();
    }
    
}