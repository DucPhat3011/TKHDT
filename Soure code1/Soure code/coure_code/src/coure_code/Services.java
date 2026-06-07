public class Services {
	private int serviceId;
	private String serviceName;
	private double unitPrice;

	public Services(int serviceId, String serviceName, double unitPrice) {
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.unitPrice = unitPrice;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void updatePrice(double newPrice) {
	}
}