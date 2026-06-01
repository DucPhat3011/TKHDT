import java.sql.Date;

public abstract class Room {
	private int roomId;
	private String roomNumber;
	private RoomStatus status;
	private RoomType type;
	private int floor;
	private String description;

	public Room(int roomId, String roomNumber, RoomStatus status, RoomType type, int floor, String description) {
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.status = status;
		this.type = type;
		this.floor = floor;
		this.description = description;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// cap nhat trang thai phong
	public void updateStatus(RoomStatus status) {
		this.status = status;

	}

	// kiem tra phong trong
	public boolean isAvailable(Date from, Date to) {
		return this.status == RoomStatus.AVAILABLE;

	}

	// tinh gia phong
	public abstract double calculatePrice();

}
