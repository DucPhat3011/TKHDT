package coure_code;


import javax.swing.JOptionPane;
import java.util.List;

public class ServiceController {
    private ServiceManager modelManager;
    private ServiceManagementView view;

    public ServiceController(ServiceManager modelManager, ServiceManagementView view) {
        this.modelManager = modelManager;
        this.view = view;
        
        // Liên kết 2 chiều: Nói cho View biết ai là Controller của nó
        this.view.setController(this);
        
        // Nạp dữ liệu lần đầu lên bảng
        refreshView();
    }

    // Xử lý logic khi View báo có người bấm nút "Thêm"
    public void handleAddService(String idStr, String name, String priceStr) {
        try {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);

            Services newService = new Services(id, name, price);
            modelManager.addService(newService);
            
            refreshView();
            view.showMessage("Da them dich vu vao danh sach he thong.", "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            view.showMessage("Thong tin nhap khong hop le hoac bo trong!", "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Xử lý logic khi View báo có người bấm nút "Cập nhật"
    public void handleUpdateService(String idStr, String newPriceStr) {
        try {
            int id = Integer.parseInt(idStr);
            double newPrice = Double.parseDouble(newPriceStr);

            // Tìm dịch vụ cần sửa trong Model
            Services targetService = null;
            for (Services s : modelManager.getAllServices()) {
                if (s.getServiceId() == id) {
                    targetService = s;
                    break;
                }
            }

            if (targetService != null) {
                targetService.setUnitPrice(newPrice);
                modelManager.updateService(id, targetService);
                
                refreshView();
                view.showMessage("Da cap nhat gia moi thanh cong.", "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("Khong tim thay dich vu nao co ID: " + id, "Khong tim thay", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            view.showMessage("Nhap Ma ID dich vu va Gia tien hop le de cap nhat!", "Loi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lấy dữ liệu mới nhất từ Model đưa cho View vẽ lại bảng
    private void refreshView() {
        List<Services> allServices = modelManager.getAllServices();
        view.displayServices(allServices);
    }
}