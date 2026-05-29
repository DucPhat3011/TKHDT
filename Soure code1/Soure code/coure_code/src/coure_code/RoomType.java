package coure_code;

public class RoomType {
    private int typeId;
    private String typeName;
    private double pricePerNight;
    private int capacity;
    private String amenities;
    private int maxOccupancy;
   
    public RoomType(int typeId, String typeName, double pricePerNight, int capacity, String amenities, int maxOccupancy) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.amenities = amenities;
        this.maxOccupancy = maxOccupancy;
    }
    
    public void updateBasePrice(double newPrice) {
        this.pricePerNight = newPrice;
    }
    
    public String getRoomTypeDetails() {
        return String.format(
            "Loại phòng: %s | Giá: %.2f | Sức chứa: %d người | Tiện nghi: %s",
            typeName, pricePerNight, capacity, amenities);
    }
}
