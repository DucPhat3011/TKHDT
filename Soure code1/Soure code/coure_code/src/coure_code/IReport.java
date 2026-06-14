import java.io.File;

/**
 * interface chung cho tat ca cac loai bao cao..
 * dong vai tro Component trong Composite Pattern.
 */
public interface IReport {
	public void generate();
	public File export(String format);
	public String getTitle();
}
