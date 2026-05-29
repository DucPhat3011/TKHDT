package coure_code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staff extends Employee {
	/**
	 * Kiểm tra danh sách phòng trống vào một ngày cụ thể
	 * +checkRoomAvailability(date: Date): List<Room>
	 */
	public List<Room> checkRoomAvailability(Date date) {
		// Logic tìm kiếm phòng trống
		return new ArrayList<Room>();
	}

	/**
	 * Tạo đơn đặt phòng mới +createBooking(data: Booking): boolean
	 */
	public boolean createBooking(Booking data) {
		// Logic lưu thông tin đặt phòng
		System.out.println("Booking created for: " + data.getBookingId());
		return true;
	}

	/**
	 * Xử lý thủ tục nhận phòng +processCheckIn(bookingId: int): void
	 */
	public void processCheckIn(int bookingId) {
		// Logic cập nhật trạng thái phòng sang OCCUPIED và tạo StayRecord
		System.out.println("Processing Check-in for Booking ID: " + bookingId);
	}

	/**
	 * Xử lý thủ tục trả phòng và trả về hóa đơn +processCheckOut(bookingId: int):
	 * Invoice
	 */
	public Invoice processCheckOut(int bookingId) {
		System.out.println("Processing Check-out for Booking ID: " + bookingId);
		return new Invoice(bookingId, salary, null, null, salary, salary, joinDate, salary, null);
	}

	/**
	 * Điều chỉnh hóa đơn (giảm giá hoặc thêm phí phát sinh thủ công)
	 * +adjustInvoice(invoiceId: int, amount: double): void
	 */
	public void adjustInvoice(int invoiceId, double amount) {
		System.out.println("Adjusting Invoice " + invoiceId + " by amount: " + amount);
	}
}
