import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainTestReport {
	public static void main(String[] args) {
		System.out.println("=== KHỞI TẠO HỆ THỐNG BÁO CÁO ===");

		// 1. khoi tao cac thanh phan theo mo hinh MVC / Layered
		ReportService reportService = new ReportService();
		ReportController reportController = new ReportController(reportService);
		ReportView reportView = new ReportView();
		reportView.setReportController(reportController);

		// thiet lap khoang thoi gian lam bao cao (Tu hom nay đen 7 ngay sau)
		Date startDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, 7);
		Date endDate = c.getTime();

		System.out.println("\n--- THỬ NGHIỆM 1: BÁO CÁO ĐƠN LẺ (SINGLE REPORT) ---");
		// controller yeu cau tao bao cao "Doanh Thu"
		reportController.generateReport("Doanh Thu Tháng 6", startDate, endDate);

		// view hien thi bao cao hien tai tu Controller
		reportView.displayReport(reportController.getCurrentReport());

		// Gia lap nguoi dung click nut Export PDF tren giao dien
		System.out.println("[User Click]: Nút Xuất PDF");
		reportView.onExportPDFClick();

		System.out.println("\n--- THỬ NGHIỆM 2: BÁO CÁO TỔNG HỢP (COMPOSITE PATTERN) ---");
		// Gia su can tong hop nhieu loai bao cao cung luc
		System.out.println("Đang yêu cầu hệ thống gom nhóm báo cáo...");
		CompositeReport compositeReport = reportService
				.getCompositeReport(Arrays.asList("Doanh Thu", "Kho Hàng", "Khách Hàng Mới"), startDate, endDate);

		// Them mot bao cao le phat sinh vao nhom tong hop (Kiem tra ham add)
		Report extraReport = reportService.getReport("Chi Phí Vận Hành", startDate, endDate);
		compositeReport.add(extraReport);

		// Chay ham generate() chung cho ca bo bao cao
		compositeReport.generate();

		// Xuat file cho toan bo bao cao tong hop nay duoi dang Excel (.xlsx)
		reportService.exportReport(compositeReport, "xlsx");

		System.out.println("\n--- THỬ NGHIỆM 3: KIỂM TRA MÃ KHUYẾN MÃI (PROMOTION) ---");
		// Tao mot ma het han (Ngay hom qua)
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date expiredDate = c.getTime();
		Promotion promoHetHan = new Promotion("GIAMGIASOCK", 50.0, expiredDate);

		// Tao mot ma con han (10 ngay nua)
		c.setTime(new Date());
		c.add(Calendar.DATE, 10);
		Date futureDate = c.getTime();
		Promotion promoConHan = new Promotion("SUMMER2026", 15.5, futureDate);

		System.out.println("Mã " + promoHetHan.getPromoCode() + " có hợp lệ không? -> " + promoHetHan.isValid());
		System.out.println("Mã " + promoConHan.getPromoCode() + " có hợp lệ không? -> " + promoConHan.isValid());

		System.out.println("\n=== KẾT THÚC QUÁ TRÌNH KIỂM THỬ ===");
	}
}

