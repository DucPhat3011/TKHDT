import javax.swing.JFrame;

public class ReportView extends JFrame {
	private ReportController reportController;

	public void displayReport(Report reportData) {
	}

	public void onExportPDFClick() {
	}

	public ReportController getReportController() {
		return reportController;
	}

	public void setReportController(ReportController reportController) {
		this.reportController = reportController;
	}
}
