package coure_code;

import java.util.Date;

public class Room {
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

	public void updateStatus(RoomStatus status) {
		this.status = status;
	}

	public boolean isAvailable(Date from, Date to) {
		return this.status == RoomStatus.AVAILABLE;
	}
}
