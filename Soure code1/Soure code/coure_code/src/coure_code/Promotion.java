package coure_code;

import java.util.Date;

public class Promotion {
	private String promoCode;
	private double discountPercent;
	private Date expiryDate;

	public Promotion(String promoCode, double discountPercent, Date expiryDate) {
		this.promoCode = promoCode;
		this.discountPercent = discountPercent;
		this.expiryDate = expiryDate;
	}

	public boolean isValid() {
		if (expiryDate == null)
			return false;

		Date now = new Date();
		return now.before(expiryDate);
	}

	public double getDiscountPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getPromoCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
