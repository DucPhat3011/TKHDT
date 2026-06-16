
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report implements IReport {
	private int reportId;
	private String title;
	private String type;
	private Date startDate;
	private Date endDate;
	private Date generateDate;
	private String data;

	private int totalInvoices;
	private double totalRevenue;
	private int totalCustomers;

	private static int staticTotalInvoices = 0;
	private static double staticTotalRevenue = 0;
	private static int staticTotalCustomers = 0;

	public Report(int reportId, String title, String type, Date startDate, Date endDate, Date generateDate,
			String data) {
		this.reportId = reportId;
		this.title = title;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.generateDate = generateDate;
		this.data = data;
		this.totalInvoices = staticTotalInvoices;
		this.totalRevenue = staticTotalRevenue;
		this.totalCustomers = staticTotalCustomers;
	}

	// Constructor mo rong: Cho phep truyen so lieu thuc tu ReportService
	public Report(int reportId, String title, String type, Date startDate, Date endDate, Date generateDate,
			String data, int totalInvoices, double totalRevenue, int totalCustomers) {
		this(reportId, title, type, startDate, endDate, generateDate, data);
		this.totalInvoices = totalInvoices;
		this.totalRevenue = totalRevenue;
		this.totalCustomers = totalCustomers;
	}

	public static void addRevenue(double amount) {
		staticTotalInvoices++;
		staticTotalRevenue += amount;
	}

	public static void addCustomer() {
		staticTotalCustomers++;
	}

	public static int getStaticTotalInvoices() {
		return staticTotalInvoices;
	}

	public static double getStaticTotalRevenue() {
		return staticTotalRevenue;
	}

	public static int getStaticTotalCustomers() {
		return staticTotalCustomers;
	}

	// --- Getters/Setters ---
	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	// Setter de ReportService co the truyen du lieu thuc
	public void setStats(int invoices, double revenue, int customers) {
		this.totalInvoices = invoices;
		this.totalRevenue = revenue;
		this.totalCustomers = customers;
	}

	// Tao bao cao
	@Override
	public void generate() {
		this.generateDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateStr = sdf.format(generateDate);

		// Xay dung chu thich khoang thoi gian neu co
		String periodStr = "";
		if (startDate != null && endDate != null && !startDate.equals(endDate)) {
			periodStr = "\nKhoảng thời gian: " + sdf.format(startDate) + " -> " + sdf.format(endDate) + "\n";
		}

		if (type.equalsIgnoreCase("Doanh Thu")) {
		    this.data =
		        "===== BÁO CÁO DOANH THU =====\n"
		        + "Ngày tạo báo cáo: " + dateStr + "\n"
		        + periodStr + "\n"
		        + "1. Tổng số hóa đơn đã thanh toán: " + totalInvoices + "\n"
		        + "2. Tổng doanh thu ghi nhận: "
		        + String.format("%,.0f", totalRevenue) + " VND\n"
		        + "3. Doanh thu trung bình mỗi hóa đơn: "
		        + String.format("%,.0f", totalInvoices == 0 ? 0 : totalRevenue / totalInvoices) + " VND\n"
		        + "4. Trạng thái hoạt động kinh doanh: Ổn định\n"
		        + "5. Dữ liệu được tổng hợp từ các giao dịch đã hoàn tất.\n\n"
		        + "ĐÁNH GIÁ:\n"
		        + "- Doanh thu được ghi nhận từ các hóa đơn thanh toán hợp lệ.\n"
		        + "- Các giao dịch đã được xử lý và lưu trữ trên hệ thống.\n"
		        + "- Số liệu hỗ trợ việc theo dõi tình hình kinh doanh hiện tại.\n\n"
		        + "KẾT LUẬN:\n"
		        + "Báo cáo doanh thu cung cấp thông tin tổng quan về kết quả "
		        + "kinh doanh của khách sạn. Dữ liệu này hỗ trợ nhà quản lý "
		        + "đánh giá hiệu quả hoạt động và đưa ra các quyết định phù hợp.";
		} else if (type.equalsIgnoreCase("Khách Hàng")) {
		    this.data =
		        "===== BÁO CÁO KHÁCH HÀNG =====\n"
		        + "Ngày tạo báo cáo: " + dateStr + "\n"
		        + periodStr + "\n"
		        + "1. Tổng số khách hàng đã sử dụng dịch vụ: " + totalCustomers + "\n"
		        + "2. Tổng lượt đặt phòng ghi nhận: " + totalInvoices + "\n"
		        + "3. Trạng thái dữ liệu khách hàng: Đang hoạt động\n"
		        + "4. Dữ liệu khách hàng được lưu trữ và quản lý trên hệ thống.\n\n"
		        + "ĐÁNH GIÁ:\n"
		        + "- Hệ thống ghi nhận đầy đủ thông tin khách hàng.\n"
		        + "- Dữ liệu hỗ trợ quá trình đặt phòng và thanh toán.\n"
		        + "- Thông tin khách hàng được sử dụng để theo dõi mức độ sử dụng dịch vụ.\n\n"
		        + "KẾT LUẬN:\n"
		        + "Báo cáo khách hàng phản ánh số lượng khách đã sử dụng dịch vụ. "
		        + "Các số liệu này giúp bộ phận quản lý đánh giá nhu cầu sử dụng dịch vụ, "
		        + "nâng cao chất lượng chăm sóc khách hàng.";
		} else {
		    this.data = "Không có dữ liệu cho loại báo cáo này.";
		}
	}

	// Xuat bao cao ra file
	@Override
	public File export(String format) {
		String fileName = this.title.replaceAll("\\s+", "_") + "." + format.toLowerCase();
		return new File(fileName);
	}

	@Override
	public String getTitle() {
		return this.title;
	}
}