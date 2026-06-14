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
		if (cardNumber == null || cvv == null) {
			return false;
		}
		if (cardNumber.length() < 12) {
			return false;
		}
		if (cvv.length() != 3) {
			return false;
		}
		if (expiryDate.before(new Date(System.currentTimeMillis()))) {
			return false;
		}
		return true;
	}

	public boolean refund(String transactionId, double amount) {
		if (transactionId == null || transactionId.isEmpty()) {
			return false;
		}
		if (amount <= 0) {
			return false;
		}
		setStatus(PaymentStatus.REFUNDED);
		return true;
	}

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		if (!validateCard()) {
			setStatus(PaymentStatus.FAILED);
			return false;
		}
		ExternalPaymentAPI api = new ExternalPaymentAPI();
		IPaymentGateway adapter = new PaymentAdapter(api);
		boolean isSuccess = adapter.pay(amount);
		if (isSuccess) {
			setStatus(PaymentStatus.SUCCESS);
			return true;
		} else {
			setStatus(PaymentStatus.FAILED);
			return false;
		}
	}
}
