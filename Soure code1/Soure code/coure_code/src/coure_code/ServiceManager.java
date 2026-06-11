package coure_code;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<Services> services;
    private IPriceStrategy priceStrategy;

    public void addService(Services service) {
        if (this.services == null) {
            this.services = new ArrayList<>();
        }
        this.services.add(service);
        System.out.println("Đã thêm dịch vụ: " + service.getServiceName());
    }

    public void updateService(int serviceId, Services updatedService) {
        if (this.services != null) {
            for (int i = 0; i < this.services.size(); i++) {
                if (this.services.get(i).getServiceId() == serviceId) {
                    this.services.set(i, updatedService);
                    System.out.println("Đã cập nhật dịch vụ có ID: " + serviceId);
                    return;
                }
            }
        }
        System.out.println("Không tìm thấy dịch vụ ID: " + serviceId);
    }

    public void deleteService(int serviceId) {
        if (this.services != null) {
            boolean isRemoved = this.services.removeIf(s -> s.getServiceId() == serviceId);
            if (isRemoved) {
                System.out.println("Hệ thống đã xóa dịch vụ có ID: " + serviceId);
            } else {
                System.out.println("Không tìm thấy dịch vụ ID: " + serviceId + " để xóa.");
            }
        }
    }

    public List<Services> getAllServices() {
        if (this.services == null) {
            this.services = new ArrayList<>();
        }
        return this.services;
    }

    public void setPriceStrategy(IPriceStrategy strategy) {
        this.priceStrategy = strategy;
    }

    public double calculateServicePrice(Services service) {
        if (service != null && this.priceStrategy != null) {
            return this.priceStrategy.calculatePrice(service.getUnitPrice());
        }
        return service != null ? service.getUnitPrice() : 0.0;
    }
}