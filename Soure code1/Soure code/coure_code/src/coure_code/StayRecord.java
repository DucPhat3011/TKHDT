package coure_code;

import java.util.*;


public class StayRecord {
    private int recordId;
    private Date actualCheckIn;
    private Date actualCheckOut;
    private BookingDetail bookingDetail;
    private String checkInNote;
    private List<ServiceUsage> serviceUsages; // Các dịch vụ đã dùng

    public StayRecord(int recordId, BookingDetail bookingDetail, String checkInNote) {
        this.recordId = recordId;
        this.actualCheckIn = new Date();
        this.bookingDetail = bookingDetail;
        this.checkInNote = checkInNote;
        this.serviceUsages = new ArrayList<>();
    }

    public void addService(Services service, int quantity) {
        ServiceUsage usage = new ServiceUsage(serviceUsages.size() + 1, quantity, service, this);
        serviceUsages.add(usage);
        System.out.println("Da cap nhat dich vu: " + service.getServiceName() );
    }

    public void removeService(int serviceId) {
        serviceUsages.removeIf(usage -> usage.getService().getServiceId() == serviceId);
    }

    public double calculateTotalServiceCost() {
        double total = 0;
        for (ServiceUsage usage : serviceUsages) {
            total += usage.calculateServiceCharge();
        }
        return total;
    }

    public void updateActualCheckOut(Date time) {
        this.actualCheckOut = time;
    }

    public void applyExtraCharges(String description, double amount) {
        System.out.println("Phu Thu: " + description + " - " + amount);
    }

    public Invoice generateInvoice() {
        System.out.println("Dang Xuat Hoa Don");
        return null;
    }
}