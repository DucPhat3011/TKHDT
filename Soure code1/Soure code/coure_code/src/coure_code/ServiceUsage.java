public class ServiceUsage {
	private int usageId;
	private int quantity;
	private Services service;
	private double totalCost;
	private StayRecord stayRecord;

	public ServiceUsage(int usageId, int quantity, Services service, double totalCost, StayRecord stayRecord) {
		this.usageId = usageId;
		this.quantity = quantity;
		this.service = service;
		this.totalCost = calculateServiceCharge();
		this.stayRecord = stayRecord;
	}

	public int getUsageId() {
		return usageId;
	}

	public void setUsageId(int usageId) {
		this.usageId = usageId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public StayRecord getStayRecord() {
		return stayRecord;
	}

	public void setStayRecord(StayRecord stayRecord) {
		this.stayRecord = stayRecord;
	}

	// Tinh chi phi su dung dich vu
	public double calculateServiceCharge() {
		return service.getUnitPrice() * quantity;
	}

}