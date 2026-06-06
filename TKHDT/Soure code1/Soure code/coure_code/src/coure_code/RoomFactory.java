
public class RoomFactory {

	// tao doi tuong phong theo loai phong yeu cau
	public Room createRoom(String roomType) {
		if (roomType == null) {
			return null;
		}
		int autoId = (int) (Math.random() * 1000);
		if (roomType.equalsIgnoreCase("STANDARD")) {
			RoomType standardType = new RoomType(1, "STANDARD", 500000, 2, "Standard Amenities", 2);
			return new StandardRoom(autoId, "STD-" + autoId, RoomStatus.AVAILABLE, standardType, 1, "Mo ta Standard",
					"Single");
		} else {
			if (roomType.equalsIgnoreCase("VIP")) {
				RoomType vipType = new RoomType(2, "VIP", 1200000, 2, "VIP Amenities", 4);
				return new VipRoom(autoId, "VIP-" + autoId, RoomStatus.AVAILABLE, vipType, 1, "Mo ta VIP", true);
			}
		}
		return null;

	}
}
