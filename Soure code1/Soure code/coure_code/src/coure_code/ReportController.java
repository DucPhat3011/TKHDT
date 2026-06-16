import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ReportController {
	private ReportService reportService;
	private ReportView view;

	public ReportController(ReportService reportService, ReportView view) {
		this.reportService = reportService;
		this.view = view;
		
		this.view.setReportController(this);
	}

	// Xu ly tao bao cao theo loai va khoang thoi gian
	public void handleGenerateReport(String type, Date startDate, Date endDate) {
		try {
			if (type == null || type.trim().isEmpty()) {
				view.showMessage("Vui lòng chọn loại báo cáo!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				return;
			}
			IReport report = reportService.getReport(type, startDate, endDate);
			if (report != null) {
				report.generate();

				view.displayReportList(report);

				System.out.println("Đã tạo báo cáo: " + report.getTitle());
				view.showMessage("Đã tạo báo cáo: " + type + " thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			view.showMessage("Có lỗi xảy ra khi tạo báo cáo: " + e.getMessage(), "Lỗi hệ thống",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Xuat bao cao ra file theo dinh dang chon
	public void handleExport(IReport report, String format) {
		if (report == null) {
			view.showMessage("Vui lòng chọn báo cáo để xuất file!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return;
		}

		reportService.exportReport(report, format);
		view.showMessage("Đã xuất file " + format.toUpperCase() + " thành công!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
	}
}