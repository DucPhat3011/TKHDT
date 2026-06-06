import java.util.List;

public class RoomController {
	private RoomManager roomManager;
	private RoomFactory roomFactory;

	public RoomController(RoomManager roomManager, RoomFactory roomFactory) {
		this.roomManager = roomManager;
		this.roomFactory = roomFactory;
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	// them quy trinh tao phong moi tren giao dien
	public Room addRoom(int roomNumber, String type, String description, int floor) {
		Room newRoom = roomFactory.createRoom(type);
		if (newRoom != null) {
			newRoom.setRoomNumber(String.valueOf(roomNumber));
			newRoom.setFloor(floor);
			newRoom.setDescription(description);
			roomManager.addRoom(newRoom);
		}
		return newRoom;
	}

	public List<Room> getAllRooms() {
		// TODO Auto-generated method stub
		return roomManager.getAllRooms();
	}
}
