import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
	private List<Services> services;
	private IPriceStrategy priceStrategy;

	public ServiceManager(List<Services> services, IPriceStrategy priceStrategy) {
		this.services = services;
		this.priceStrategy = priceStrategy;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	public IPriceStrategy getPriceStrategy() {
		return priceStrategy;
	}

	// Them dich vu moi
	public void addService(Services service) {
		if (this.services == null) {
			this.services = new ArrayList<>();
		}
		this.services.add(service);
		System.out.println("Da them dich vu: " + service.getServiceName());
	}

	// Cap nhat thong tin dich vu
	public void updateService(int serviceId, Services updatedService) {
		if (this.services != null) {
			for (int i = 0; i < this.services.size(); i++) {
				if (this.services.get(i).getServiceId() == serviceId) {
					this.services.set(i, updatedService);
					System.out.println("Da cap nhat dich vu co ID: " + serviceId);
					return;
				}
			}
		}
		System.out.println("Khong tim thay dich vu ID: " + serviceId);
	}

	// Xoa dich vu
	public void deleteService(int serviceId) {
		if (this.services != null) {
			boolean isRemoved = this.services.removeIf(s -> s.getServiceId() == serviceId);
			if (isRemoved) {
				System.out.println("He thong da xoa dich vu co ID: " + serviceId);
			} else {
				System.out.println("Khong tim thay dich vu ID: " + serviceId + " de xoa.");
			}
		}
	}

	// Lay toan bo danh sach dich vu
	public List<Services> getAllServices() {
		if (this.services == null) {
			this.services = new ArrayList<>();
		}
		return this.services;
	}

	// Thay doi chien luoc tinh gia cho he thong
	public void setPriceStrategy(IPriceStrategy strategy) {
		this.priceStrategy = strategy;
	}

	// Tinh so tien thuc te khach phai tra sau khi ap dung chien luoc gia
	public double calculateServicePrice(Services service) {
		if (service != null && this.priceStrategy != null) {
			return this.priceStrategy.calculatePrice(service.getUnitPrice());
		}
		return service != null ? service.getUnitPrice() : 0.0;
	}

	// Tinh tong tien dich vu cua 1 phong cu the
	public double getTotalServiceFeeByRoom(String roomNumber) {
		double total = 0;
		if (this.services != null) {
			for (Services s : this.services) {
				if (s.getRoomNumber() != null && s.getRoomNumber().equals(roomNumber)) {
					total += calculateServicePrice(s);
				}
			}
		}
		return total;
	}

}