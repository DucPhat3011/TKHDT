import javax.swing.SwingUtilities;

public class MainTestRoom {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			RoomManager roomManager = new RoomManager();
			RoomFactory roomFactory = new RoomFactory();
			RoomController roomController = new RoomController(roomManager, roomFactory);
			RoomManagementView view = new RoomManagementView();
			view.setRoomController(roomController);
			view.setVisible(true);
		});
	}
}
