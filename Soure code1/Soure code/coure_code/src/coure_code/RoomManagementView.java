
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
public class RoomManagementView extends JPanel {
    private RoomController roomController;
    private DefaultTableModel tableModel;

    private JTextField txtRoomNumber;
    private JTextField txtFloor;
    private JTextField txtDescription;
    private JComboBox<String> cbType;

    public void setRoomController(RoomController roomController) {
        this.roomController = roomController;
    }

    public RoomManagementView() {
        Color bgLeft = new Color(245, 247, 250);
        Color bgRight = Color.WHITE;
        Color primaryColor = new Color(41, 128, 185);
        Color textColor = new Color(44, 62, 80);
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(bgRight);

        // --- KHUNG NHAP THONG TIN ---
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBackground(bgLeft);
        pnlInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(218, 223, 230), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        pnlInput.setPreferredSize(new Dimension(360, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Tieu de
        JLabel lblTitle = new JLabel("THÔNG TIN PHÒNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        pnlInput.add(lblTitle, gbc);

        Dimension fieldSize = new Dimension(200, 32);
        gbc.gridwidth = 1;

        // So phong
        gbc.gridy = 1; 
        gbc.gridx = 0; 
        gbc.weightx = 0;
        JLabel lblRoomNumber = new JLabel("Số phòng:");
        lblRoomNumber.setFont(mainFont);
        lblRoomNumber.setForeground(textColor);
        pnlInput.add(lblRoomNumber, gbc);
        
        gbc.gridx = 1; 
        gbc.weightx = 1;
        txtRoomNumber = new JTextField();
        txtRoomNumber.setPreferredSize(fieldSize);
        txtRoomNumber.setFont(mainFont);
        pnlInput.add(txtRoomNumber, gbc);

        // Loai phong
        gbc.gridy = 2; 
        gbc.gridx = 0; 
        gbc.weightx = 0;
        JLabel lblType = new JLabel("Loại phòng:");
        lblType.setFont(mainFont);
        lblType.setForeground(textColor);
        pnlInput.add(lblType, gbc);
        
        gbc.gridx = 1; 
        gbc.weightx = 1;
        cbType = new JComboBox<>(new String[] { "STANDARD", "VIP" });
        cbType.setPreferredSize(fieldSize);
        cbType.setFont(mainFont);
        cbType.setBackground(Color.WHITE);
        pnlInput.add(cbType, gbc);

        // So tang
        gbc.gridy = 3; 
        gbc.gridx = 0; 
        gbc.weightx = 0;
        JLabel lblFloor = new JLabel("Số tầng:");
        lblFloor.setFont(mainFont);
        lblFloor.setForeground(textColor);
        pnlInput.add(lblFloor, gbc);
        
        gbc.gridx = 1; 
        gbc.weightx = 1;
        txtFloor = new JTextField();
        txtFloor.setPreferredSize(fieldSize);
        txtFloor.setFont(mainFont);
        pnlInput.add(txtFloor, gbc);

        // Mo ta
        gbc.gridy = 4; 
        gbc.gridx = 0; 
        gbc.weightx = 0;
        JLabel lblDesc = new JLabel("Mô tả:");
        lblDesc.setFont(mainFont);
        lblDesc.setForeground(textColor);
        pnlInput.add(lblDesc, gbc);
        
        gbc.gridx = 1; 
        gbc.weightx = 1;
        txtDescription = new JTextField();
        txtDescription.setPreferredSize(fieldSize);
        txtDescription.setFont(mainFont);
        pnlInput.add(txtDescription, gbc);

        // Nut them phong
        gbc.gridy = 5; 
        gbc.gridx = 0; 
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; 
        gbc.insets = new Insets(20, 5, 5, 5);
        JButton btnAdd = new JButton("Thêm Phòng Mới");
        btnAdd.setPreferredSize(new Dimension(0, 38));
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAdd.setBackground(primaryColor);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        pnlInput.add(btnAdd, gbc);

        add(pnlInput, BorderLayout.WEST);

        // --- BANG DU LIEU ---
        tableModel = new DefaultTableModel(
                new String[] { "ID Phòng", "Số Phòng", "Loại", "Tầng", "Mô Tả", "Trạng Thái", "Giá/Đêm" }, 0);
        JTable roomTable = new JTable(tableModel);
        roomTable.setFont(mainFont);
        roomTable.setRowHeight(30); 
        roomTable.setGridColor(new Color(230, 233, 238));
        roomTable.setSelectionBackground(new Color(232, 244, 252));
        roomTable.setSelectionForeground(textColor);

        JTableHeader header = roomTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
        add(scrollPane, BorderLayout.CENTER);

        // Xu ly su kien
        btnAdd.addActionListener(e -> {
            if (roomController != null) {
                roomController.handleAddRoom(
                    txtRoomNumber.getText(),
                    cbType.getSelectedItem().toString(),
                    txtFloor.getText(),
                    txtDescription.getText()
                );
            }
        });
    }

    public void clearInputFields() {
        txtRoomNumber.setText("");
        txtFloor.setText("");
        txtDescription.setText("");
    }

    public void displayRoomList(List<Room> rooms) {
        tableModel.setRowCount(0);
        for (Room r : rooms) {
            tableModel.addRow(new Object[] { 
                r.getRoomId(), 
                r.getRoomNumber(), 
                r.getType().getTypeName(), 
                r.getFloor(),
                r.getDescription(), 
                r.getStatus().name(), 
                String.format("%,.0f VND", r.calculatePrice()) 
            });
        }
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}