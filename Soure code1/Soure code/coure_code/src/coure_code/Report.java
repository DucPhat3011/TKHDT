import java.io.File;
import java.util.Date;

public class Report implements IReport {
	private int reportId;
	private String title;
	private String type;
	private Date startDate;
	private Date endDate;
	private Date generateDate;
	private String data;

	public Report(int reportId, String title, String type, Date startDate, Date endDate, Date generateDate,
			String data) {
		this.reportId = reportId;
		this.title = title;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.generateDate = generateDate;
		this.data = data;
	}

	public String getGetTitle() {
		return "";
	}

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

	// tao bao cao
	@Override
	public void generate() {
        this.generateDate = new Date();
        this.data = "Dữ liệu được tạo tự động cho báo cáo: " + this.title;
        System.out.println("Đã sinh dữ liệu cho báo cáo: " + this.title);
	}

	// xuat bao cao ra file
	@Override
	public File export(String format) {
		System.out.println("Đang xuất báo cáo '" + this.title + "' ra định dạng: " + format);
        return new File(title.replaceAll("\\s+", "_") + "." + format);
	}
	
	@Override
	public String getTitle() {
		return title;
	}

}
