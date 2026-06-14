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

	@Override
	public void generate() {
		System.out.println("Generating report: " + title);
	}

	@Override
	public File export(String format) {
		System.out.println("Exporting " + title + " to " + format);
        return new File(title + "." + format);
	}

	@Override
	public String getTitle() {
		return title;
	}

}
