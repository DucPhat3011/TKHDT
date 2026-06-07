import java.util.ArrayList;
import java.util.List;

public class PromotionEngine {
	private List<Promotion> promotions;
	
	public PromotionEngine(List<Promotion> promotions) {
		this.promotions = new ArrayList<Promotion>();
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	public void createPromotion(Promotion promotion) {
	}

	public void updatePromotion(String promoId, Promotion promotion) {
	}

	public void deletePromotion(String promoId) {
	}

	public List<Promotion> getApplicablePromotions(double amount, String customerType) {
		return null;
	}

	public double applyPromotion(Invoice invoice, Promotion promo) {
		return 0.0;
	}

	public boolean validatePromotion(Promotion promo) {
		return false;
	}

}
