package coure_code;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<Services> serviceList;

    public ServiceManager() {
        this.serviceList = new ArrayList<>();
    }

    public void addService(Services service) {
        serviceList.add(service);
    }

    public void removeService(int serviceId) {
        serviceList.removeIf(s -> s.getServiceId() == serviceId);
    }

    public void updateServicePrice(int serviceId, double newPrice) {
        for (Services s : serviceList) {
            if (s.getServiceId() == serviceId) {
                s.updatePrice(newPrice);
            }
        }
    }
}