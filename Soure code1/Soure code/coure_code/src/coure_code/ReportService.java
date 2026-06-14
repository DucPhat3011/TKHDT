import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportService {

    public Report getReport(String type, Date startDate, Date endDate) {
    	Report report = new Report( 1, type + " Report", type, startDate,
    			endDate, new Date(), "Sample Data");

        report.generate();
        return report;
    }

    public CompositeReport getCompositeReport(List<String> types, Date startDate, Date endDate) {
    	CompositeReport composite = new CompositeReport("Composite Report", new ArrayList<IReport>());

        for (String type : types) {
            composite.add(getReport(type, startDate, endDate));
        }

        return composite;
    }

    public File exportReport(IReport report, String format) {
    	return report.export(format);
    }
}
