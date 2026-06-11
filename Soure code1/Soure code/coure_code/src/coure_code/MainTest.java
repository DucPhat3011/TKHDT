package coure_code;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest extends JFrame {

    private List<User> userList = new ArrayList<>();
    private ServiceManager serviceManager = new ServiceManager();
    private StayRecord currentStayRecord;

    // --- CAC THANH PHAN GUI CAN TRUY CAP DA MAN HINH ---
    private DefaultTableModel userTableModel;
    private DefaultTableModel serviceTableModel;
    private DefaultTableModel usageTableModel;
    private JComboBox<String> cbChooseService;
    private JLabel lblTotalCost;

    public MainTest() {
        initMockData();

        setTitle("HE THONG QUAN LY KHACH SAN");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("HE THONG QUAN LY KHACH SAN", JLabel.CENTER);
        add(lblHeader, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Nhan su va Khach Hang", createScreen1());
        tabbedPane.addTab("Dich VU", createScreen2());
        tabbedPane.addTab("Thanh Toan", createScreen3());
        
        tabbedPane.addTab("4. Quản lý Buồng phòng", createScreen4());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void initMockData() {
        userList.add(new Admin(1, "admin", "123", "admin@hotel.com", "Hoang Duc Phat", "090", "HCM", "AD01", 15000000));
        userList.add(new Staff(2, "reception", "123", "staff@hotel.com", "Vay Anh Phuong", "091", "Dong Nai", "ST01", 8000000));
        userList.add(new Customer(3, "khach01", "123", "customer@gmail.com", "Nguyen Tran QUoc Phong", "092", "Da lat", "ID123", "VN"));

        serviceManager.addService(new Services(101, "Buffet Sang", 250000));
        serviceManager.addService(new Services(102, "Massage ", 500000));
        serviceManager.addService(new Services(103, "Giat ui", 60000));

        Customer vipCustomer = (Customer) userList.get(2);
        currentStayRecord = new StayRecord(777, null, "KH Vip");
    }

    private JPanel createScreen1() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelInput = new JPanel(new GridLayout(9, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder(" THong tin nguoi dung "));
        panelInput.setPreferredSize(new Dimension(500, 0));

        JTextField txtId = new JTextField();
        JTextField txtUsername = new JTextField();
        JTextField txtPassword = new JTextField();
        JTextField txtFullName = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtAddress = new JTextField();
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"Admin", "Staff", "Housekeeper", "Customer"});

        panelInput.add(new JLabel("ID:")); 
        panelInput.add(txtId);
        panelInput.add(new JLabel("Tai Khoan:")); 
        panelInput.add(txtUsername);
        panelInput.add(new JLabel("Mat Khau:")); 
        panelInput.add(txtPassword);
        panelInput.add(new JLabel("Name:")); 
        panelInput.add(txtFullName);
        panelInput.add(new JLabel("Email:")); 
        panelInput.add(txtEmail);
        panelInput.add(new JLabel("Phone:")); 
        panelInput.add(txtPhone);
        panelInput.add(new JLabel("Dia chi:")); 
        panelInput.add(txtAddress);
        panelInput.add(new JLabel("Chuc vu:")); 
        panelInput.add(cbRole);

        JPanel panelActionButtons = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnAdd = new JButton("ADD");
        JButton btnEdit = new JButton("FIX");
        JButton btnDelete = new JButton("DELET");
        panelActionButtons.add(btnAdd); panelActionButtons.add(btnEdit); panelActionButtons.add(btnDelete);
        panelInput.add(new JLabel("Thao tac:")); panelInput.add(panelActionButtons);

        String[] columns = {"ID", "Tai Khoan", "Ho Ten", "Email", "Chuc vu"};
        userTableModel = new DefaultTableModel(columns, 0);
        JTable userTable = new JTable(userTableModel);
        refreshUserTable();

        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String user = txtUsername.getText();
                String pass = txtPassword.getText();
                String name = txtFullName.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();
                String addr = txtAddress.getText();
                String role = (String) cbRole.getSelectedItem();

                User newUser;
                if (role.equals("Admin")) {
                    newUser = new Admin(id, user, pass, email, name, phone, addr, "AD" + id, 15000000);
                } else if (role.equals("Staff")) {
                    newUser = new Staff(id, user, pass, email, name, phone, addr, "ST" + id, 8000000);
                } else if (role.equals("Housekeeper")) {
                    newUser = new Housekeeper(id, user, pass, email, name, phone, addr, "HK" + id, 6000000, "Ca Sang");
                } else {
                    newUser = new Customer(id, user, pass, email, name, phone, addr, "CMND" + id, "Viet Nam");
                }

                userList.add(newUser);
                refreshUserTable();
                JOptionPane.showMessageDialog(this, "Da khoi tao doi tuong " + role + " thanh cong qua tinh Da Hinh!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin hop le!");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                userList.remove(selectedRow);
                refreshUserTable();
                JOptionPane.showMessageDialog(this, "Da xoa nguoi dung khoi danh sach.");
            } else {
                JOptionPane.showMessageDialog(this, "Vui long chon dong can xoa!");
            }
        });

        panel.add(panelInput, BorderLayout.WEST);
        panel.add(new JScrollPane(userTable), BorderLayout.CENTER);
        return panel;
    }

    private void refreshUserTable() {
        userTableModel.setRowCount(0); 
        
        for (User u : userList) {
            String roleStr = u.getClass().getSimpleName(); 
            
            userTableModel.addRow(new Object[]{
                u.getId(),
                u.getUsername(),
                u.getFullName(),
                u.getEmail(),
                roleStr
            });
        }
    }

    private JPanel createScreen2() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 1, 1));
        panelInput.setBorder(BorderFactory.createTitledBorder(" Dich Vu "));
        panelInput.setPreferredSize(new Dimension(400, 0));

        JTextField txtServiceId = new JTextField();
        JTextField txtServiceName = new JTextField();
        JTextField txtUnitPrice = new JTextField();

        panelInput.add(new JLabel("Ma ID:")); panelInput.add(txtServiceId);
        panelInput.add(new JLabel("Ten:")); panelInput.add(txtServiceName);
        panelInput.add(new JLabel("Don Gia):")); panelInput.add(txtUnitPrice);

        JPanel panelBtns = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton btnAddService = new JButton("Them dich vu");
        JButton btnUpdatePrice = new JButton("Cap nhat gia");
        panelBtns.add(btnAddService); panelBtns.add(btnUpdatePrice);
        panelInput.add(new JLabel("Thao tac:")); panelInput.add(panelBtns);

        String[] columns = {"Ma dich vu", "Ten dich vu", "Don Gia"};
        serviceTableModel = new DefaultTableModel(columns, 0);
        JTable serviceTable = new JTable(serviceTableModel);
        refreshServiceTable();

        btnAddService.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtServiceId.getText());
                String name = txtServiceName.getText();
                double price = Double.parseDouble(txtUnitPrice.getText());

                Services s = new Services(id, name, price);
                serviceManager.addService(s); 
                refreshServiceTable();
                updateServiceComboBox(); 
                JOptionPane.showMessageDialog(this, "Da them dich vu vao danh sach he thong.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Thong tin nhap khong hop le!");
            }
        });

        btnUpdatePrice.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtServiceId.getText());
                double newPrice = Double.parseDouble(txtUnitPrice.getText());

                Services targetService = null;
                for (Services s : serviceManager.getAllServices()) {
                    if (s.getServiceId() == id) {
                        targetService = s;
                        break;
                    }
                }

                if (targetService != null) {
                    targetService.setUnitPrice(newPrice); 
                    serviceManager.updateService(id, targetService); 
                    
                    refreshServiceTable(); 
                    JOptionPane.showMessageDialog(this, "Da cap nhat gia moi thanh cong.");
                } else {
                    JOptionPane.showMessageDialog(this, "Khong tim thay dich vu nao co ID: " + id);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Nhap Ma ID dich vu va Gia tien hop le de cap nhat!");
            }
        });

        panel.add(panelInput, BorderLayout.WEST);
        panel.add(new JScrollPane(serviceTable), BorderLayout.CENTER);
        return panel;
    }

    private void refreshServiceTable() {
        serviceTableModel.setRowCount(0);
        List<Services> list = serviceManager.getAllServices();
        for (Services s : list) {
            serviceTableModel.addRow(new Object[]{s.getServiceId(), s.getServiceName(), String.format("%,.0f", s.getUnitPrice()) + " VND"});
        }
    }

    private void updateServiceComboBox() {
        if (cbChooseService != null) {
            cbChooseService.removeAllItems();
            for (Services s : serviceManager.getAllServices()) {
                cbChooseService.addItem(s.getServiceId() + " - " + s.getServiceName());
            }
        }
    }

    private JPanel createScreen3() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelTopInfo = new JPanel(new GridLayout(2, 2, 10, 5));
        panelTopInfo.setBorder(BorderFactory.createTitledBorder(" Chi tiet thong tin luu tru "));
        panelTopInfo.add(new JLabel("<html><b>Khach hang luu tru:</b> Phan Thi Ngoc Nhu</html>"));
        panelTopInfo.add(new JLabel("<html><b>Phong dang thue:</b> Phong 302</html>"));
        panelTopInfo.add(new JLabel("<html><b>Gio nhan phong:</b> Gio thuc te luc check in</html>"));
        panelTopInfo.add(new JLabel("<html><b>Ma ho so luu tru:</b> SR-000777</html>"));

        String[] columns = {"STT ", "Ten dich vu da dung", "So luong", "Don gia", "Thanh tien dich vu"};
        usageTableModel = new DefaultTableModel(columns, 0);
        JTable usageTable = new JTable(usageTableModel);
        
        JPanel panelOrderService = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelOrderService.setBorder(BorderFactory.createTitledBorder(" Them dich vu cho phong "));
        
        cbChooseService = new JComboBox<>();
        updateServiceComboBox(); 
        JTextField txtQty = new JTextField("1", 5);
        JButton btnCallService = new JButton("Goi dich vu");
        
        panelOrderService.add(new JLabel("Chon dich vu:"));
        panelOrderService.add(cbChooseService);
        panelOrderService.add(new JLabel("So luong mua:"));
        panelOrderService.add(txtQty);
        panelOrderService.add(btnCallService);

        btnCallService.addActionListener(e -> {
            try {
                int selectedIndex = cbChooseService.getSelectedIndex();
                if (selectedIndex < 0) return;
                
                Services selectedService = serviceManager.getAllServices().get(selectedIndex);
                int quantity = Integer.parseInt(txtQty.getText());

                currentStayRecord.addService(selectedService, quantity);
                
                double cost = selectedService.getUnitPrice() * quantity;
                usageTableModel.addRow(new Object[]{
                    usageTableModel.getRowCount() + 1,
                    selectedService.getServiceName(),
                    quantity,
                    String.format("%,.0f", selectedService.getUnitPrice()) + " VND",
                    String.format("%,.0f", cost) + " VND"
                });
                
                lblTotalCost.setText("Tong chi phi dich vu hien tai: " + String.format("%,.0f", currentStayRecord.calculateTotalServiceCost()) + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "So luong khong hop le!");
            }
        });

        JPanel panelCheckout = new JPanel(new BorderLayout(5, 5));
        panelCheckout.setBorder(BorderFactory.createTitledBorder(" Khu vuc Tinh tien & Xuat hoa don "));

        JPanel panelStrategySelect = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JComboBox<String> cbStrategy = new JComboBox<>(new String[]{"Regular Price", "Holiday Price","VIPPrice"});
        JButton btnCalculate = new JButton("Thuc hien Tinh tien & In Bien lai");
        btnCalculate.setFont(new Font("Arial", Font.BOLD, 13));
        btnCalculate.setBackground(new Color(0, 153, 76));
        btnCalculate.setForeground(Color.WHITE);

        panelStrategySelect.add(new JLabel("<html><b>Chon gia phong hien tai:</b></html>"));
        panelStrategySelect.add(cbStrategy);
        panelStrategySelect.add(btnCalculate);
        

        lblTotalCost = new JLabel("Tong chi phi dich vu hien tai: 0 VND", JLabel.RIGHT);
        lblTotalCost.setFont(new Font("Arial", Font.BOLD, 15));
        lblTotalCost.setForeground(Color.RED);
        lblTotalCost.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

        panelCheckout.add(panelStrategySelect, BorderLayout.WEST);
        panelCheckout.add(lblTotalCost, BorderLayout.EAST);

        btnCalculate.addActionListener(e -> {
            String selectedStrategy = (String) cbStrategy.getSelectedItem();
            IPriceStrategy strategy;
            
            if (selectedStrategy.contains("Holiday")) {
                strategy = new HolidayPrice();
            } else if (selectedStrategy.contains("VIP")) {
                strategy = new VIPPrice();
            } else {
                strategy = new RegularPrice();
            }

            double roomBasePrice = 1200000;
            double calculatedRoomPrice = strategy.calculatePrice(roomBasePrice);
            double totalService = currentStayRecord.calculateTotalServiceCost();
            double grandTotal = calculatedRoomPrice + totalService;

            String receipt = "============== BIEN LAI THANH TOAN KHACH SAN ==============\n"
                           + " Ma so luu tru: SR-000777\n"
                           + " Khach hang: Phan THi Ngoc Nhu\n"
                           + "--------------------------------------------------------\n"
                           + " Tien phong goc: " + String.format("%,.0f", roomBasePrice) + " VND\n"
                           + " Loai Gia: " + selectedStrategy + "\n"
                           + " => Tien phong : " + String.format("%,.0f", calculatedRoomPrice) + " VND\n"
                           + " Tong chi phi dich vu phat sinh dung them: " + String.format("%,.0f", totalService) + " VND\n"
                           + "--------------------------------------------------------\n"
                           + " ==> TONG SO TIEN KHACH PHAI THANH TOAN: " + String.format("%,.0f", grandTotal) + " VND\n"
                           + "========================================================";

            JTextArea txtReceipt = new JTextArea(receipt);
            txtReceipt.setFont(new Font("Consolas", Font.PLAIN, 14));
            txtReceipt.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(txtReceipt), "He thong Ket xuat Hoa don (StayRecord context)", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel centerGrid = new JPanel(new BorderLayout(5, 5));
        centerGrid.add(panelOrderService, BorderLayout.NORTH);
        centerGrid.add(new JScrollPane(usageTable), BorderLayout.CENTER);

        panel.add(panelTopInfo, BorderLayout.NORTH);
        panel.add(centerGrid, BorderLayout.CENTER);
        panel.add(panelCheckout, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createScreen4() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Khu vực nhập liệu bên trái
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder(" Thao tác dọn phòng "));
        panelInput.setPreferredSize(new Dimension(350, 0));

        JTextField txtRoomId = new JTextField();
        // Giả lập Enum RoomStatus thành các chuỗi String để chọn trên giao diện
        JComboBox<String> cbRoomStatus = new JComboBox<>(new String[]{
            "AVAILABLE (Trống, sạch sẽ)", 
            "CLEANING (Đang dọn dẹp)", 
            "OCCUPIED (Đang có khách)", 
            "MAINTENANCE (Đang bảo trì)"
        });

        panelInput.add(new JLabel("Nhập ID Phòng cần dọn:")); 
        panelInput.add(txtRoomId);
        panelInput.add(new JLabel("Chuyển sang trạng thái:")); 
        panelInput.add(cbRoomStatus);

        JButton btnUpdateStatus = new JButton("Cập nhật Trạng thái");
        btnUpdateStatus.setBackground(new Color(51, 153, 255));
        btnUpdateStatus.setForeground(Color.WHITE);
        
        panelInput.add(new JLabel("")); // Ô trống để căn chỉnh layout
        panelInput.add(btnUpdateStatus);

        // 2. Khu vực Bảng hiển thị danh sách phòng bên phải
        String[] columns = {"ID Phòng", "Số Phòng", "Tình trạng hiện tại"};
        DefaultTableModel roomTableModel = new DefaultTableModel(columns, 0);
        JTable roomTable = new JTable(roomTableModel);
        
        // Thêm dữ liệu giả lập cho danh sách phòng
        roomTableModel.addRow(new Object[]{101, "P.101", "CLEANING (Đang dọn dẹp)"});
        roomTableModel.addRow(new Object[]{102, "P.102", "AVAILABLE (Trống, sạch sẽ)"});
        roomTableModel.addRow(new Object[]{201, "P.201", "OCCUPIED (Đang có khách)"});

        // 3. Sự kiện bấm nút "Cập nhật Trạng thái"
        btnUpdateStatus.addActionListener(e -> {
            try {
                int roomId = Integer.parseInt(txtRoomId.getText());
                String selectedStatus = (String) cbRoomStatus.getSelectedItem();

                // Tạo một đối tượng Housekeeper để gọi hàm nghiệp vụ
                // Sử dụng Constructor đầy đủ tham số như bạn vừa cung cấp
                Housekeeper hk = new Housekeeper(4, "hk01", "123", "hk@hotel.com", "Cô lao công", "0999", "HCM", "NV04", 5000000, "Ca Sáng");

                // Giả lập việc gọi hàm có tham số RoomStatus (Ở đây truyền String để tránh lỗi biên dịch nếu bạn chưa tạo enum RoomStatus)
                // hk.updateRoomStatus(roomId, RoomStatus.valueOf(selectedStatus)); 
                
                // In ra Console đúng như logic trong class Housekeeper của bạn
                System.out.println("Nhan vien don phong  " + roomId + " sang trang thai: " + selectedStatus);

                // Cập nhật lại giao diện Bảng (Tìm đúng ID phòng để đổi trạng thái)
                boolean found = false;
                for (int i = 0; i < roomTableModel.getRowCount(); i++) {
                    if ((int) roomTableModel.getValueAt(i, 0) == roomId) {
                        roomTableModel.setValueAt(selectedStatus, i, 2);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    JOptionPane.showMessageDialog(this, "Nhân viên Buồng phòng đã cập nhật Phòng " + roomId + "\nThành trạng thái: " + selectedStatus);
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy phòng có ID: " + roomId + " trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ID Phòng là một số nguyên!", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
            }
        });

        // 4. Gộp vào Panel chính
        panel.add(panelInput, BorderLayout.WEST);
        panel.add(new JScrollPane(roomTable), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainTest frame = new MainTest();
            frame.setVisible(true);
        });
    }
}