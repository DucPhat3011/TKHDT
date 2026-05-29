package coure_code;

public class ServiceUsage {
    private int usageId;
    private int quantity;
    private Service service;
    private double totalCost;
    private StayRecord stayRecord;

    public ServiceUsage(int usageId, int quantity, Service service, StayRecord stayRecord) {
        this.usageId = usageId;
        this.quantity = quantity;
        this.service = service;
        this.stayRecord = stayRecord;
        this.totalCost = calculateServiceCharge();
    }

    /**
     * Phương thức tính phí dịch vụ theo sơ đồ
     * Phí = Số lượng * Đơn giá dịch vụ
     */
    public double calculateServiceCharge() {
        if (service != null) {
            this.totalCost = this.quantity * service.getUnitPrice();
            return this.totalCost;
        }
        return 0;
    }
}
