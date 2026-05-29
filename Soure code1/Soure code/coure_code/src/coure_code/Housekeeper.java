package coure_code;

public class Housekeeper extends Employee {
	private String shift;

	public Housekeeper(String shift) {
		super();
		this.shift = shift;
	}

	@Override
	public void updateRoomStatus(int roomId, RoomStatus status) {
		System.out.println("Nhân viên ca " + shift + " đã cập nhật phòng " + roomId + " sang trạng thái: " + status);
	}
}
