import java.io.File;

public interface IReport {
	public void generate();
	public File export(String format);
	public String getTitle();
}
