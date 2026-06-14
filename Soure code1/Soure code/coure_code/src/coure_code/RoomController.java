
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
            Room newRoom = roomFactory.createRoom(type);
            if (newRoom != null) {
                newRoom.setRoomNumber(String.valueOf(roomNumber));
                newRoom.setFloor(floor);
                newRoom.setDescription(description);
                
                roomManager.addRoom(newRoom); 
                
                refreshView(); 
                view.clearInputFields(); 
                view.showMessage("Thêm phòng mới thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("Không thể tạo loại phòng này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            view.showMessage("Số phòng và số tầng phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshView() {
        if (view != null) {
            view.displayRoomList(roomManager.getAllRooms());
        }
    }

    public List<Room> getAllRooms() {
        return roomManager.getAllRooms();
    }
}