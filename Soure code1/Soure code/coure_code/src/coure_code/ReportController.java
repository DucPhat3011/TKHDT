import java.util.Date;

public class ReportController {
	private ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	public void generateReport(String reportType, Date startDate, Date endDate) {
	}

	public void exportReport(String format) {
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}