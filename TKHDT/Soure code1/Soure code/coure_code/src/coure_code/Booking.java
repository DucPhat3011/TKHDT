package coure_code;

import java.time.LocalDateTime;
import java.util.*;

public class Booking implements ISubject {

    // ==================== THUỘC TÍNH ====================
    private int bookingId;                  // Mã đặt phòng
    private Customer customer;              // Khách hàng đặt phòng
    private LocalDateTime bookingDate;      // Ngày tạo đơn
    private LocalDateTime checkInt;         // Ngày nhận phòng
    private LocalDateTime checkOut;         // Ngày trả phòng
    private double totalAmount;             // Tổng tiền
    private BookingStatus status;           // Trạng thái booking
    private String promoCode;               // Mã khuyến mãi
    private String note;                    // Ghi chú / lý do hủy
    private List<IObserver> observers;      // Danh sách observer theo dõi

    // ==================== CONSTRUCTOR ====================
    public Booking(int bookingId, Customer customer,
                   LocalDateTime checkInt, LocalDateTime checkOut, // ✅ Sửa LocalDate → LocalDateTime
                   String promoCode, String note) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.bookingDate = LocalDateTime.now();  // Ngày tạo = thời điểm hiện tại
        this.checkInt = checkInt;
        this.checkOut = checkOut;
        this.status = BookingStatus.PENDING;     // Mặc định trạng thái chờ xác nhận
        this.promoCode = promoCode;
        this.note = note;
        this.observers = new ArrayList<>();      // Khởi tạo danh sách observer rỗng
    }

    // ==================== OBSERVER PATTERN ====================

    // Thêm observer vào danh sách theo dõi
    @Override
    public void attach(IObserver obs) {
        observers.add(obs);
    }

    // Xóa observer khỏi danh sách theo dõi
    @Override
    public void detach(IObserver obs) {
        observers.remove(obs);
    }

    // Thông báo cho tất cả observer khi có thay đổi ✅ thêm @Override
    @Override
    public void notifyObservers() {
        String message = "ID phòng: " + bookingId + " - Trạng thái: " + status;
        for (IObserver observer : observers) {
            observer.update(message); // Gửi thông báo đến từng observer
        }
    }

    // ==================== BUSINESS METHODS ====================

    // Hủy booking với lý do hủy
    public boolean cancel(String reason) {
        if (!isEligibleForCancellation()) {
            return false; // Không đủ điều kiện hủy
        }
        this.note = reason;                      // Lưu lý do hủy vào note
        this.status = BookingStatus.CANCELLED;   // Cập nhật trạng thái
        notifyObservers();                        // Thông báo cho observer
        return true;
    }

    // Tính số tiền hoàn lại khi hủy phòng
    public double getRefundAmount() {
        if (!isEligibleForCancellation()) {
            return 0.0; // Không đủ điều kiện hủy → không hoàn tiền
        }
        if (status == BookingStatus.PENDING) {
            return totalAmount;          // Hoàn 100% nếu đang PENDING
        } else {
            return totalAmount * 0.5;    // Hoàn 50% nếu đã CONFIRMED
        }
    }

    // Cập nhật trạng thái booking và thông báo cho observer
    public boolean updateBookingStatus(BookingStatus newStatus) {
        this.status = newStatus;   // Gán trạng thái mới
        notifyObservers();          // Thông báo cho tất cả observer
        return true;
    }

    // Kiểm tra booking có đủ điều kiện hủy không
    public boolean isEligibleForCancellation() {
        return status == BookingStatus.PENDING || status == BookingStatus.CONFIRMED;
    }

    // Tính phần trăm tiền hoàn lại
    public double getAvailableRefundPercentage() {
        if (!isEligibleForCancellation()) {
            return 0.0; // Không thể hủy → 0%
        }
        if (status == BookingStatus.PENDING) {
            return 100.0; // Hoàn 100%
        }
        return 50.0; // Hoàn 50%
    }

    // ==================== GETTERS ====================

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCheckInDate() {
        return checkInt;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOut;
    }


    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInt = checkInDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOut = checkOutDate;
    }
}