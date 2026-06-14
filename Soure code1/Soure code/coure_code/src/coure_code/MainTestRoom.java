
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTestRoom {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			RoomManager roomManager = new RoomManager();
			RoomFactory roomFactory = new RoomFactory();
			RoomManagementView view = new RoomManagementView();
			
			RoomController roomController = new RoomController(roomManager, roomFactory, view);
			
			JFrame mainFrame = new JFrame("He Thong Quan Ly Phong Khach San");
			mainFrame.setSize(900, 500);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLocationRelativeTo(null);
			
			mainFrame.add(view);
			mainFrame.setVisible(true);
		});
	}
}