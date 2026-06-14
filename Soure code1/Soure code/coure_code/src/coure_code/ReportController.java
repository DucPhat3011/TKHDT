import java.io.File;
import java.util.Date;

public class ReportController {
	private ReportService reportService;
	private IReport currentReport;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	// yeu cau tao bao cao
	public void generateReport(String reportType, Date startDate, Date endDate) {
		currentReport = reportService.getReport(reportType, startDate, endDate);
	}

	// yeu cau xuat bao cao
	public void exportReport(String format) {
		if (currentReport != null) {
            File file = reportService.exportReport(currentReport, format);
            System.out.println("Exported file: " + file.getAbsolutePath());
        } else {
            System.out.println("No report to export.");
        }
	}
	
	public IReport getCurrentReport() {
        return currentReport;
    }
	
	public ReportService getReportService() {
		return reportService;
	}
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
