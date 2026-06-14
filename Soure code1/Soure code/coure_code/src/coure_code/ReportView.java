import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReportView extends JFrame {
	private ReportController reportController;
	
	public ReportView(ReportController reportController) {
        this.reportController = reportController;
    }

	public void displayReport(Report reportData) {
		JOptionPane.showMessageDialog( this, "Title: " + reportData.getTitle() 
										+ "\nType: " + reportData.getType()  
										+ "\nData: " 
										+ reportData.getData(), "Report",  
										JOptionPane.INFORMATION_MESSAGE);
	}

	public void onExportPDFClick() {
		if (reportController != null) {
            reportController.exportReport("pdf");
            JOptionPane.showMessageDialog(this, "Export PDF successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "No controller attached!");
        }
	}

	public ReportController getReportController() {
		return reportController;
	}

	public void setReportController(ReportController reportController) {
		this.reportController = reportController;
	}
}
