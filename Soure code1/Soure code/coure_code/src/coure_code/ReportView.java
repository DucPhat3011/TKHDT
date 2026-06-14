import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReportView extends JFrame {
	private ReportController reportController;
	
	public ReportView(ReportController reportController) {
        this.reportController = reportController;
    }

	// hien thi thong tin bao cao len giao dien
	public void displayReport(Report reportData) {
		JOptionPane.showMessageDialog( this, "Title: " + reportData.getTitle() 
										+ "\nType: " + reportData.getType()  
										+ "\nData: " 
										+ reportData.getData(), "Report",  
										JOptionPane.INFORMATION_MESSAGE);
	}

	// xu ly su kien khi nguoi dung nhan nut Export PDF
	public void onExportPDFClick() {
		if (reportController != null) {
            reportController.exportReport("pdf");
            JOptionPane.showMessageDialog(this, "Export PDF successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "No controller attached!");
        }
	}

	// lay controller dang su dung
	public ReportController getReportController() {
		return reportController;
	}

	// gan controller cho giao dien
	public void setReportController(ReportController reportController) {
		this.reportController = reportController;
	}
}
