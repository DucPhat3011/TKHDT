import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RoomManagementView extends JFrame {
	private RoomController roomController;
	private List<Room> roomListData = new ArrayList<>();
	private DefaultTableModel tableModel;

	public void setRoomController(RoomController roomController) {
		this.roomController = roomController;
	}

	public RoomManagementView() {
		setTitle("Quan Ly Phong Khach San");
		setSize(900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(5, 5));

		JTextField txtRoomNumber = new JTextField();
		JTextField txtFloor = new JTextField();
		JTextField txtDescription = new JTextField();
		JComboBox<String> cbType = new JComboBox<String>(new String[] { "STANDARD", "VIP" });

		tableModel = new DefaultTableModel(
				new String[] { "ID Phong", "So Phong", "Loai", "Tang", "Mo Ta", "Trang Thai", "Gia/Dem" }, 0);
		JTable roomTable = new JTable(tableModel);

		// khung nhap thong tin
		JPanel pnlInput = new JPanel(new GridLayout(4, 2, 5, 5));
		pnlInput.add(new JLabel(" So phong:"));
		pnlInput.add(txtRoomNumber);
		pnlInput.add(new JLabel(" Loai phong:"));
		pnlInput.add(cbType);
		pnlInput.add(new JLabel(" So tang:"));
		pnlInput.add(txtFloor);
		pnlInput.add(new JLabel(" Mo ta:"));
		pnlInput.add(txtDescription);
		add(pnlInput, BorderLayout.NORTH);

		// bang hien thi
		add(new JScrollPane(roomTable), BorderLayout.CENTER);

		// nut bam
		JPanel pnlButtons = new JPanel();
		JButton btnAdd = new JButton("Them Phong");
		pnlButtons.add(btnAdd);
		add(pnlButtons, BorderLayout.SOUTH);

		// xu ly them phong
		btnAdd.addActionListener(e -> onAddRoomClick(txtRoomNumber, cbType, txtFloor, txtDescription));
	}

	// hien thi danh sach phong len bang
	public void displayRoomList(List<Room> rooms) {
		this.roomListData = rooms;
		tableModel.setRowCount(0);
		for (Room r : rooms) {
			tableModel.addRow(new Object[] { r.getRoomId(), r.getRoomNumber(), r.getType().getTypeName(), r.getFloor(),
					r.getDescription(), r.getStatus().name(), String.format("%.0f VND", r.calculatePrice()) });
		}
		tableModel.fireTableDataChanged();
	}

	// xu ly su kien bam nut them phong
	public void onAddRoomClick(JTextField txtNumber, JComboBox<String> cbType, JTextField txtFloor,
			JTextField txtDesc) {
		try {
			String number = txtNumber.getText().trim();
			String type = cbType.getSelectedItem().toString();
			String floorStr = txtFloor.getText().trim();
			String description = txtDesc.getText().trim();

			if (number.isEmpty() || floorStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui long nhap day du so phong va so tang!");
				return;
			}

			int roomNumber = Integer.parseInt(number);
			int floor = Integer.parseInt(floorStr);
			
			if(roomNumber <= 0 || floor <= 0) {
				JOptionPane.showMessageDialog(this, "So phong va so tang phai lon hon 0!");
				return;
			}
			if (roomController != null) {
				Room createdRoom = roomController.addRoom(roomNumber, type, description, floor);
				if (createdRoom != null) {
					displayRoomList(roomController.getAllRooms());
					JOptionPane.showMessageDialog(this, "Them phong thanh cong!");

					txtNumber.setText("");
					txtFloor.setText("");
					txtDesc.setText("");
				} else {
					JOptionPane.showMessageDialog(this, "Khong the tao phong! Vui long kiem tra lai");
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "So phong va so tang phai la so nguyen hop le!");
		}
	}
}
