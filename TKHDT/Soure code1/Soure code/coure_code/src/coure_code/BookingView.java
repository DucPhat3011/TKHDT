package coure_code;

import java.time.LocalDate;
import java.util.Scanner;

public class BookingView {

    private BookingController bookingController;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomType;

    public BookingView(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    // Hiển thị giao diện booking ra màn hình
    public void render() {
        System.out.println("=== GIAO DIỆN ĐẶT PHÒNG ===");
        System.out.println("Ngày check-in : " + checkInDate);
        System.out.println("Ngày check-out: " + checkOutDate);
        System.out.println("Loại phòng    : " + roomType);
        System.out.println("===========================");
    }

    public void onSubmitBooking() {
        System.out.println("[BookingView] Đang xử lý đặt phòng...");

        bookingController.handleBookingRequest(
                "KH001",
                roomType,
                checkInDate,
                checkOutDate
        );
    }

    // Hiển thị thông báo thành công
    public void showSuccessMessage(String message) {
        System.out.println("Thành Công " + message);
    }
}