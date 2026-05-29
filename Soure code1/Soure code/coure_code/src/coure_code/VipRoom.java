package coure_code;

public class VipRoom extends Room {
	public VipRoom(int roomId, String roomNumber, RoomStatus status, RoomType type, int floor, String description) {
		super(roomId, roomNumber, status, type, floor, description);
		// TODO Auto-generated constructor stub
	}

	private boolean hasComplementaryWine;

	/**
     * Phương thức tính tiền riêng cho phòng VIP (+)
     * Thường sẽ lấy giá từ RoomType và cộng thêm phí dịch vụ VIP
     */
    public double tinhtien() {
        if (this.getType() != null) {
            double basePrice = this.getType().getPricePerNight();
            // Ví dụ: Phòng VIP phụ thu thêm 20% phí dịch vụ hoặc phí rượu vang
            double vipSurcharge = 0.2; 
            return basePrice * (1 + vipSurcharge);
        }
        return 0;
    }

	private Object getType() {
		return null;
	}
}