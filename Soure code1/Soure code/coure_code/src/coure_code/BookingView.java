import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingView extends JPanel {

	private BookingController bookingController;

	private JTextField txtCustomerId;
	private JTextField txtRoomNumber;
	private JTextField txtCheckInDate;
	private JTextField txtCheckOutDate;
	private JComboBox<String> cbRoomType;
	private JButton btnSubmit;
	private JButton btnLookup;
	private JTextField txtLookupId;
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
		Color lookupColor = new Color(142, 68, 173);
		Color textColor = new Color(44, 62, 80);
		Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
		Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(bgRight);

		// ===== PANEL TRAI: FORM DAT PHONG + TRA CUU =====
		JPanel pnlLeft = new JPanel(new BorderLayout(0, 15));
		pnlLeft.setBackground(bgRight);
		pnlLeft.setPreferredSize(new Dimension(380, 0));

		// --- FORM DAT PHONG ---
		JPanel pnlInput = new JPanel(new GridBagLayout());
		pnlInput.setBackground(bgLeft);
		pnlInput.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(218, 223, 230), 1), new EmptyBorder(20, 20, 20, 20)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 5, 8, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;

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
		gbc.insets = new Insets(20, 5, 5, 5);
		btnSubmit = new JButton("XÁC NHẬN ĐẶT PHÒNG");
		btnSubmit.setPreferredSize(new Dimension(0, 40));
		btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSubmit.setBackground(accentColor);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFocusPainted(false);
		btnSubmit.setBorderPainted(false);
		pnlInput.add(btnSubmit, gbc);

		// --- PANEL TRA CUU HOA DON ---
		JPanel pnlLookup = new JPanel(new GridBagLayout());
		pnlLookup.setBackground(new Color(250, 245, 255));
		pnlLookup.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(lookupColor, 1),
				new EmptyBorder(15, 20, 15, 20)));

		GridBagConstraints gbcL = new GridBagConstraints();
		gbcL.insets = new Insets(6, 5, 6, 5);
		gbcL.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblLookupTitle = new JLabel("TRA CỨU HÓA ĐƠN");
		lblLookupTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLookupTitle.setForeground(lookupColor);
		gbcL.gridx = 0;
		gbcL.gridy = 0;
		gbcL.gridwidth = 2;
		pnlLookup.add(lblLookupTitle, gbcL);

		gbcL.gridy = 1;
		gbcL.gridx = 0;
		gbcL.gridwidth = 1;
		gbcL.weightx = 0;
		pnlLookup.add(createLabel("Mã hóa đơn:", mainFont, textColor), gbcL);
		gbcL.gridx = 1;
		gbcL.weightx = 1.0;
		txtLookupId = createTextField(new Dimension(140, 32), mainFont);
		txtLookupId.setToolTipText("Nhập mã HD001, HD002... hoac so 1, 2...");
		pnlLookup.add(txtLookupId, gbcL);

		gbcL.gridy = 2;
		gbcL.gridx = 0;
		gbcL.gridwidth = 2;
		gbcL.insets = new Insets(10, 5, 5, 5);
		btnLookup = new JButton("XEM CHI TIẾT HÓA ĐƠN");
		btnLookup.setPreferredSize(new Dimension(0, 36));
		btnLookup.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnLookup.setBackground(lookupColor);
		btnLookup.setForeground(Color.WHITE);
		btnLookup.setFocusPainted(false);
		btnLookup.setBorderPainted(false);
		pnlLookup.add(btnLookup, gbcL);

		pnlLeft.add(pnlInput, BorderLayout.CENTER);
		pnlLeft.add(pnlLookup, BorderLayout.SOUTH);
		add(pnlLeft, BorderLayout.WEST);

		// --- BANG DANH SACH DAT PHONG ---
		String[] columns = { "Mã HD", "Mã KH", "Số Phòng", "Loại Phòng", "Ngày Đén", "Ngày Đi", "Trạng Thái" };
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

		JTableHeader header = bookingTable.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 38));

		JScrollPane scrollPane = new JScrollPane(bookingTable);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
		add(scrollPane, BorderLayout.CENTER);

		btnSubmit.addActionListener(e -> onSubmitBooking());
		btnLookup.addActionListener(e -> onLookupInvoice());
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
			// Kiem tra check-in khong duoc la ngay qua khu
			if (checkIn.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(this, "Ngày check-in không được là ngày quá khứ!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!checkOut.isAfter(checkIn)) {
				JOptionPane.showMessageDialog(this, "Ngày check-out phải sau ngày check-in!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// Kiem tra so dem toi da
			long nights = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
			if (nights > 30) {
				JOptionPane.showMessageDialog(this, "Số đêm lưu trú không được vượt quá 30 đêm!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// Kiem tra dat phong truoc toi da 365 ngay
			if (checkIn.isAfter(LocalDate.now().plusDays(365))) {
				JOptionPane.showMessageDialog(this, "Chỉ được đặt phòng trước tối đa 365 ngày!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			Booking booking = null;
			if (bookingController != null) {
				booking = bookingController.handleBookingRequest(customerId, roomNumber, roomType, checkIn, checkOut);
			}

			int bookingId = (booking != null) ? booking.getBookingId() : tableModel.getRowCount() + 1;
			String invoiceCode = String.format("HD%03d", bookingId);

			tableModel.addRow(new Object[] { invoiceCode, customerId, roomNumber, roomType, checkIn.toString(),
					checkOut.toString(), "PENDING" });

			// Reset form
			txtCustomerId.setText("");
			txtRoomNumber.setText("");
			txtCheckInDate.setText(LocalDate.now().toString());
			txtCheckOutDate.setText(LocalDate.now().plusDays(1).toString());

			JTextArea msgArea = new JTextArea("Đặt phòng thành công!\n" + "Mã hóa đơn của bạn: " + invoiceCode + "\n"
					+ "Lưu lại mã này để dùng khi:\n" + "  - Thanh toán tại tab 'Thanh Toán Hóa Đơn'\n"
					+ "  - Tra cứu lại thông tin tại ô 'Tra Cứu Hóa Đơn'");
			msgArea.setEditable(false);
			msgArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			msgArea.setBackground(new Color(240, 255, 240));
			msgArea.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
			JOptionPane.showMessageDialog(this, msgArea, "Đặt Phòng Thành Công", JOptionPane.INFORMATION_MESSAGE);

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Đặt Phòng Thất Bại", JOptionPane.WARNING_MESSAGE);
		} catch (java.time.format.DateTimeParseException ex) {
			JOptionPane.showMessageDialog(this,
					"Vui lòng nhập ngày đúng định dạng:\n\nYYYY-MM-DD\n\nVi du:\n2026-06-15", "Ngày Không Hợp Lệ",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Có lỗi xảy ra:\n" + ex.getMessage(), "Lỗi Hệ Thống",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Tra cuu hoa don theo ma
	public void onLookupInvoice() {
		String input = txtLookupId.getText().trim();
		if (input.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String invoiceCode;
		if (input.toUpperCase().startsWith("HD")) {
			invoiceCode = input.toUpperCase();
		} else {
			try {
				int num = Integer.parseInt(input);
				invoiceCode = String.format("HD%03d", num);
			} catch (NumberFormatException e) {
				invoiceCode = input.toUpperCase();
			}
		}

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String rowCode = tableModel.getValueAt(i, 0).toString();
			if (rowCode.equalsIgnoreCase(invoiceCode)) {
				String maKH = tableModel.getValueAt(i, 1).toString();
				String soPhong = tableModel.getValueAt(i, 2).toString();
				String loaiPhong = tableModel.getValueAt(i, 3).toString();
				String ngayDen = tableModel.getValueAt(i, 4).toString();
				String ngayDi = tableModel.getValueAt(i, 5).toString();
				String trangThai = tableModel.getValueAt(i, 6).toString();

				JTextArea detail = new JTextArea("========================================\n"
						+ "           THÔNG TIN HÓA ĐƠN\n" + "========================================\n"
						+ String.format("Mã hóa đơn  : %s\n", invoiceCode) 
						+ String.format("Mã khách hàng: %s\n", maKH)
						+ String.format("Số phòng    : %s\n", soPhong) 
						+ String.format("Loại phòng  : %s\n", loaiPhong)
						+ String.format("Check-in    : %s\n", ngayDen) 
						+ String.format("Check-out   : %s\n", ngayDi)
						+ "----------------------------------------\n" 
						+ String.format("Trạng thái  : %s\n", trangThai)
						+ "========================================\n" 
						+ "Dùng mã " + invoiceCode + " tại tab\n"
						+ "   'Thanh Toán Hóa Đơn' để thanh toán.");
				detail.setEditable(false);
				detail.setFont(new Font("Monospaced", Font.PLAIN, 13));
				detail.setBackground(new Color(252, 252, 252));
				detail.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

				JScrollPane scroll = new JScrollPane(detail);
				scroll.setPreferredSize(new Dimension(400, 280));
				JOptionPane.showMessageDialog(this, scroll, "Chi Tiết Hóa Đơn - " + invoiceCode,
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this,
				"Không tìm thấy mã hóa đơn với mã: " + invoiceCode + "\nVui lòng kiểm tra lại mã hóa đơn.",
				"Không tìm thấy", JOptionPane.WARNING_MESSAGE);
	}

	public void setBookingController(BookingController bookingController) {
		this.bookingController = bookingController;
	}
}