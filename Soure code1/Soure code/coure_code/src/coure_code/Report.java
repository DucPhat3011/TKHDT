import java.util.Date;
import java.io.File;

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
		// TODO Auto-generated method stub

	}

	@Override
	public File export(String format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}