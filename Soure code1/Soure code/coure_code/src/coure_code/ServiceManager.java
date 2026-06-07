import java.util.List;

public class ServiceManager {
	private List<Services> services;
	private IPriceStrategy priceStrategy;

	public ServiceManager(List<Services> services, IPriceStrategy priceStrategy) {
		this.services = services;
		this.priceStrategy = priceStrategy;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	public IPriceStrategy getPriceStrategy() {
		return priceStrategy;
	}

	public void addService(Services service) {
	}

	public void updateService(int serviceId, Services service) {
	}

	public void deleteService(int serviceId) {
	}

	public List<Services> getAllServices() {
		return null;
	}

	public void setPriceStrategy(IPriceStrategy strategy) {
	}

	public double calculateServicePrice(Services service) {
		return 0.0;
	}

}