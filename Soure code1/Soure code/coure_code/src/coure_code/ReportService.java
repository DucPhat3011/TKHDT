import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportService {
	// tao va tra ve bao cao theo loai va khoang thoi gian
    public Report getReport(String type, Date startDate, Date endDate) {
        // Thực tế sẽ query vào Database, ở đây trả về mock data
        return new Report(
            (int) (Math.random() * 1000), 
            "Báo cáo " + type, 
            type, 
            startDate, 
            endDate, 
            new Date(), 
            "Sample Data"
        );
    }
    
    // tao bao cao tong hop tu nhieu loai bao cao
    public CompositeReport getCompositeReport(List<String> types, Date startDate, Date endDate) {
        List<IReport> subReports = new ArrayList<>();
        for (String type : types) {
            subReports.add(getReport(type, startDate, endDate));
        }
        return new CompositeReport("Báo cáo Tổng Hợp", subReports);
    }


    // xuat bao cao ra file
    public File exportReport(IReport report, String format) {
        if (report != null) {
            return report.export(format);
        }
        return null;
    }
}
