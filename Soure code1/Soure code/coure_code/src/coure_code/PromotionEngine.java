import java.util.ArrayList;
import java.util.List;

public class PromotionEngine {
	private List<Promotion> promotions;

	public PromotionEngine() {
		this.promotions = new ArrayList<>();
	}

	public PromotionEngine(List<Promotion> promotions) {
		this.promotions = new ArrayList<>(promotions); 
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	// Them promotion moi vao danh sach
	public void createPromotion(Promotion promotion) {
		if (promotion == null) {
			return;
		}
		for (Promotion p : promotions) {
			if (p.getPromoCode().equalsIgnoreCase(promotion.getPromoCode())) {
				System.out.println("Mã khuyến mãi đã tồn tại!");
				return;
			}
		}
		promotions.add(promotion);
	}

	// Cap nhat promotion
	public void updatePromotion(String promoCode, Promotion updated) {
		for (int i = 0; i < promotions.size(); i++) {
			if (promotions.get(i).getPromoCode().equalsIgnoreCase(promoCode)) {
				promotions.set(i, updated);
				System.out.println("Đã cập nhật mã khuyến mãi: " + promoCode);
				return;
			}
		}
		System.out.println("Không tìm thấy mã khuyến mãi: " + promoCode);
	}

	// Xoa 
	public void deletePromotion(String promoCode) {
		promotions.removeIf(p -> p.getPromoCode().equalsIgnoreCase(promoCode));
		System.out.println("Đã xóa mã khuyến mãi: " + promoCode);
	}

	// Lay danh sach promotion con han
	public List<Promotion> getApplicablePromotions(double amount, String customerType) {
		List<Promotion> result = new ArrayList<>();
		for (Promotion p : promotions) {
			if (validatePromotion(p)) {
				if ("VIP".equalsIgnoreCase(customerType) || !p.getPromoCode().startsWith("VIP")) {
					result.add(p);
				}
			}
		}
		System.out.println("Tìm thấy " + result.size() + " mã khuyến mãi áp dụng được.");
		return result;
	}

	// Ap dung promotion vao invoice
	public double applyPromotion(Invoice invoice, Promotion promo) {
		if (invoice == null || promo == null)
			return invoice != null ? invoice.getTotalAmount() : 0;
		if (!validatePromotion(promo)) {
			System.out.println("Mã khuyến mãi " + promo.getPromoCode() + " không hợp lệ hoặc đã hết hạn!");
			return invoice.getTotalAmount();
		}
		invoice.applyPromotion(promo);
		double finalAmount = invoice.calculateFinalAmount();
		System.out.printf("Áp dụng mã [%s] - Giảm %.1f%% -> Tổng tiền: %.2f VNĐ%n", promo.getPromoCode(),
				promo.getDiscountPercent(), finalAmount);
		return finalAmount;
	}

	// Kiem tra hop le
	public boolean validatePromotion(Promotion promo) {
		if (promo == null)
			return false;
		return promo.isValid();
	}
	
	// Kiem tra tong tien (phong + dich vu + VAT)
	public boolean canApplyPromotion(Room room, double roomFee, BookingDetail detail) {
		return canApplyPromotion(room, roomFee, detail, roomFee);
	}

	// Truyen them totalAmount = roomFee + serviceFee + tax
	public boolean canApplyPromotion(Room room, double roomFee, BookingDetail detail, double totalAmount) {
	    if (room instanceof VipRoom) {
	        if (detail == null
	                || detail.getCheckInDate() == null
	                || detail.getCheckOutDate() == null) {
	            return false;
	        }
	        long diff = detail.getCheckOutDate().getTime() - detail.getCheckInDate().getTime();
	        long nights = diff / (1000 * 60 * 60 * 24);
	        return nights >= 2;
	    }
	    return totalAmount >= 2000000;
	}
}