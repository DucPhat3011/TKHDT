
public class RoomType {
	private int typeId;
	private String typeName;
	private double pricePerNight;
	private int capacity;
	private String amenities;
	private int maxOccupancy;

	public RoomType(int typeId, String typeName, double pricePerNight, int capacity, String amenities,
			int maxOccupancy) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.pricePerNight = pricePerNight;
		this.capacity = capacity;
		this.amenities = amenities;
		this.maxOccupancy = maxOccupancy;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	// cao nhat gia co ban cua loai phong
	public void updateBasePrice(double newPrice) {
		this.pricePerNight = newPrice;

	}

	// lay thong tin chi tiet cua loai phong
	public String getRoomTypeDetails() {
		return "---THONG TIN CHI TIET LOAI PHONG---\n" + 
	"Ma loai phong: " + typeId + "\n" + 
	"Ten loai phong: " + typeName + "\n" + 
	"Gia phong/Dem: " + pricePerNight + " VND\n" +
	"Suc chua mac dinh: " + capacity + " nguoi\n" +
	"Suc chua toi da: " + maxOccupancy + " nguoi\n" +
	"Tien nghi di kem: " + amenities;
	}

}
