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
		// yeu cau Service lay bao cao
        this.currentReport = reportService.getReport(reportType, startDate, endDate);
		// sinh du lieu bao cao
        if (this.currentReport != null) {
            this.currentReport.generate();
            System.out.println("Đã hoàn tất việc tạo báo cáo trên Controller.");
        }
	}

	// yeu cau xuat bao cao
	public void exportReport(String format) {
        if (this.currentReport != null) {
            File exportedFile = reportService.exportReport(this.currentReport, format);
            if (exportedFile != null) {
                System.out.println("File báo cáo đã lưu tại: " + exportedFile.getName());
            }
        } else {
            System.out.println("Không có báo cáo nào để xuất. Vui lòng tạo báo cáo trước!");
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
