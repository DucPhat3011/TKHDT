
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class BookingView extends JPanel {

	private BookingController bookingController;

	private JTextField txtCustomerId;
	private JTextField txtRoomNumber;
	private JTextField txtCheckInDate;
	private JTextField txtCheckOutDate;
	private JComboBox<String> cbRoomType;
	private JButton btnSubmit;
	private JTable bookingTable;
	private DefaultTableModel tableModel;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public BookingView() {
		initComponent();
	}

	private void initComponent() {
		Color bgLeft = new Color(245, 247, 250);
		Color bgRight = Color.WHITE;
		Color primaryColor = new Color(41, 128, 185);
		Color accentColor = new Color(39, 174, 96);
		Color textColor = new Color(44, 62, 80);
		Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
		Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(bgRight);

		// --- FORM NHAP ---
		JPanel pnlInput = new JPanel(new GridBagLayout());
		pnlInput.setBackground(bgLeft);
		pnlInput.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(218, 223, 230), 1), new EmptyBorder(25, 20, 25, 20)));

		pnlInput.setPreferredSize(new Dimension(360, 0));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 5, 10, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		// Tieu de
		JLabel lblTitle = new JLabel("THÔNG TIN ĐẶT PHÒNG");
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(primaryColor);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		pnlInput.add(lblTitle, gbc);

		Dimension fieldSize = new Dimension(200, 32);
		gbc.gridwidth = 1;

		// Ma khach hang
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.weightx = 0;
		pnlInput.add(createLabel("Mã khách hàng:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		txtCustomerId = createTextField(fieldSize, mainFont);
		pnlInput.add(txtCustomerId, gbc);

		// Loai phong
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0;
		pnlInput.add(createLabel("Loại phòng:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		cbRoomType = new JComboBox<>(new String[] { "STANDARD", "VIP" });
		cbRoomType.setPreferredSize(fieldSize);
		cbRoomType.setFont(mainFont);
		cbRoomType.setBackground(Color.WHITE);
		pnlInput.add(cbRoomType, gbc);

		// So phong
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.weightx = 0;
		pnlInput.add(createLabel("Số phòng:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		txtRoomNumber = createTextField(fieldSize, mainFont);
		pnlInput.add(txtRoomNumber, gbc);

		// Check-in
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.weightx = 0;
		pnlInput.add(createLabel("Ngày Check-in:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		txtCheckInDate = createTextField(fieldSize, mainFont);
		txtCheckInDate.setText(LocalDate.now().toString());
		pnlInput.add(txtCheckInDate, gbc);

		// Check-out
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.weightx = 0;
		pnlInput.add(createLabel("Ngày Check-out:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		txtCheckOutDate = createTextField(fieldSize, mainFont);
		txtCheckOutDate.setText(LocalDate.now().plusDays(1).toString());
		pnlInput.add(txtCheckOutDate, gbc);

		// Nut dat phong
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(25, 5, 5, 5);
		btnSubmit = new JButton("XÁC NHẬN ĐẶT PHÒNG");
		btnSubmit.setPreferredSize(new Dimension(0, 40));
		btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSubmit.setBackground(accentColor);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFocusPainted(false);
		btnSubmit.setBorderPainted(false);
		pnlInput.add(btnSubmit, gbc);

		add(pnlInput, BorderLayout.WEST);

		// --- BANG DANH SACH ---
		String[] columns = { "ID", "Mã KH", "Số Phòng", "Loại Phòng", "Ngày Đến", "Ngày Đi", "Trạng Thái" };
		tableModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		bookingTable = new JTable(tableModel);
		bookingTable.setFont(mainFont);
		bookingTable.setRowHeight(32);
		bookingTable.setGridColor(new Color(230, 233, 238));
		bookingTable.setSelectionBackground(new Color(232, 244, 252));

		// Custom Header bang du lieu
		JTableHeader header = bookingTable.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 38));

		JScrollPane scrollPane = new JScrollPane(bookingTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
		add(scrollPane, BorderLayout.CENTER);

		// Su kien nut bam
		btnSubmit.addActionListener(e -> onSubmitBooking());
	}

	private JLabel createLabel(String text, Font font, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(color);
		return label;
	}

	private JTextField createTextField(Dimension size, Font font) {
		JTextField field = new JTextField();
		field.setPreferredSize(size);
		field.setFont(font);
		field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
				BorderFactory.createEmptyBorder(2, 8, 2, 8)));
		return field;
	}

	public void onSubmitBooking() {
		try {
			String customerId = txtCustomerId.getText().trim();
			String roomNumber = txtRoomNumber.getText().trim();
			String roomType = (String) cbRoomType.getSelectedItem();
			LocalDate checkIn = LocalDate.parse(txtCheckInDate.getText().trim(), formatter);
			LocalDate checkOut = LocalDate.parse(txtCheckOutDate.getText().trim(), formatter);

			if (customerId.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (roomNumber.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số phòng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return;
			}

			Booking booking = null;
			if (bookingController != null) {
				booking = bookingController.handleBookingRequest(customerId, roomNumber, roomType, checkIn, checkOut);
			}

			int bookingId = (booking != null) ? booking.getBookingId() : tableModel.getRowCount() + 1;

			tableModel.addRow(new Object[] { bookingId, customerId, roomNumber, roomType, checkIn.toString(),
					checkOut.toString(), "PENDING" });

			// Reset form
			txtCustomerId.setText("");
			txtRoomNumber.setText("");
			txtCheckInDate.setText(LocalDate.now().toString());
			txtCheckOutDate.setText(LocalDate.now().plusDays(1).toString());

			JOptionPane.showMessageDialog(this,
					"Đặt phòng thành công cho khách " + customerId + "\nMã Hóa Đơn của bạn là: " + bookingId
							+ "\n(Vui lòng nhớ mã này để dùng khi thanh toán)",
					"Thành công", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ (YYYY-MM-DD)!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
}