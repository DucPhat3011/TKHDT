import java.util.List;
import javax.swing.JOptionPane;

public class RoomController {
	private RoomManager roomManager;
	private RoomFactory roomFactory;
	private RoomManagementView view;

	public RoomController(RoomManager roomManager, RoomFactory roomFactory, RoomManagementView view) {
		this.roomManager = roomManager;
		this.roomFactory = roomFactory;
		this.view = view;
		this.view.setRoomController(this);
		refreshView();
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	// Them phong moi
	public void handleAddRoom(String numberStr, String type, String floorStr, String description) {
		try {
			if (numberStr.trim().isEmpty() || floorStr.trim().isEmpty()) {
				view.showMessage("Vui lòng nhập đầy đủ số phòng và số tầng!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				return;
			}
			int roomNumber = Integer.parseInt(numberStr.trim());
			int floor = Integer.parseInt(floorStr.trim());
			if (roomNumber <= 0 || floor <= 0) {
				view.showMessage("Số phòng và số tầng phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// Kiem tra so tang tuong ung voi loai phong nao
			if (floor >= 1 && floor <= 4 && !type.equalsIgnoreCase("STANDARD")) {
				view.showMessage("Tầng 1 đến tầng 4 chỉ được là phòng STANDARD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (floor >= 5 && !type.equalsIgnoreCase("VIP")) {
				view.showMessage("Tầng 5 trở lên chỉ được là phòng VIP!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Kiem tra tinh hop le cua so phong theo tang
			String roomNumberStr = String.valueOf(roomNumber);
			String floorStr2 = String.valueOf(floor);
			if (!roomNumberStr.startsWith(floorStr2)) {
				view.showMessage(
						"Số phòng không hợp lệ!\n" + "Tầng " + floor + " thì số phòng phải bắt đầu bằng " + floor
								+ "\nVí dụ: " + floor + "01, " + floor + "02, " + floor + "03...",
						"Lỗi Số Phòng", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Kiem tra trung so phong
			for (Room r : roomManager.getAllRooms()) {
				if (r.getRoomNumber().equals(roomNumberStr)) {
					view.showMessage("Số phòng " + roomNumber + " đã tồn tại!\nVui lòng chọn số phòng khác.",
							"Trùng Số Phòng", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			// Tao va them phong vao quan ly
			Room newRoom = roomFactory.createRoom(type);
			if (newRoom != null) {
				newRoom.setRoomNumber(roomNumberStr);
				newRoom.setFloor(floor);
				newRoom.setDescription(description);
				roomManager.addRoom(newRoom);
				refreshView();
				view.clearInputFields();
				view.showMessage("Thêm phòng " + roomNumber + " (Tầng " + floor + ") thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				view.showMessage("Không thể tạo loại phòng này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException ex) {
			view.showMessage("Số phòng và số tầng phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Cap nhat danh sach phong tren giao dien
	public void refreshView() {
		if (view != null) {
			view.displayRoomList(roomManager.getAllRooms());
		}
	}

	// Lay danh sach tat ca cac phong
	public List<Room> getAllRooms() {
		return roomManager.getAllRooms();
	}
}