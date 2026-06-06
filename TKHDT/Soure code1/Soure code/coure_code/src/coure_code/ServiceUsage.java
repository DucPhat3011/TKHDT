package coure_code;
import java.util.Date;

public class ServiceUsage {
    private Service service;
    private int quantity;
    private Date usageDate;

    public ServiceUsage(Service service, int quantity, Date usageDate) {
        this.service = service;
        this.quantity = quantity;
        this.usageDate = usageDate;
    }

    public double getTotalCost() {
        return service.getPrice() * quantity;
    }
}