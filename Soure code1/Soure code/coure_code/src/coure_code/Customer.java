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
        System.out.println("Khach hang da dang ky thanh vien thanh cong.");
    }

    public List<Booking> getBookingHistory() {
        System.out.println("Dang truy xuat lich su dat phong...");
        return new ArrayList<>(); 
    }

    public void updateLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
        System.out.println("Diem tich luy hien tai: " + this.loyaltyPoints);
    }

    public List<Booking> getActiveBookings() {
        System.out.println("Dang lay cac don dat phong hien tai...");
        return new ArrayList<>();
    }
    
}