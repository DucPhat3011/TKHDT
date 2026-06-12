package coure_code;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class ServiceManagementView extends JPanel {
    private ServiceController controller;

    private JTextField txtServiceId;
    private JTextField txtServiceName;
    private JTextField txtUnitPrice;
    private DefaultTableModel serviceTableModel;
    private JTable serviceTable;

    public ServiceManagementView() {
        initUI();
    }

    public void setController(ServiceController controller) {
        this.controller = controller;
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 1, 1));
        panelInput.setBorder(BorderFactory.createTitledBorder(" Thong tin Dich Vu "));
        panelInput.setPreferredSize(new Dimension(400, 0));

        txtServiceId = new JTextField();
        txtServiceName = new JTextField();
        txtUnitPrice = new JTextField();

        panelInput.add(new JLabel("Ma ID:"));
        panelInput.add(txtServiceId);
        panelInput.add(new JLabel("Ten Dich Vu:"));
        panelInput.add(txtServiceName);
        panelInput.add(new JLabel("Don Gia (VND):"));
        panelInput.add(txtUnitPrice);

        JPanel panelBtns = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton btnAddService = new JButton("Them dich vu");
        JButton btnUpdatePrice = new JButton("Cap nhat gia");
        panelBtns.add(btnAddService);
        panelBtns.add(btnUpdatePrice);
        
        panelInput.add(new JLabel("Thao tac:"));
        panelInput.add(panelBtns);

        String[] columns = {"Ma dich vu", "Ten dich vu", "Don Gia"};
        serviceTableModel = new DefaultTableModel(columns, 0);
        serviceTable = new JTable(serviceTableModel);

        add(panelInput, BorderLayout.WEST);
        add(new JScrollPane(serviceTable), BorderLayout.CENTER);

        btnAddService.addActionListener(e -> {
            if (controller != null) {
                controller.handleAddService(txtServiceId.getText(), txtServiceName.getText(), txtUnitPrice.getText());
            }
        });

        btnUpdatePrice.addActionListener(e -> {
            if (controller != null) {
                controller.handleUpdateService(txtServiceId.getText(), txtUnitPrice.getText());
            }
        });
    }

    public void displayServices(List<Services> services) {
        serviceTableModel.setRowCount(0);
        for (Services s : services) {
            serviceTableModel.addRow(new Object[]{
                s.getServiceId(), 
                s.getServiceName(), 
                String.format("%,.0f", s.getUnitPrice()) + " VND"
            });
        }
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}
