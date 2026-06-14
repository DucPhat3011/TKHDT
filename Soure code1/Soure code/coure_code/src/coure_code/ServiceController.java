import javax.swing.JOptionPane;
import java.util.List;

public class ServiceController {
	private ServiceManager modelManager;
	private ServiceManagementView view;

	public ServiceController(ServiceManager modelManager, ServiceManagementView view) {
		this.modelManager = modelManager;
		this.view = view;
		
		this.view.setController(this);
		
		refreshView();
	}

	// Thay dummyDesc thanh roomNum
	public void handleAddService(String name, String priceStr, String roomNum) {
		try {
			if (name.trim().isEmpty() || priceStr.trim().isEmpty() || roomNum.trim().isEmpty()) {
				view.showMessage("Vui lòng nhập đầy đủ tên, giá và số phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double price = Double.parseDouble(priceStr);

			int nextId = modelManager.getAllServices().size() + 1;

			// Su dung constructor moi voi roomNum
			Services newService = new Services(nextId, name, price, roomNum);
			modelManager.addService(newService);
			
			refreshView();
			view.clearInputFields(); 
			view.showMessage("Đã thêm dịch vụ cho phòng " + roomNum, "Thành công", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			view.showMessage("Dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void handleUpdateService(String idStr, String newPriceStr) {
		try {
			int id = Integer.parseInt(idStr);
			double newPrice = Double.parseDouble(newPriceStr);

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
				view.showMessage("Đã cập nhật giá mới thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
			} else {
				view.showMessage("Không tìm thấy dịch vụ nào có ID: " + id, "Không tìm thấy", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			view.showMessage("Nhập Mã ID dịch vụ và Giá tiền dịch vụ để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshView() {
		List<Services> allServices = modelManager.getAllServices();
		view.displayServiceList(allServices);
	}
}