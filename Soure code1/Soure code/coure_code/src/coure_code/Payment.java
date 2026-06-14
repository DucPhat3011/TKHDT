import java.sql.Date;

public abstract class Payment {
	private int paymentId;
	private double amount;
	private Date paymentDate;
	private PaymentMethod method;
	private IPriceStrategy priceStrategy;
	private PaymentStatus status;

	public Payment(int paymentId, double amount, Date paymentDate, PaymentMethod method, IPriceStrategy priceStrategy,
			PaymentStatus status) {
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.method = method;
		this.priceStrategy = priceStrategy;
		this.status = status;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public IPriceStrategy getPriceStrategy() {
		return priceStrategy;
	}

	public void setPriceStrategy(IPriceStrategy priceStrategy) {
		this.priceStrategy = priceStrategy;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public abstract boolean processPayment(double amount);
}
