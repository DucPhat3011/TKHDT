import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	private List<Room> rooms = new ArrayList<Room>();
	private List<RoomType> roomTypes = new ArrayList<RoomType>();

	// them phong moi
	public Room addRoom(Room newRoom) {
		if (newRoom != null) {
			rooms.add(newRoom);
		}
		return newRoom;

	}

	// cap nhat thong tin phong
	public boolean updateRoom(int roomId, Room newData) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getRoomId() == roomId) {
				rooms.set(i, newData);
				return true;
			}
		}
		return false;
	}

	// xoa phong
	public boolean deleteRoom(int roomId) {
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getRoomId() == roomId) {
				rooms.remove(i);
				return true;
			}

		}
		return false;

	}

	// tim phong bang id
	public Room getRoomById(int roomId) {
		for (Room r : rooms) {
			if (r.getRoomId() == roomId) {
				return r;
			}
		}
		return null;

	}

	// lay danh sach phong trong
	public List<Room> getAvailableRooms(Date checkIn, Date checkOut) {
		List<Room> availableList = new ArrayList<Room>();
		for (Room r : rooms) {
			if (r.isAvailable(checkIn, checkOut)) {
				availableList.add(r);
			}
		}
		return availableList;

	}

	// cap nhat trang thai phong
	public void updateRoomStatus(int roomId, RoomStatus status) {
		Room room = getRoomById(roomId);
		if (room != null) {
			room.updateStatus(status);
		}

	}

	// lay danh sach phong theo loai
	public List<Room> getRoomsByType(String type) {
		List<Room> list = new ArrayList<Room>();
		for (Room r : rooms) {
			if (r.getType().getTypeName().equalsIgnoreCase(type)) {
				list.add(r);
			}
		}
		return list;

	}
	
	public List<Room> getAllRooms() {
		return this.rooms;
	}
}
