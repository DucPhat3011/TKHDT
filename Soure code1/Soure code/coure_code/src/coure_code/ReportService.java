import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportService {
    // tao va tra ve bao cao theo loai va khoang thoi gian
    public Report getReport(String type, Date startDate, Date endDate) {
    	Report report = new Report( 1, type + " Report", type, startDate,
    			endDate, new Date(), "Sample Data");

        report.generate();
        return report;
    }
    
    // tao bao cao tong hop tu nhieu loai bao cao
    public CompositeReport getCompositeReport(List<String> types, Date startDate, Date endDate) {
    	CompositeReport composite = new CompositeReport("Composite Report", new ArrayList<IReport>());

        for (String type : types) {
            composite.add(getReport(type, startDate, endDate));
        }

        return composite;
    }

    // xuat bao cao ra file
    public File exportReport(IReport report, String format) {
    	return report.export(format);
    }
}
