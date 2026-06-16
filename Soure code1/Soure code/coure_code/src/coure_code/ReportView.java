import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ReportView extends JPanel {
	private ReportController reportController;
	
	private DefaultTableModel tableModel;
	private JComboBox<String> cbType;
	private JTable table;

	public void setReportController(ReportController rc) {
		this.reportController = rc;
	}

	public ReportView() {
		Color bgMain = new Color(245, 247, 250);
		Color cardBg = Color.WHITE;
		Color primaryColor = new Color(41, 128, 185);
		Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
		Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

		setLayout(new BorderLayout(15, 15));
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(bgMain);

		JPanel pnlInput = new JPanel(new GridBagLayout());
		pnlInput.setBackground(cardBg);
		pnlInput.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(218, 223, 230), 1), new EmptyBorder(15, 15, 15, 15)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Label & Combobox
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblType = new JLabel("Loại báo cáo:");
		lblType.setFont(mainFont);
		pnlInput.add(lblType, gbc);

		cbType = new JComboBox<>(new String[] { "Doanh Thu", "Khách Hàng" });
		cbType.setPreferredSize(new Dimension(200, 30));
		gbc.gridx = 1;
		pnlInput.add(cbType, gbc);

		// Nut
		JButton btnGenerate = new JButton("TẠO BÁO CÁO");
		btnGenerate.setFont(boldFont);
		btnGenerate.setBackground(primaryColor);
		btnGenerate.setForeground(Color.WHITE);
		btnGenerate.setFocusPainted(false);
		gbc.gridx = 2;
		gbc.gridy = 0;
		pnlInput.add(btnGenerate, gbc);

		add(pnlInput, BorderLayout.NORTH);

		// --- BANG BAO CAO ---
		String[] cols = { "Mã BC", "Loại Báo Cáo", "Ngày Tạo", "Nội Dung" };
		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(3).setPreferredWidth(800);
		table.getColumnModel().getColumn(3).setPreferredWidth(800);
		table.setFont(mainFont);
		table.setRowHeight(30);

		JTableHeader header = table.getTableHeader();
		header.setFont(boldFont);
		header.setBackground(new Color(52, 73, 94));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 35));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(218, 223, 230), 1));
		add(scrollPane, BorderLayout.CENTER);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				int row = table.getSelectedRow();

				if (row >= 0) {

					String content = tableModel.getValueAt(row, 3).toString();

					JTextArea txt = new JTextArea(content);
					txt.setEditable(false);
					txt.setLineWrap(true);
					txt.setWrapStyleWord(true);
					txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));

					JScrollPane sp = new JScrollPane(txt);
					sp.setPreferredSize(new Dimension(600, 300));

					JOptionPane.showMessageDialog(ReportView.this, sp, "Chi Tiết Báo Cáo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		// Sự kiện
		btnGenerate.addActionListener(e -> {
			if (reportController != null)
				reportController.handleGenerateReport(cbType.getSelectedItem().toString(), new java.util.Date(),
						new java.util.Date());
		});
	}

	// Hien thi bao cao vao bang
	public void displayReportList(IReport report) {
		if (report instanceof Report) {
			Report r = (Report) report;
			String content = r.getData();
			tableModel.addRow(new Object[] {
					"BC" + String.format("%03d", r.getReportId()),
					r.getType(),
					new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(r.getGenerateDate()),
					content });
		}
	}

	// Hien thi thong bao len giao dien
	public void showMessage(String msg, String title, int type) {
		JOptionPane.showMessageDialog(this, msg, title, type);
	}
}