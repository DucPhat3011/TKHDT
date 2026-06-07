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
    }

    public void remove(IReport report) {
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