import java.util.Date;

import javax.swing.JFrame;

public class BookingView extends JFrame {
	
    private BookingController bookingController;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomType;

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public void render() {
    }

    public void onSubmitBooking() {
    }

    public void showSuccessMessage(String message) {
    }

}