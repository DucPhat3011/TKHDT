package coure_code;

public class RoomFactory {

	/**
     * Phương thức tạo phòng dựa trên loại phòng truyền vào.
     * @param roomType: "VIP" hoặc "STANDARD"
     * @return Đối tượng Room tương ứng
     */
    public Room createRoom(String roomType) {
        // 1. Kiểm tra nếu chuỗi truyền vào bị null
        if (roomType == null) {
            return null;
        }

        String type = roomType.trim().toUpperCase();

        
        if (type.equals("VIP")) {
            return new VipRoom(); 
        } else if (type.equals("STANDARD")) {
            return new StandardRoom();
        }

        System.out.println("Loại phòng không hợp lệ: " + roomType);
        return null;
    }
}
