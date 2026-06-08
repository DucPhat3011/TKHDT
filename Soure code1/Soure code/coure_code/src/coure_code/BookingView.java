import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingView extends JFrame {

    private BookingController bookingController;

    private JTextField txtCustomerId;
    private JTextField txtCheckInDate;
    private JTextField txtCheckOutDate;
    private JComboBox<String> cbRoomType;
    private JButton btnSubmit;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BookingView(BookingController bookingController) {
        this.bookingController = bookingController;
        initComponent();
    }

    private void initComponent() {
        setTitle("He Thong Đat Phong Khach San");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Form nhap =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 10, 4, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Ma khach hang
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JLabel("Ma khach hang:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCustomerId = new JTextField();
        formPanel.add(txtCustomerId, gbc);

        // Loai phong
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formPanel.add(new JLabel("Loai phong:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        cbRoomType = new JComboBox<>(new String[]{"STANDARD", "VIP"});
        formPanel.add(cbRoomType, gbc);

        // Check-in
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formPanel.add(new JLabel("Ngay Check-in (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCheckInDate = new JTextField(LocalDate.now().toString());
        formPanel.add(txtCheckInDate, gbc);

        // Check-out
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        formPanel.add(new JLabel("Ngay Check-out (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCheckOutDate = new JTextField(LocalDate.now().plusDays(1).toString());
        formPanel.add(txtCheckOutDate, gbc);

        add(formPanel, BorderLayout.NORTH);

        // ===== Bang danh sach booking =====
        String[] columns = {"ID Booking", "Ma KH", "Loai Phong", "Check-in", "Check-out", "Trang Thai"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(22);
        bookingTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        // ===== Nut bam =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        btnSubmit = new JButton("Đặt Phòng");
        btnSubmit.setPreferredSize(new Dimension(120, 28));
        bottomPanel.add(btnSubmit);
        add(bottomPanel, BorderLayout.SOUTH);

        btnSubmit.addActionListener(e -> onSubmitBooking());
    }

    public void render() {
        setVisible(true);
    }

    public void onSubmitBooking() {
        try {
            String customerId = txtCustomerId.getText().trim();
            String roomType = (String) cbRoomType.getSelectedItem();
            LocalDate checkIn = LocalDate.parse(txtCheckInDate.getText().trim(), formatter);
            LocalDate checkOut = LocalDate.parse(txtCheckOutDate.getText().trim(), formatter);

            if (customerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap ma khach hang!", "Canh bao", JOptionPane.WARNING_MESSAGE);
                return;
            }

            bookingController.handleBookingRequest(customerId, roomType, checkIn, checkOut);

            // Them dong moi vao bang
            tableModel.addRow(new Object[]{
                tableModel.getRowCount() + 1,
                customerId,
                roomType,
                checkIn.toString(),
                checkOut.toString(),
                "PENDING"
            });

            // Reset form sau khi dat
            txtCustomerId.setText("");
            txtCheckInDate.setText(LocalDate.now().toString());
            txtCheckOutDate.setText(LocalDate.now().plusDays(1).toString());

            showSuccessMessage("Dat phong thanh cong cho khach " + customerId);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Dinh dang khong hop le (dung: YYYY-MM-DD)!", "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thong bao", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}