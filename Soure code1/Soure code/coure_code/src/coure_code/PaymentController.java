import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PaymentController {
	private Payment payment;
	private PaymentView view;
	private RoomController roomController;
	private PromotionEngine promotionEngine;
	private ServiceManager serviceManager;
	private BookingManager bookingManager;
	private NotificationService notificationService;

	// Track cac hoa don da thanh toan de tranh double payment
	private Set<Integer> paidInvoiceIds = new HashSet<>();

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

	// Tim BookingDetail theo so phong
	private BookingDetail findBookingDetailByRoom(String roomNum) {
		if (bookingManager == null || roomNum == null || roomNum.isEmpty())
			return null;
		for (Booking booking : bookingManager.getAllBookings()) {
			for (BookingDetail detail : booking.getBookingDetails()) {
				if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber()))
					return detail;
			}
		}
		return null;
	}

	// Tim Booking theo so phong
	private Booking findBookingByRoom(String roomNum) {
		if (bookingManager == null || roomNum == null || roomNum.isEmpty())
			return null;
		for (Booking booking : bookingManager.getAllBookings()) {
			for (BookingDetail detail : booking.getBookingDetails()) {
				if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber()))
					return booking;
			}
		}
		return null;
	}

	// Tim Room theo so phong
	private Room findRoomByNumber(String roomNum) {
		if (roomNum == null || roomNum.isEmpty())
			return null;
		if (roomController != null) {
			for (Room r : roomController.getAllRooms()) {
				if (r.getRoomNumber().equals(roomNum))
					return r;
			}
		}
		if (bookingManager != null) {
			for (Booking booking : bookingManager.getAllBookings()) {
				for (BookingDetail detail : booking.getBookingDetails()) {
					if (detail.getRoom() != null && roomNum.equals(detail.getRoom().getRoomNumber()))
						return detail.getRoom();
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

	// Dinh dang ma hoa don
	public static String formatInvoiceCode(int id) {
		return String.format("HD%03d", id);
	}

	// Chuyen doi ma hoa don sang so nguyen
	public static int parseInvoiceCode(String code) {
		if (code == null || code.isEmpty())
			return 0;
		String cleaned = code.trim().toUpperCase();
		if (cleaned.startsWith("HD")) {
			try {
				return Integer.parseInt(cleaned.substring(2));
			} catch (NumberFormatException e) {
			}
		}
		try {
			return Integer.parseInt(cleaned);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	// Nhap Ma Hoa Don -> tu dong dien so phong va tinh tien
	public void handleInvoiceIdChanged(String invoiceIdStr) {
		if (bookingManager == null || invoiceIdStr == null || invoiceIdStr.isEmpty())
			return;
		try {
			int bookingId = parseInvoiceCode(invoiceIdStr);
			Booking booking = bookingManager.getBookingById(bookingId);
			if (booking != null && !booking.getBookingDetails().isEmpty()) {
				Room room = booking.getBookingDetails().get(0).getRoom();
				if (room != null && room.getRoomNumber() != null) {
					view.setRoomNumber(room.getRoomNumber());
					calculateAndDisplayPaymentDetails(room, room.getRoomNumber());
				}
			} else {
				view.setRoomNumber("");
				view.setPaymentDetails("", "0", "0", "");
				view.setServiceFee("0");
				view.setPromoMessage("");
			}
		} catch (NumberFormatException ex) {
		}
	}

	// Nhap So Phong -> tu dong tinh tien
	public void handleRoomNumberChanged(String roomNum, String serviceFeeStr) {
		if (roomNum.isEmpty()) {
			view.setPaymentDetails("", "0", "0", "");
			view.setServiceFee("0");
			view.setPromoMessage("");
			return;
		}
		Room targetRoom = findRoomByNumber(roomNum);
		if (targetRoom != null) {
			calculateAndDisplayPaymentDetails(targetRoom, roomNum);
		} else {
			view.setPaymentDetails("", "0", "0", "");
			view.setServiceFee("0");
			view.setPromoMessage("");
		}
	}

	// Tinh tien + kiem tra dieu kien giam gia
	private void calculateAndDisplayPaymentDetails(Room targetRoom, String roomNum) {
		double roomFee = targetRoom.calculatePrice();
		double serviceFee = (serviceManager != null) ? serviceManager.getTotalServiceFeeByRoom(roomNum) : 0;
		view.setServiceFee(String.format("%.0f", serviceFee));

		double tax = (roomFee + serviceFee) * 0.1;
		double discountAmount = 0;

		BookingDetail detail = findBookingDetailByRoom(roomNum);
		double totalBeforeDiscount = roomFee + serviceFee + tax;
		boolean eligible = (promotionEngine != null)
				&& promotionEngine.canApplyPromotion(targetRoom, roomFee, detail, totalBeforeDiscount);

		if (eligible && promotionEngine.getPromotions() != null) {
			for (Promotion p : promotionEngine.getPromotions()) {
				if (promotionEngine.validatePromotion(p)) {
					discountAmount = (roomFee + serviceFee) * (p.getDiscountPercent() / 100.0);
					view.setPromoMessage(
							String.format("Đã áp mã [%s] giảm %.0f%% trên tiền phòng + dịch vụ (-%.0f VND)",
									p.getPromoCode(), p.getDiscountPercent(), discountAmount));
					break;
				}
			}
		} else {
			String reason;
			if (targetRoom instanceof VipRoom) {
				long nights = 0;
				if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
					nights = (detail.getCheckOutDate().getTime() - detail.getCheckInDate().getTime())
							/ (1000 * 60 * 60 * 24);
				}
				reason = "Phòng VIP cần đạt tối thiểu 2 đêm (hiện tại: " + nights + " đêm)";
			} else {
				reason = String.format("Phòng STANDARD cần tổng tiền >= 2.000.000 VND (hiện tại: %,.0f VND)",
						totalBeforeDiscount);
			}
			view.setPromoMessage("Lưu ý: " + reason);
			discountAmount = 0;
		}

		double total = roomFee + serviceFee + tax - discountAmount;
		view.setPaymentDetails(String.format("%.0f", roomFee), String.format("%.0f", tax),
				String.format("%.0f", discountAmount), String.format("%.0f", total));
	}

	// Xem chi tiet hoa don
	public void handleViewInvoice(String invoiceId, String roomNum, String roomFeeStr, String serviceFeeStr,
			String discountStr, String taxStr, String totalStr) {
		try {
			int id = (invoiceId == null || invoiceId.isEmpty()) ? 0 : parseInvoiceCode(invoiceId);
			double roomFee = parse(roomFeeStr);
			double serviceFee = parse(serviceFeeStr);
			double discount = parse(discountStr);
			double tax = parse(taxStr);
			double total = (totalStr == null || totalStr.isEmpty()) ? roomFee + serviceFee + tax - discount
					: parse(totalStr);

			BookingDetail detail = findBookingDetailByRoom(roomNum);
			long nights = 0;
			if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
				nights = (detail.getCheckOutDate().getTime() - detail.getCheckInDate().getTime())
						/ (1000 * 60 * 60 * 24);
			}

			Room room = findRoomByNumber(roomNum);
			String promoNote = "";
			if (room != null && promotionEngine != null) {
				double totalCheck = roomFee + serviceFee + tax;
				boolean eligible = promotionEngine.canApplyPromotion(room, roomFee, detail, totalCheck);
				if (eligible && discount > 0) {
					promoNote = "Đã áp mã giảm giá";
				} else if (!eligible) {
					if (room instanceof VipRoom) {
						promoNote = "VIP cần >= 2 đêm (hiện: " + nights + " đêm)";
					} else {
						promoNote = String.format("STANDARD cần tổng >= 2.000.000 VND (hiện: %,.0f VND)", totalCheck);
					}
				}
			}

			// Kiem tra da thanh toan chua
			boolean alreadyPaid = paidInvoiceIds.contains(id);

			StringBuilder sb = new StringBuilder();
			sb.append("=======================================\n");
			sb.append("           CHI TIẾT HÓA ĐƠN\n");
			sb.append("=======================================\n");
			sb.append(String.format("Mã hóa đơn   : %s\n", formatInvoiceCode(id)));
			if (alreadyPaid)
				sb.append("TRẠNG THÁI   : ĐÃ THANH TOÁN\n");
			sb.append(String.format("Số phòng     : %s\n", roomNum == null || roomNum.isEmpty() ? "---" : roomNum));
			sb.append(String.format("Ngày xem     : %s\n",
					new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date())));
			if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
				sb.append(String.format("Check-in     : %s\n", DATE_FORMAT.format(detail.getCheckInDate())));
				sb.append(String.format("Check-out    : %s\n", DATE_FORMAT.format(detail.getCheckOutDate())));
				sb.append(String.format("Số đêm       : %d đêm\n", nights));
			} else {
				sb.append("Check-in     : ---\nCheck-out    : ---\n");
			}
			sb.append("---------------------------------------\n");
			sb.append(String.format("Tiền phòng   : %,.0f VND\n", roomFee));
			sb.append(String.format("Tiền dịch vụ : %,.0f VND\n", serviceFee));
			sb.append(String.format("Tạm tính     : %,.0f VND\n", roomFee + serviceFee));
			sb.append(String.format("Giảm giá     : %,.0f VND\n", discount));
			if (!promoNote.isEmpty())
				sb.append("               ").append(promoNote).append("\n");
			sb.append(String.format("Thuế VAT 10%% : %,.0f VND\n", tax));
			sb.append("---------------------------------------\n");
			sb.append(String.format("TỔNG CỘNG    : %,.0f VND\n", total));
			sb.append("=======================================\n");

			view.showInvoicePreview(sb.toString());
		} catch (Exception ex) {
			view.showMessage("Vui lòng nhập đầy đủ thông tin hóa đơn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Xac nhan thanh toan - co kiem tra double payment
	public void handleConfirmPayment(String invoiceId, String roomNum, String roomFeeStr, String serviceFeeStr,
			String discountStr, String taxStr, String totalStr, String method) {
		try {
			if (totalStr == null || totalStr.isEmpty()) {
				view.showMessage("Vui lòng nhập mã hóa đơn trước khi xác !", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			double roomFee = parse(roomFeeStr);
			double serviceFee = parse(serviceFeeStr);
			double discount = parse(discountStr);
			double tax = parse(taxStr);
			double total = parse(totalStr);
			int id = (invoiceId == null || invoiceId.isEmpty()) ? 1 : parseInvoiceCode(invoiceId);

			// Kiem tra double payment
			if (paidInvoiceIds.contains(id)) {
				view.showMessage(
						"Hoá đơn " + formatInvoiceCode(id) + " đã được thanh toán trước đó!\nKhông thể thanh toán lại.",
						"Cảnh Báo", JOptionPane.WARNING_MESSAGE);
				return;
			}

			processWebPayment(id, total, method);

			// Danh dau da thanh toan
			paidInvoiceIds.add(id);

			// Cap nhat trang thai booking sang COMPLETED
			if (bookingManager != null) {
				bookingManager.updateBookingStatus(id, BookingStatus.COMPLETED);
			}

			BookingDetail detailForCheckout = findBookingDetailByRoom(roomNum);
			if (detailForCheckout != null) {
				detailForCheckout.setCheckOutDate(new java.util.Date());
			}

			Invoice invoice = new Invoice(id, null, roomFee, serviceFee, (roomFee + serviceFee), discount, tax, total,
					null, new Date(), InvoiceStatus.PAID);

			StringBuilder sb = new StringBuilder();
			sb.append(invoice.getFormattedInvoice());
			BookingDetail detail = findBookingDetailByRoom(roomNum);
			if (detail != null && detail.getCheckInDate() != null && detail.getCheckOutDate() != null) {
				sb.append("\nCheck-in: ").append(DATE_FORMAT.format(detail.getCheckInDate()));
				sb.append("\nCheck-out: ").append(DATE_FORMAT.format(detail.getCheckOutDate()));
			}

			view.showInvoicePreview(sb.toString());
			Report.addRevenue(total);
			view.addInvoiceToTable(new Object[] { formatInvoiceCode(id), roomFee, serviceFee, total, "PAID" });
			view.showMessage("Thanh toán thành công bằng " + method, "Thành Công", JOptionPane.INFORMATION_MESSAGE);
			view.setPromoMessage("");

			Booking booking = findBookingByRoom(roomNum);
			if (booking != null && booking.getCustomer() != null) {
				notificationService.sendPaymentRecap(payment, booking.getCustomer());
			}
		} catch (Exception ex) {
			view.showMessage("Lỗi xác nhận thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Xu ly thanh toan qua web
	public void processWebPayment(int invoiceId, double amount, String method) {
		if (payment != null) {
			boolean result = payment.processPayment(amount);
			System.out.println(result ? "Payment Successful" : "Payment Failed");
		}
	}

	private double parse(String s) {
		if (s == null || s.trim().isEmpty())
			return 0;
		try {
			return Double.parseDouble(s.trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}