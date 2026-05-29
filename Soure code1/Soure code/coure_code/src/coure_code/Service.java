package coure_code;

public class Service {
    private int serviceId;
    private String serviceName;
    private double unitPrice;

    public Service(int serviceId, String serviceName, double unitPrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.unitPrice = unitPrice;
    }

    // Phương thức cập nhật giá dịch vụ theo sơ đồ (+)
    public void updatePrice(double newPrice) {
        this.unitPrice = newPrice;
    }

	public int getUnitPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
}
