import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReportView extends JFrame {
	private ReportController reportController;
	
	public ReportView(ReportController reportController) {
        this.reportController = reportController;
    }

	// hien thi thong tin bao cao len giao dien
	public void displayReport(IReport reportData) {
        if (reportData != null) {
            System.out.println("--- GIAO DIỆN HIỂN THỊ ---");
            System.out.println("Tiêu đề: " + reportData.getTitle());
            System.out.println("--------------------------");
        }
	}

	// xu ly su kien khi nguoi dung nhan nut Export PDF
	public void onExportPDFClick() {
        if (reportController != null) {
            reportController.exportReport("pdf");
            // Hiển thị thông báo lên UI Swing
            JOptionPane.showMessageDialog(this, "Xuất file PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Controller chưa được khởi tạo!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
	}

	public ReportController getReportController() {
		return reportController;
	}
	
	public void setReportController(ReportController reportController) {
		this.reportController = reportController;
	}
}
