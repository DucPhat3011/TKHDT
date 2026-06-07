import java.sql.Date;

public class CreditCardPayment extends Payment {
	private String cardHolderName;
    private String last4Digits;
    private Date expiryDate;
    private String cardNumber;
    private String cvv;

    public CreditCardPayment(int paymentId, double amount, Date paymentDate, PaymentMethod method,
			IPriceStrategy priceStrategy, PaymentStatus status, String cardHolderName, String last4Digits,
			Date expiryDate, String cardNumber, String cvv) {
		super(paymentId, amount, paymentDate, method, priceStrategy, status);
		this.cardHolderName = cardHolderName;
		this.last4Digits = last4Digits;
		this.expiryDate = expiryDate;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public boolean validateCard() {
        return false;
    }

    public boolean refund(String transactionId, double amount) {
        return false;
    }

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
