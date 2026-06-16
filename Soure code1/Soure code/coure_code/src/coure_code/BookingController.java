
import java.time.LocalDate;

public class BookingController {
	private BookingManager modelManager;
	private BookingView view;

	public BookingController(BookingManager modelManager, BookingView view) {
		this.modelManager = modelManager;
		this.view = view;

		this.view.setBookingController(this);
	}

	// Xu ly yeu cau dat phong
		public Booking handleBookingRequest(String customerId, String roomNumber, String roomType, LocalDate checkIn,
				LocalDate checkOut) {
			try {
				// Tao customer voi gia tri mac dinh hop ly
				Customer customer = new Customer(Integer.parseInt(customerId), "customer" + customerId, "", "",
						"Khach Hang " + customerId, "", "", "", "", 0, null);

				// Kiem tra ngay check-in khong duoc la qua khu
				if (checkIn.isBefore(LocalDate.now())) {
					throw new IllegalArgumentException("Ngay check-in khong duoc la ngay qua khu!");
				}

				// Kiem tra so dem toi da 30 dem
				long nights = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
				if (nights > 30) {
					throw new IllegalArgumentException("So dem luu tru khong duoc vuot qua 30 dem!");
				}

				// Kiem tra dat phong truoc toi da 365 ngay
				if (checkIn.isAfter(LocalDate.now().plusDays(365))) {
					throw new IllegalArgumentException("Chi duoc dat phong truoc toi da 365 ngay!");
				}

				// Kiem tra loai phong theo so phong
				int floor = Integer.parseInt(roomNumber.substring(0, 1));

				if (floor >= 1 && floor <= 4 && !roomType.equalsIgnoreCase("STANDARD")) {
					throw new IllegalArgumentException("Tang 1 den 4 chi duoc dat phong STANDARD!");
				}

				if (floor >= 5 && !roomType.equalsIgnoreCase("VIP")) {
					throw new IllegalArgumentException("Tang 5 tro len chi duoc dat phong VIP!");
				}

				RoomFactory roomFactory = new RoomFactory();
				Room selectedRoom = roomFactory.createRoom(roomType);

				// Kiem tra room null truoc khi dung
				if (selectedRoom == null) {
					throw new IllegalArgumentException("Loai phong khong hop le: " + roomType);
				}
				selectedRoom.setRoomNumber(roomNumber);

				return modelManager.createBooking(customer, selectedRoom, checkIn, checkOut);

			} catch (NumberFormatException e) {
				System.out.println("Loi: Ma khach hang phai la ky tu so nguyen!");
				throw e;
			} catch (Exception e) {
				System.out.println("Loi phat sinh trong qua trinh xu ly nghiep vu dat phong: " + e.getMessage());
				throw e;
			}
		}
	}