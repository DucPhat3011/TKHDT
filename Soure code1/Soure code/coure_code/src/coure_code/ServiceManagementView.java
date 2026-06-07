import java.util.List;

import javax.swing.JFrame;

public class ServiceManagementView extends JFrame {
	private ServiceController controller;

	public ServiceController getController() {
		return controller;
	}

	public void setController(ServiceController controller) {
		this.controller = controller;
	}

	public void displayServices(List<Services> services) {
	}

	public void onBtnCalculateClick() {
	}

}
