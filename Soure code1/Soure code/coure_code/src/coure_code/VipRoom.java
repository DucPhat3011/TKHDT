
public class VipRoom extends Room {
	private boolean hasComplementatyWine;

	public VipRoom(int roomId, String roomNumber, RoomStatus status, RoomType type, int floor, String description,
			boolean hasComplementatyWine) {
		super(roomId, roomNumber, status, type, floor, description);
		this.hasComplementatyWine = hasComplementatyWine;
	}

	public boolean isHasComplementatyWine() {
		return hasComplementatyWine;
	}

	public void setHasComplementatyWine(boolean hasComplementatyWine) {
		this.hasComplementatyWine = hasComplementatyWine;
	}
	
	@Override
	// tinh gia phong Vip
	public double calculatePrice() {
		// TODO Auto-generated method stub
		return getType().getPricePerNight() + 500000;
	}

}
