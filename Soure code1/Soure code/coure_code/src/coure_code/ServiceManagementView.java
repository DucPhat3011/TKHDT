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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ServiceManagementView extends JPanel {
	private ServiceController serviceController;
	
	private DefaultTableModel tableModel;        
	private DefaultTableModel catalogModel;      

	private JTextField txtSTT;
	private JTextField txtServiceName;
	private JTextField txtServicePrice;
	private JTextField txtRoomNumber;

	// Danh muc dich vu co san (STT, Ten, Gia)
	private static final Object[][] CATALOG = {
		{1,  "Dọn phòng",              50_000},
		{2,  "Giặt ủi",               80_000},
		{3,  "Ăn sáng tại phòng",    120_000},
		{4,  "Ăn tối tại phòng",     200_000},
		{5,  "Thuê xe đưa đón",      300_000},
		{6,  "Spa & Massage",        400_000},
		{7,  "Minibar",               50_000},
		{8,  "Internet tốc độ cao",   30_000},
		{9,  "Đặt tour du lịch",     500_000},
		{10, "Phòng gym",             80_000},
	};

	public void setController(ServiceController serviceController) {
		this.serviceController = serviceController;
	}

	public ServiceManagementView() {
		Color bgLeft  = new Color(245, 247, 250);
		Color bgRight = Color.WHITE;
		Color primaryColor = new Color(41, 128, 185);
		Color accentColor  = new Color(39, 174, 96);
		Color textColor    = new Color(44, 62, 80);
		Font mainFont  = new Font("Segoe UI", Font.PLAIN, 14);
		Font boldFont  = new Font("Segoe UI", Font.BOLD, 14);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(bgRight);

		// ===== PANEL TRAI =====
		JPanel pnlLeft = new JPanel(new BorderLayout(0, 12));
		pnlLeft.setBackground(bgRight);
		pnlLeft.setPreferredSize(new Dimension(370, 0));

		// --- BANG DANH MUC DICH VU CO SAN ---
		JPanel pnlCatalog = new JPanel(new BorderLayout(0, 6));
		pnlCatalog.setBackground(bgLeft);
		pnlCatalog.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(218, 223, 230), 1),
			new EmptyBorder(12, 12, 12, 12)));

		JLabel lblCatalogTitle = new JLabel("DANH MỤC DỊCH VỤ");
		lblCatalogTitle.setFont(boldFont);
		lblCatalogTitle.setForeground(primaryColor);
		pnlCatalog.add(lblCatalogTitle, BorderLayout.NORTH);

		catalogModel = new DefaultTableModel(
			new String[]{"STT", "Tên Dịch Vụ", "Đơn Giá (VND)"}, 0) {
			public boolean isCellEditable(int r, int c) { return false; }
		};
		for (Object[] row : CATALOG) {
			catalogModel.addRow(new Object[]{
				row[0], row[1],
				String.format("%,.0f VND", ((Number) row[2]).doubleValue())
			});
		}
		JTable catalogTable = new JTable(catalogModel);
		catalogTable.setFont(mainFont);
		catalogTable.setRowHeight(26);
		catalogTable.setGridColor(new Color(230, 233, 238));
		catalogTable.setSelectionBackground(new Color(232, 244, 252));
		catalogTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		catalogTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		catalogTable.getColumnModel().getColumn(2).setPreferredWidth(110);

		JTableHeader catHeader = catalogTable.getTableHeader();
		catHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));
		catHeader.setBackground(new Color(52, 73, 94));
		catHeader.setForeground(Color.WHITE);
		catHeader.setPreferredSize(new Dimension(0, 30));

		// Click vao hang -> tu dien STT
		catalogTable.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = catalogTable.getSelectedRow();
				if (row >= 0) {
					txtSTT.setText(catalogModel.getValueAt(row, 0).toString());
				}
			}
		});

		JScrollPane catScroll = new JScrollPane(catalogTable);
		catScroll.setPreferredSize(new Dimension(0, 230));
		catScroll.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
		pnlCatalog.add(catScroll, BorderLayout.CENTER);

		// --- FORM NHAP ---
		JPanel pnlInput = new JPanel(new GridBagLayout());
		pnlInput.setBackground(bgLeft);
		pnlInput.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(218, 223, 230), 1),
			new EmptyBorder(15, 15, 15, 15)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(7, 5, 7, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		JLabel lblFormTitle = new JLabel("THÊM DỊCH VỤ VÀO PHÒNG");
		lblFormTitle.setFont(boldFont);
		lblFormTitle.setForeground(primaryColor);
		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
		pnlInput.add(lblFormTitle, gbc);

		Dimension fieldSize = new Dimension(180, 30);
		gbc.gridwidth = 1;

		// STT dich vu
		gbc.gridy = 1; gbc.gridx = 0; gbc.weightx = 0;
		JLabel lblSTT = new JLabel("Nhập STT dịch vụ:");
		lblSTT.setFont(mainFont); lblSTT.setForeground(textColor);
		pnlInput.add(lblSTT, gbc);
		gbc.gridx = 1; gbc.weightx = 1;
		txtSTT = new JTextField();
		txtSTT.setPreferredSize(fieldSize);
		txtSTT.setFont(mainFont);
		txtSTT.setToolTipText("Nhập số thứ tự từ bảng trên hoặc click vào dòng");
		pnlInput.add(txtSTT, gbc);

		// Ten dich vu (tu dong dien)
		gbc.gridy = 2; gbc.gridx = 0; gbc.weightx = 0;
		JLabel lblName = new JLabel("Tên dịch vụ:");
		lblName.setFont(mainFont); lblName.setForeground(textColor);
		pnlInput.add(lblName, gbc);
		gbc.gridx = 1; gbc.weightx = 1;
		txtServiceName = new JTextField();
		txtServiceName.setPreferredSize(fieldSize);
		txtServiceName.setFont(mainFont);
		txtServiceName.setEditable(false);
		txtServiceName.setBackground(new Color(248, 248, 248));
		pnlInput.add(txtServiceName, gbc);

		// Gia dich vu (tu dong dien)
		gbc.gridy = 3; gbc.gridx = 0; gbc.weightx = 0;
		JLabel lblPrice = new JLabel("Đơn giá:");
		lblPrice.setFont(mainFont); lblPrice.setForeground(textColor);
		pnlInput.add(lblPrice, gbc);
		gbc.gridx = 1; gbc.weightx = 1;
		txtServicePrice = new JTextField();
		txtServicePrice.setPreferredSize(fieldSize);
		txtServicePrice.setFont(mainFont);
		txtServicePrice.setEditable(false);
		txtServicePrice.setBackground(new Color(248, 248, 248));
		pnlInput.add(txtServicePrice, gbc);

		// So phong
		gbc.gridy = 4; gbc.gridx = 0; gbc.weightx = 0;
		JLabel lblRoom = new JLabel("Số phòng:");
		lblRoom.setFont(mainFont); lblRoom.setForeground(textColor);
		pnlInput.add(lblRoom, gbc);
		gbc.gridx = 1; gbc.weightx = 1;
		txtRoomNumber = new JTextField();
		txtRoomNumber.setPreferredSize(fieldSize);
		txtRoomNumber.setFont(mainFont);
		pnlInput.add(txtRoomNumber, gbc);

		// Nut them
		gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
		gbc.weighty = 1.0; gbc.insets = new Insets(15, 5, 5, 5);
		JButton btnAdd = new JButton("Thêm Dịch Vụ Vào Phòng");
		btnAdd.setPreferredSize(new Dimension(0, 36));
		btnAdd.setFont(boldFont);
		btnAdd.setBackground(accentColor);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFocusPainted(false);
		btnAdd.setBorderPainted(false);
		pnlInput.add(btnAdd, gbc);

		pnlLeft.add(pnlCatalog, BorderLayout.CENTER);
		pnlLeft.add(pnlInput, BorderLayout.SOUTH);
		add(pnlLeft, BorderLayout.WEST);

		// ===== BANG DICH VU DA THEM =====
		tableModel = new DefaultTableModel(
			new String[]{"ID", "Tên Dịch Vụ", "Đơn Giá (VND)", "Số Phòng"}, 0) {
			public boolean isCellEditable(int r, int c) { return false; }
		};
		JTable serviceTable = new JTable(tableModel);
		serviceTable.setFont(mainFont);
		serviceTable.setRowHeight(30);
		serviceTable.setGridColor(new Color(230, 233, 238));
		serviceTable.setSelectionBackground(new Color(232, 244, 252));
		serviceTable.setSelectionForeground(textColor);

		JTableHeader header = serviceTable.getTableHeader();
		header.setFont(boldFont);
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 35));

		JScrollPane scrollPane = new JScrollPane(serviceTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));

		// Panel phai: tieu de + bang
		JPanel pnlRight = new JPanel(new BorderLayout(0, 8));
		pnlRight.setBackground(bgRight);
		JLabel lblListTitle = new JLabel("  DỊCH VỤ ĐÃ ĐĂNG KÝ THEO PHÒNG");
		lblListTitle.setFont(boldFont);
		lblListTitle.setForeground(textColor);
		pnlRight.add(lblListTitle, BorderLayout.NORTH);
		pnlRight.add(scrollPane, BorderLayout.CENTER);
		add(pnlRight, BorderLayout.CENTER);

		// Su kien nhap STT -> tu dong dien ten + gia
		txtSTT.getDocument().addDocumentListener(new DocumentListener() {
			private void trigger() { fillFromSTT(txtSTT.getText().trim()); }
			public void insertUpdate(DocumentEvent e) { trigger(); }
			public void removeUpdate(DocumentEvent e) { trigger(); }
			public void changedUpdate(DocumentEvent e) { trigger(); }
		});

		// Su kien nut them
		btnAdd.addActionListener(e -> {
			if (serviceController != null) {
				serviceController.handleAddService(
					txtServiceName.getText(),
					txtServicePrice.getText(),
					txtRoomNumber.getText().trim());
			}
		});
	}

	// Dien ten + gia tu STT nhap vao
	private void fillFromSTT(String sttStr) {
		if (sttStr.isEmpty()) {
			txtServiceName.setText("");
			txtServicePrice.setText("");
			return;
		}
		try {
			int stt = Integer.parseInt(sttStr);
			if (stt >= 1 && stt <= CATALOG.length) {
				Object[] row = CATALOG[stt - 1];
				txtServiceName.setText(row[1].toString());
				txtServicePrice.setText(String.valueOf(((Number) row[2]).doubleValue()));
			} else {
				txtServiceName.setText("Không tìm thấy STT này");
				txtServicePrice.setText("");
			}
		} catch (NumberFormatException ex) {
			txtServiceName.setText("");
			txtServicePrice.setText("");
		}
	}

	// Xoa du lieu tren cac o nhap lieu
	public void clearInputFields() {
		txtSTT.setText("");
		txtServiceName.setText("");
		txtServicePrice.setText("");
		txtRoomNumber.setText("");
	}

	// Hien thi danh sach dich vu da dang ky len bang
	public void displayServiceList(List<Services> services) {
		tableModel.setRowCount(0);
		for (Services s : services) {
			tableModel.addRow(new Object[]{
				s.getServiceId(),
				s.getServiceName(),
				String.format("%,.0f VND", s.getUnitPrice()),
				s.getRoomNumber()
			});
		}
	}

	// Hien thi hop thoai thong bao
	public void showMessage(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
}