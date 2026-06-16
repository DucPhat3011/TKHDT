import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PaymentView extends JPanel {
	private PaymentController paymentController;

	private JTextField txtInvoiceId;
	private JTextField txtRoomNumber;
	private JTextField txtRoomFee;
	private JTextField txtServiceFee;
	private JTextField txtDiscount;
	private JTextField txtTax;
	private JTextField txtTotal;
	private JLabel lblPromoMessage;

	private JComboBox<String> cboMethod;
	private DefaultTableModel model;

	public void setPaymentController(PaymentController paymentController) {
		this.paymentController = paymentController;
	}

	public PaymentView() {
		Color bgMain = new Color(240, 242, 245);
		Color cardBg = Color.WHITE;
		Color primaryColor = new Color(41, 128, 185);
		Color accentColor = new Color(39, 174, 96);
		Color textColor = new Color(44, 62, 80);
		Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
		Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(bgMain);

		JPanel pnlInvoiceCard = new JPanel(new GridBagLayout());
		pnlInvoiceCard.setBackground(cardBg);
		pnlInvoiceCard.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(218, 223, 230), 1),
				new EmptyBorder(25, 25, 25, 25)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 15, 10, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblHeader = new JLabel("CHI TIẾT THANH TOÁN");
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblHeader.setForeground(primaryColor);
		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
		gbc.insets = new Insets(0, 15, 20, 15);
		pnlInvoiceCard.add(lblHeader, gbc);

		gbc.gridwidth = 1;
		gbc.insets = new Insets(8, 15, 8, 15);
		Dimension fieldSize = new Dimension(180, 32);

		// Hang 1: Ma hoa don & So phong
		gbc.gridy = 1; gbc.gridx = 0;
		pnlInvoiceCard.add(createStyledLabel("Mã Hóa Đơn:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		txtInvoiceId = createStyledField(fieldSize, mainFont);
		pnlInvoiceCard.add(txtInvoiceId, gbc);
		gbc.gridx = 2;
		pnlInvoiceCard.add(createStyledLabel("Số Phòng:", mainFont, textColor), gbc);
		gbc.gridx = 3;
		txtRoomNumber = createStyledField(fieldSize, mainFont);
		pnlInvoiceCard.add(txtRoomNumber, gbc);

		// Hang 2: Tien phong & Tien dich vu
		gbc.gridy = 2; gbc.gridx = 0;
		pnlInvoiceCard.add(createStyledLabel("Tiền Phòng (VND):", mainFont, textColor), gbc);
		gbc.gridx = 1;
		txtRoomFee = createStyledField(fieldSize, mainFont);
		txtRoomFee.setEditable(false);
		txtRoomFee.setBackground(new Color(248, 248, 248));
		pnlInvoiceCard.add(txtRoomFee, gbc);
		gbc.gridx = 2;
		pnlInvoiceCard.add(createStyledLabel("Tiền Dịch Vụ (VND):", mainFont, textColor), gbc);
		gbc.gridx = 3;
		txtServiceFee = createStyledField(fieldSize, mainFont);
		txtServiceFee.setText("0");
		txtServiceFee.setEditable(false);
		txtServiceFee.setBackground(new Color(248, 248, 248));
		pnlInvoiceCard.add(txtServiceFee, gbc);

		// Hang 3: Giam gia & Thue VAT
		gbc.gridy = 3; gbc.gridx = 0;
		pnlInvoiceCard.add(createStyledLabel("Giảm Giá (VND):", mainFont, textColor), gbc);
		gbc.gridx = 1;
		txtDiscount = createStyledField(fieldSize, mainFont);
		txtDiscount.setText("0");
		txtDiscount.setEditable(false);
		txtDiscount.setBackground(new Color(248, 248, 248));
		pnlInvoiceCard.add(txtDiscount, gbc);
		gbc.gridx = 2;
		pnlInvoiceCard.add(createStyledLabel("Thuế VAT (10%):", mainFont, textColor), gbc);
		gbc.gridx = 3;
		txtTax = createStyledField(fieldSize, mainFont);
		txtTax.setText("0");
		txtTax.setEditable(false);
		txtTax.setBackground(new Color(248, 248, 248));
		pnlInvoiceCard.add(txtTax, gbc);

		// Hang 4: Thong bao khuyen mai
		gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 4;
		lblPromoMessage = new JLabel(" ");
		lblPromoMessage.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		lblPromoMessage.setForeground(new Color(41, 128, 185));
		pnlInvoiceCard.add(lblPromoMessage, gbc);

		// Hang 5: Phuong thuc & Tong thanh toan
		gbc.gridy = 5; gbc.gridwidth = 1; gbc.gridx = 0;
		pnlInvoiceCard.add(createStyledLabel("Phương Thức:", mainFont, textColor), gbc);
		gbc.gridx = 1;
		cboMethod = new JComboBox<>(new String[]{"CASH", "CREDIT_CARD", "BANK_TRANSFER"});
		cboMethod.setPreferredSize(fieldSize);
		cboMethod.setFont(mainFont);
		cboMethod.setBackground(Color.WHITE);
		pnlInvoiceCard.add(cboMethod, gbc);
		gbc.gridx = 2;
		pnlInvoiceCard.add(createStyledLabel("TỔNG THANH TOÁN:", boldFont, primaryColor), gbc);
		gbc.gridx = 3;
		txtTotal = createStyledField(fieldSize, new Font("Segoe UI", Font.BOLD, 15));
		txtTotal.setEditable(false);
		txtTotal.setBackground(new Color(235, 245, 251));
		txtTotal.setForeground(Color.RED);
		pnlInvoiceCard.add(txtTotal, gbc);

		add(pnlInvoiceCard, BorderLayout.NORTH);

		// Lich su giao dich
		String[] columns = {"Mã Hóa Đơn", "Tiền Phòng", "Tiền Dịch Vụ", "Tổng Thanh Toán", "Trạng Thái"};
		model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);
		table.setFont(mainFont);
		table.setRowHeight(32);
		table.setGridColor(new Color(230, 233, 238));
		table.setSelectionBackground(new Color(232, 244, 252));
		JTableHeader header = table.getTableHeader();
		header.setFont(boldFont);
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 38));
		add(new JScrollPane(table), BorderLayout.CENTER);

		// Nut
		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		pnlButtons.setBackground(bgMain);
		JButton btnViewInvoice = createStyledButton("XEM CHI TIẾT HÓA ĐƠN", new Color(142, 68, 173));
		JButton btnConfirm = createStyledButton("XÁC NHẬN THANH TOÁN", accentColor);
		pnlButtons.add(btnViewInvoice);
		pnlButtons.add(btnConfirm);
		add(pnlButtons, BorderLayout.SOUTH);

		btnViewInvoice.addActionListener(e -> {
			if (paymentController != null)
				paymentController.handleViewInvoice(txtInvoiceId.getText(), txtRoomNumber.getText(),
						txtRoomFee.getText(), txtServiceFee.getText(),
						txtDiscount.getText(), txtTax.getText(), txtTotal.getText());
		});

		btnConfirm.addActionListener(e -> {
			if (paymentController != null) {
				paymentController.handleConfirmPayment(txtInvoiceId.getText(), txtRoomNumber.getText(),
						txtRoomFee.getText(), txtServiceFee.getText(), txtDiscount.getText(), txtTax.getText(),
						txtTotal.getText(), cboMethod.getSelectedItem().toString());
			}
		});

		// Nhap Ma Hoa Don -> tu dong dien so phong + tinh tien
		txtInvoiceId.getDocument().addDocumentListener(new DocumentListener() {
			private void trigger() {
				if (paymentController != null)
					paymentController.handleInvoiceIdChanged(txtInvoiceId.getText().trim());
			}

			public void insertUpdate(DocumentEvent e) {
				trigger();
			}

			public void removeUpdate(DocumentEvent e) {
				trigger();
			}

			public void changedUpdate(DocumentEvent e) {
				trigger();
			}
		});

		// Nhap So Phong tay -> tinh tien
		txtRoomNumber.getDocument().addDocumentListener(new DocumentListener() {
			private void trigger() {
				if (paymentController != null)
					paymentController.handleRoomNumberChanged(txtRoomNumber.getText().trim(),
							txtServiceFee.getText().trim());
			}
			public void insertUpdate(DocumentEvent e) { 
				trigger(); 
			}
			public void removeUpdate(DocumentEvent e) { 
				trigger(); 
			}
			public void changedUpdate(DocumentEvent e) { 
				trigger(); 
			}
		});
	}

	// Tao nhan voi font va mau sac tuy chinh
	private JLabel createStyledLabel(String text, Font font, Color color) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(color);
		return label;
	}

	// Tao o nhap lieu voi kich thuoc va vien tuy chinh
	private JTextField createStyledField(Dimension size, Font font) {
		JTextField field = new JTextField();
		field.setPreferredSize(size);
		field.setFont(font);
		field.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
				new EmptyBorder(2, 8, 2, 8)));
		return field;
	}

	// Tao nut bam voi kieu dang tuy chinh
	private JButton createStyledButton(String text, Color bg) {
		JButton btn = new JButton(text);
		btn.setPreferredSize(new Dimension(220, 42));
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setBackground(bg);
		btn.setForeground(Color.WHITE);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		return btn;
	}

	// Cap nhat thong tin chi tiet thanh toan len giao dien
	public void setPaymentDetails(String roomFee, String tax, String discount, String total) {
		txtRoomFee.setText(roomFee);
		txtTax.setText(tax);
		txtDiscount.setText(discount);
		txtTotal.setText(total);
	}

	// Cap nhat tong tien thanh toan
	public void setTotalAmount(String total) { 
		txtTotal.setText(total); 
	}
	
	// Them dong hoa don moi vao bang
	public void addInvoiceToTable(Object[] rowData) { 
		model.addRow(rowData); 
	}
	
	// Thiet lap so phong
	public void setRoomNumber(String roomNumber) { 
		txtRoomNumber.setText(roomNumber); 
	}
	
	// Thiet lap phi dich vu
	public void setServiceFee(String fee) { 
		txtServiceFee.setText(fee); 
	}

	// Hien thi thong bao khuyen mai voi mau sac tuong ung
	public void setPromoMessage(String message) {
		lblPromoMessage.setText(message.isEmpty() ? " " : message);
		if (message.startsWith("✅")) {
			lblPromoMessage.setForeground(new Color(39, 174, 96));
		} else if (message.startsWith("⚠️")) {
			lblPromoMessage.setForeground(new Color(192, 57, 43));
		} else {
			lblPromoMessage.setForeground(new Color(41, 128, 185));
		}
	}

	// Hien thi hop thoai thong bao
	public void showMessage(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}

	// Hien thi ban xem truoc hoa don
	public void showInvoicePreview(String invoiceContent) {
		JTextArea area = new JTextArea(invoiceContent);
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		area.setMargin(new Insets(10, 15, 10, 15));
		area.setBackground(new Color(252, 252, 252));
		JScrollPane scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(430, 360));
		JOptionPane.showMessageDialog(this, scroll, "Chi Tiết Hóa Đơn", JOptionPane.INFORMATION_MESSAGE);
	}
}