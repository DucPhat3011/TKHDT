import java.util.Date;
import java.util.List;
import java.io.File;

public class ReportService {

    public Report getReport(String type, Date startDate, Date endDate) {
        return null;
    }

    public CompositeReport getCompositeReport(List<String> types, Date startDate, Date endDate) {
        return null;
    }

    public File exportReport(IReport report, String format) {
        return null;
    }
}