
public class ServiceController {
	private ServiceManager modelManager;
	private ServiceManagementView view;

	public ServiceController(ServiceManager modelManager, ServiceManagementView view) {
		this.modelManager = modelManager;
		this.view = view;
	}

	public ServiceManager getModelManager() {
		return modelManager;
	}

	public void setModelManager(ServiceManager modelManager) {
		this.modelManager = modelManager;
	}

	public ServiceManagementView getView() {
		return view;
	}

	public void setView(ServiceManagementView view) {
		this.view = view;
	}

	public double processServicePrice(Services service, String priceType) {
		return 0.0;
	}
}
