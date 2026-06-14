import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class ServiceManagementView extends JPanel {
	private ServiceController serviceController;
	private DefaultTableModel tableModel;

	private JTextField txtServiceName;
	private JTextField txtServicePrice;
	private JTextField txtRoomNumber; // O nhap so phong

	public void setController(ServiceController serviceController) {
		this.serviceController = serviceController;
	}

	public ServiceManagementView() {
		Color bgLeft = new Color(245, 247, 250);
		Color bgRight = Color.WHITE;
		Color primaryColor = new Color(41, 128, 185);
		Color textColor = new Color(44, 62, 80);
		Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(bgRight);

		JPanel pnlInput = new JPanel(new GridBagLayout());
		pnlInput.setBackground(bgLeft);
		pnlInput.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1), new EmptyBorder(20, 20, 20, 20)));
		pnlInput.setPreferredSize(new Dimension(360, 0));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 5, 8, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		// Tieu de
		JLabel lblTitle = new JLabel("THÔNG TIN DỊCH VỤ");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitle.setForeground(primaryColor);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		pnlInput.add(lblTitle, gbc);

		Dimension fieldSize = new Dimension(200, 32);
		gbc.gridwidth = 1;

		// Ten dich vu
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.weightx = 0;
		JLabel lblName = new JLabel("Tên dịch vụ:");
		lblName.setFont(mainFont);
		lblName.setForeground(textColor);
		pnlInput.add(lblName, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		txtServiceName = new JTextField();
		txtServiceName.setPreferredSize(fieldSize);
		txtServiceName.setFont(mainFont);
		pnlInput.add(txtServiceName, gbc);

		// Gia dich vu
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0;
		JLabel lblPrice = new JLabel("Giá dịch vụ:");
		lblPrice.setFont(mainFont);
		lblPrice.setForeground(textColor);
		pnlInput.add(lblPrice, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		txtServicePrice = new JTextField();
		txtServicePrice.setPreferredSize(fieldSize);
		txtServicePrice.setFont(mainFont);
		pnlInput.add(txtServicePrice, gbc);

		// So phong
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.weightx = 0;
		JLabel lblRoom = new JLabel("Số phòng:");
		lblRoom.setFont(mainFont);
		lblRoom.setForeground(textColor);
		pnlInput.add(lblRoom, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		txtRoomNumber = new JTextField();
		txtRoomNumber.setPreferredSize(fieldSize);
		txtRoomNumber.setFont(mainFont);
		pnlInput.add(txtRoomNumber, gbc);

		// Nut them dich vu moi
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(20, 5, 5, 5);
		JButton btnAdd = new JButton("Thêm Dịch Vụ Mới");
		btnAdd.setPreferredSize(new Dimension(0, 38));
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdd.setBackground(primaryColor);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorderPainted(false);
		pnlInput.add(btnAdd, gbc);

		add(pnlInput, BorderLayout.WEST);

		// --- BANG DU LIEU ---
		tableModel = new DefaultTableModel(new String[] { "ID Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá (VND)", "Số Phòng" }, 0);
		JTable serviceTable = new JTable(tableModel);
		serviceTable.setFont(mainFont);
		serviceTable.setRowHeight(30);
		serviceTable.setGridColor(new Color(230, 233, 238));
		serviceTable.setSelectionBackground(new Color(232, 244, 252));
		serviceTable.setSelectionForeground(textColor);

		JTableHeader header = serviceTable.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 35));

		JScrollPane scrollPane = new JScrollPane(serviceTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
		add(scrollPane, BorderLayout.CENTER);

		btnAdd.addActionListener(e -> {
			if (serviceController != null) {
				serviceController.handleAddService(txtServiceName.getText(), txtServicePrice.getText(), txtRoomNumber.getText());
			}
		});
	}

	public void clearInputFields() {
		txtServiceName.setText("");
		txtServicePrice.setText("");
		txtRoomNumber.setText("");
	}

	public void displayServiceList(List<Services> services) {
		tableModel.setRowCount(0);
		for (Services s : services) {
			tableModel.addRow(new Object[] { s.getServiceId(), s.getServiceName(),
					String.format("%,.0f VND", s.getUnitPrice()), s.getRoomNumber() });
		}
	}

	public void showMessage(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
}