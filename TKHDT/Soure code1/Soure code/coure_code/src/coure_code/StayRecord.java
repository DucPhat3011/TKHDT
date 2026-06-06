package coure_code;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StayRecord {
    private int stayId;
    private Customer customer;
    private Room room; // Lớp Room bạn đã code
    private Date checkInDate;
    private Date checkOutDate;
    
    private List<ServiceUsage> usedServices;
    private IPriceStrategy priceStrategy; // Dependency Injection cho Strategy Pattern

    public StayRecord(int stayId, Customer customer, Room room, Date checkInDate) {
        this.stayId = stayId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.usedServices = new ArrayList<>();
        this.priceStrategy = new RegularPrice(); // Mặc định là ngày thường
    }

    public void setPriceStrategy(IPriceStrategy strategy) {
        this.priceStrategy = strategy;
    }

    public void addServiceUsage(Service service, int quantity, Date date) {
        usedServices.add(new ServiceUsage(service, quantity, date));
    }

    public double calculateTotal() {
        double roomBasePrice = room.tinhtien(); // Lấy hàm tinhtien() từ VipRoom/StandardRoom của bạn
        double finalRoomPrice = priceStrategy.calculatePrice(roomBasePrice);

        double servicesCost = 0;
        for (ServiceUsage usage : usedServices) {
            servicesCost += usage.getTotalCost();
        }

        return finalRoomPrice + servicesCost;
    }
}