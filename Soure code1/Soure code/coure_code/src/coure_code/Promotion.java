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
 
    public String getPromoCode() {
        return promoCode;
    }
 
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
 
    public double getDiscountPercent() {
        return discountPercent;
    }
 
    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
 
    public Date getExpiryDate() {
        return expiryDate;
    }
 
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
 
    // Kiem tra ma khuyen mai con hieu luc hay khong 
    public boolean isValid() {
        return expiryDate != null
                && !new Date().after(expiryDate);
    }
 
    @Override
    public String toString() {
        return "Promotion{code=" + promoCode + ", discount=" + discountPercent + "%, expiry=" + expiryDate + "}";
    }
}
 