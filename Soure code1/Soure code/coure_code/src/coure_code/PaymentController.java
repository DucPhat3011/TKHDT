
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class PaymentController {
	private Payment payment;
	private PaymentView view;
	private RoomController roomController;
	private PromotionEngine promotionEngine;
	private ServiceManager serviceManager;
	private BookingManager bookingManager;
	private NotificationService notificationService;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public PaymentController(Payment payment, PaymentView view, RoomController roomController,
			PromotionEngine promotionEngine, ServiceManager serviceManager, BookingManager bookingManager) {
		this.payment = payment;
		this.view = view;
		this.roomController = roomController;
		this.promotionEngine = promotionEngine;
		this.serviceManager = serviceManager;
		this.bookingManager = bookingManager;
		this.notificationService = new NotificationService();

		this.view.setPaymentController(this);
	}

	// Tim BookingDetail tuong ung voi so phong
	private BookingDetail findBookingDetailByRoom(String roomNum) {
		if (bookingManager == null || roomNum == null || roomNum.isEmpty()) {
			return null;
		}
		for (Booking booking : bookingManager.getAllBookings()) {
			for (BookingDetail detail : booking.getBookingDetails()) {
				if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber())) {
					return detail;
				}
			}
		}
		return null;
	}

	// Tim Booking (de lay Customer) tuong ung voi so phong
	private Booking findBookingByRoom(String roomNum) {
		if (bookingManager == null || roomNum == null || roomNum.isEmpty()) {
			return null;
		}
		for (Booking booking : bookingManager.getAllBookings()) {
			for (BookingDetail detail : booking.getBookingDetails()) {
				if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber())) {
					return booking;
				}
			}
		}
		return null;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// Chuyen int -> "HD001", "HD002", ...
	public static String formatInvoiceCode(int id) {
		return String.format("HD%03d", id);
	}

	// Chuyen "HD001" hoac "1" -> int
	public static int parseInvoiceCode(String code) {
		if (code == null || code.isEmpty()) return 0;
		String cleaned = code.trim().toUpperCase();
		if (cleaned.startsWith("HD")) {
			try {
				return Integer.parseInt(cleaned.substring(2));
			} catch (NumberFormatException e) { /* fall through */ }
		}
		try {
			return Integer.parseInt(cleaned);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	// Nhap Ma Hoa Don (= Ma Booking) -> tu dong tra so phong tu Booking
	public void handleInvoiceIdChanged(String invoiceIdStr) {
		if (bookingManager == null || invoiceIdStr == null || invoiceIdStr.isEmpty()) {
			return;
		}
		try {
			int bookingId = parseInvoiceCode(invoiceIdStr);
			Booking booking = bookingManager.getBookingById(bookingId);
			if (booking != null && !booking.getBookingDetails().isEmpty()) {
				Room room = booking.getBookingDetails().get(0).getRoom();
				if (room != null && room.getRoomNumber() != null) {
					view.setRoomNumber(room.getRoomNumber());
					// Tu dong tinh tien phong + tien dich vu + VAT + giam gia + tong tien
					// Dung truc tiep Room lay tu Booking (vi room nay co the chua co trong RoomController)
					calculateAndDisplayPaymentDetails(room, room.getRoomNumber());
				}
			} else {
				// Khong tim thay hoa don/booking -> xoa cac o lien quan
				view.setRoomNumber("");
				view.setPaymentDetails("", "0", "0", "");
				view.setServiceFee("0");
			}
		} catch (NumberFormatException ex) {
			// Ma hoa don khong phai so -> bo qua, khong tu dien
		}
	}

	// Tim phong, tinh VAT, khuyen mai, dich vu (khi nguoi dung tu nhap So Phong)
	public void handleRoomNumberChanged(String roomNum, String serviceFeeStr) {
		if (roomNum.isEmpty() || roomController == null) {
			view.setPaymentDetails("", "0", "0", "");
			view.setServiceFee("0");
			return;
		}

		Room targetRoom = null;
		// 1. Tim trong danh sach phong cua RoomController
		for (Room r : roomController.getAllRooms()) {
			if (r.getRoomNumber().equals(roomNum)) {
				targetRoom = r;
				break;
			}
		}

		// 2. Neu khong co trong RoomController, tim trong Booking (phong tao boi RoomFactory khi dat phong)
		if (targetRoom == null && bookingManager != null) {
			for (Booking booking : bookingManager.getAllBookings()) {
				for (BookingDetail detail : booking.getBookingDetails()) {
					if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber())) {
						targetRoom = detail.getRoom();
						break;
					}
				}
				if (targetRoom != null) break;
			}
		}

		if (targetRoom != null) {
			calculateAndDisplayPaymentDetails(targetRoom, roomNum);
		} else {
			view.setPaymentDetails("", "0", "0", "");
			view.setServiceFee("0");
		}
	}

	// Tinh tien phong + tien dich vu + VAT + giam gia + tong tien va hien thi len view
	private void calculateAndDisplayPaymentDetails(Room targetRoom, String roomNum) {
		double roomFee = targetRoom.calculatePrice();

		// Dich vu
		double serviceFee = (serviceManager != null) ? serviceManager.getTotalServiceFeeByRoom(roomNum) : 0;
		view.setServiceFee(String.format("%.0f", serviceFee));

		// VAT 10%
		double tax = (roomFee + serviceFee) * 0.1;

		// Ap dung khuyen mai
		double discountAmount = 0;
		if (promotionEngine != null && promotionEngine.getPromotions() != null) {
			for (Promotion p : promotionEngine.getPromotions()) {
				if (p.isValid()) {
					discountAmount = roomFee * (p.getDiscountPercent() / 100.0);
					break;
				}
			}
		}

		// Tu dong tinh luon tong thanh toan
		double total = roomFee + serviceFee + tax - discountAmount;
		view.setPaymentDetails(String.format("%.0f", roomFee), String.format("%.0f", tax),
				String.format("%.0f", discountAmount), String.format("%.0f", total));
	}

	// Tong tien thuc te khach phai tra
	public void handleCalculate(String roomFeeStr, String serviceFeeStr, String discountStr, String taxStr) {
		try {
			double roomFee = roomFeeStr.isEmpty() ? 0 : Double.parseDouble(roomFeeStr);
			double serviceFee = serviceFeeStr.isEmpty() ? 0 : Double.parseDouble(serviceFeeStr);
			double discount = discountStr.isEmpty() ? 0 : Double.parseDouble(discountStr);
			double tax = taxStr.isEmpty() ? 0 : Double.parseDouble(taxStr);

			double total = roomFee + serviceFee + tax - discount;
			view.setTotalAmount(String.valueOf(total));
		} catch (Exception ex) {
			view.showMessage("Dữ liệu các ô tính toán không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Hien thi chi tiet hoa don truoc khi xac nhan
	public void handleViewInvoice(String invoiceId, String roomNum, String roomFeeStr, String serviceFeeStr,
			String discountStr, String taxStr, String totalStr) {
		try {
			int id = (invoiceId == null || invoiceId.isEmpty()) ? 0 : parseInvoiceCode(invoiceId);
			double roomFee = (roomFeeStr == null || roomFeeStr.isEmpty()) ? 0 : Double.parseDouble(roomFeeStr);
			double serviceFee = (serviceFeeStr == null || serviceFeeStr.isEmpty()) ? 0
					: Double.parseDouble(serviceFeeStr);
			double discount = (discountStr == null || discountStr.isEmpty()) ? 0 : Double.parseDouble(discountStr);
			double tax = (taxStr == null || taxStr.isEmpty()) ? 0 : Double.parseDouble(taxStr);
			double total = (totalStr == null || totalStr.isEmpty()) ? (roomFee + serviceFee + tax - discount)
					: Double.parseDouble(totalStr);

			Invoice invoice = new Invoice(id, null, roomFee, serviceFee, roomFee + serviceFee, discount, tax, total,
					null, new java.util.Date(), InvoiceStatus.UNPAID);

			StringBuilder sb = new StringBuilder();
			sb.append("=======================================\n");
			sb.append("           CHI TIẾT HÓA ĐƠN\n");
			sb.append("=======================================\n");
			sb.append(String.format("Mã hóa đơn   : %s\n", formatInvoiceCode(id)));
			sb.append(String.format("Số phòng     : %s\n", (roomNum == null || roomNum.isEmpty()) ? "---" : roomNum));
			sb.append(String.format("Ngày xem     : %s\n",
					new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date())));

			// Hien thi ngay check-in / check-out cua phong (neu tim duoc booking detail)
			BookingDetail detail = findBookingDetailByRoom(roomNum);
			if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
				sb.append(String.format("Check-in     : %s\n", DATE_FORMAT.format(detail.getCheckInDate())));
				sb.append(String.format("Check-out    : %s\n", DATE_FORMAT.format(detail.getCheckOutDate())));
			} else {
				sb.append("Check-in     : ---\n");
				sb.append("Check-out    : ---\n");
			}
			sb.append("---------------------------------------\n");
			sb.append(String.format("Tiền phòng   : %,.0f VND\n", roomFee));
			sb.append(String.format("Tiền dịch vụ : %,.0f VND\n", serviceFee));
			sb.append(String.format("Tạm tính     : %,.0f VND\n", roomFee + serviceFee));
			sb.append(String.format("Giảm giá     : %,.0f VND\n", discount));
			sb.append(String.format("Thuế VAT 10%% : %,.0f VND\n", tax));
			sb.append("---------------------------------------\n");
			sb.append(String.format("TỔNG CỘNG    : %,.0f VND\n", total));
			sb.append("---------------------------------------\n");
			sb.append(String.format("Trạng thái   : %s\n", invoice.getStatus()));
			sb.append("=======================================\n");

			view.showInvoicePreview(sb.toString());
		} catch (Exception ex) {
			view.showMessage("Vui lòng nhập đầy đủ thông tin hóa đơn trước khi xem!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Xu ly khi nhan xac nhan
	public void handleConfirmPayment(String invoiceId, String roomNum, String roomFee, String serviceFee,
			String totalStr, String method) {
		try {
			if (totalStr.isEmpty()) {
				view.showMessage("Vui lòng bấm nút Tính tổng trước khi xác nhận!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			double total = Double.parseDouble(totalStr);
			int id = invoiceId.isEmpty() ? 1 : parseInvoiceCode(invoiceId);

			processWebPayment(id, total, method);

			// Check-out = thoi diem thanh toan hoa don
			BookingDetail detailForCheckout = findBookingDetailByRoom(roomNum);
			if (detailForCheckout != null) {
				detailForCheckout.setCheckOutDate(new java.util.Date());
			}

			// Hien thi hoa don len man hinh
			Invoice invoice = new Invoice(id, null, Double.parseDouble(roomFee), Double.parseDouble(serviceFee),
					(Double.parseDouble(roomFee) + Double.parseDouble(serviceFee)), 0, 0, total, null,
					new java.util.Date(), InvoiceStatus.PAID);

			StringBuilder sb = new StringBuilder();
			sb.append(invoice.getFormattedInvoice());

			// Them thong tin check-in / check-out vao hoa don
			BookingDetail detail = findBookingDetailByRoom(roomNum);
			if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
				sb.append("\nCheck-in: ").append(DATE_FORMAT.format(detail.getCheckInDate()));
				sb.append("\nCheck-out: ").append(DATE_FORMAT.format(detail.getCheckOutDate()));
			}

			view.showInvoicePreview(sb.toString());

			view.addInvoiceToTable(new Object[] { formatInvoiceCode(id), roomFee, serviceFee, total, "PAID" });
			view.showMessage("Thanh toán thành công bằng " + method, "Thành Công", JOptionPane.INFORMATION_MESSAGE);

			// Gui thong bao hoa don/thanh toan cho khach (Observer pattern)
			Booking booking = findBookingByRoom(roomNum);
			if (booking != null && booking.getCustomer() != null) {
				notificationService.sendPaymentRecap(payment, booking.getCustomer());
			}
		} catch (Exception ex) {
			view.showMessage("Lỗi xác nhận thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void processWebPayment(int invoiceId, double amount, String method) {
		if (payment != null) {
			boolean result = payment.processPayment(amount);
			if (result) {
				System.out.println("Payment Successful");
			} else {
				System.out.println("Payment Failed");
			}
		}
	}
}