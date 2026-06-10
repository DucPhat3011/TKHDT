package coure_code;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ServiceUsage {
    private int usageId;
    private int quantity;
    private Services service; 
    private double totalCost;
    private StayRecord stayRecord;

    public ServiceUsage(int usageId, int quantity, Services service, StayRecord stayRecord) {
        this.usageId = usageId;
        this.quantity = quantity;
        this.service = service;
        this.stayRecord = stayRecord;
        this.totalCost = calculateServiceCharge();
    }

    public double calculateServiceCharge() {
        return service.getUnitPrice() * quantity;
    }
    
    public Services getService() {
        return service;
    }
}