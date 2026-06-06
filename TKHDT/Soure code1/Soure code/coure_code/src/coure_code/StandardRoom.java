
public class StandardRoom extends Room {
	private String bedType;

	public StandardRoom(int roomId, String roomNumber, RoomStatus status, RoomType type, int floor, String description,
			String bedType) {
		super(roomId, roomNumber, status, type, floor, description);
		this.bedType = bedType;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	// tinh gia phong tieu chuan
	@Override
	public double calculatePrice() {
		// TODO Auto-generated method stub
		return getType().getPricePerNight();
	}

}
