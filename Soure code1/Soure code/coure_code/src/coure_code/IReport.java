import java.io.File;

public interface IReport {
	// tao bao cao
	public void generate();
	
	// xuat bao cao theo dinh dang chi dinh
	public File export(String format);
	
	// lay ten bao cao
	public String getTitle();
}
