package coure_code;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<Services> servicesList;

    public ServiceManager() {
        this.servicesList = new ArrayList<>();
    }

    // Thêm một dịch vụ mới vào danh mục của khách sạn
    public void addService(Services service) {
        if (service != null) {
            servicesList.add(service);
            System.out.println("Đã thêm dịch vụ: " + service.getServiceName());
        }
    }

    // Xóa một dịch vụ khỏi danh mục
    public void removeService(Services service) {
        if (servicesList.remove(service)) {
            System.out.println("Đã xóa dịch vụ: " + service.getServiceName());
        }
    }

    // Tìm kiếm dịch vụ theo ID
    public Services findServiceById(int serviceId) {
        for (Services s : servicesList) {
            if (s.getServiceId() == serviceId) {
                return s;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Lấy toàn bộ danh sách dịch vụ hiện có
    public List<Services> getAllServices() {
        return servicesList;
    }
    
    // Cập nhật giá cho một dịch vụ
    public void updateServicePrice(int serviceId, double newPrice) {
        Services s = findServiceById(serviceId);
        if (s != null) {
            s.setPrice(newPrice);
            System.out.println("Đã cập nhật giá cho dịch vụ: " + s.getServiceName());
        }
    }
}