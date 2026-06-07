import java.io.File;

public interface IReport {
	void generate();
    File export(String format);
    String getTitle();

}
