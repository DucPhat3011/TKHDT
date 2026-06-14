public class Services {
	private int serviceId;
	private String serviceName;
	private double unitPrice;
	private String roomNumber;

	public Services(int serviceId, String serviceName, double unitPrice, String roomNumber) {
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.unitPrice = unitPrice;
		this.roomNumber = roomNumber;
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
	
	public String getRoomNumber() { 
		return roomNumber; 
	}
    public void setRoomNumber(String roomNumber) { 
    	this.roomNumber = roomNumber; 
    }

	// Cap nhat gia dich vu
	public void updatePrice(double newPrice) {
		if(newPrice >= 0) {
			this.unitPrice = newPrice;
		} else {
			System.out.println("Gia tien khong hop le!");
		}
		
	}
}