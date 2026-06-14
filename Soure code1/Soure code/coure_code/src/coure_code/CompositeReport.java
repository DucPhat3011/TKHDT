import java.util.List;
import java.io.File;

public class CompositeReport implements IReport {
	private String title;
	private List<IReport> reports;

	public CompositeReport(String title, List<IReport> reports) {
		this.title = title;
		this.reports = reports;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<IReport> getReports() {
		return reports;
	}

	public void setReports(List<IReport> reports) {
		this.reports = reports;
	}

	public void add(IReport report) {
		reports.add(report);
	}

	public void remove(IReport report) {
		reports.remove(report);
	}

	@Override
	public void generate() {
		System.out.println("Generating composite report: " + title);

        for (IReport report : reports) {
            report.generate();
            }
        }

	@Override
	public File export(String format) {
		System.out.println("Exporting composite report: " + title);
		return new File(title + "." + format);
	}

	@Override
	public String getTitle() {
		return title;
	}

}
