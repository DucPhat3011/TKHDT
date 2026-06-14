import java.util.List;
import java.io.File;

public class CompositeReport implements IReport {
	private String title;
	private List<IReport> reports;

	public CompositeReport(String title, List<IReport> reports) {
		this.title = title;
		this.reports = reports;
	}
	
	// cap nhat tieu de bao cao
	public void setTitle(String title) {
		this.title = title;
	}

	// lay danh sach bao cao con
	public List<IReport> getReports() {
		return reports;
	}

	// cap nhat danh sach bao cao con
	public void setReports(List<IReport> reports) {
		this.reports = reports;
	}
	
	// them bao cao vao bao cao tong hop
	public void add(IReport report) {
		reports.add(report);
	}

	// xoa bao cao khoi bao cao tong hop
	public void remove(IReport report) {
		reports.remove(report);
	}
	
	// tao tat ca bao cao con trong danh sach
	@Override
	public void generate() {
		System.out.println("Đang tổng hợp báo cáo: " + title);

        for (IReport report : reports) {
            report.generate();
            }
        }

	// xuat bao cao tong hop ra file
	@Override
	public File export(String format) {
		System.out.println("Đang xuất báo cáo tổng hợp '" + title + "' ra định dạng: ");
		return new File(title.replaceAll("\\s+", "_") + "." + format);
	}

	// lay ten bao cao tong hop
	@Override
	public String getTitle() {
		return title;
	}

}
