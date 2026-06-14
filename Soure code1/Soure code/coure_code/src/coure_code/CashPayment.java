import java.sql.Date;

public class CashPayment extends Payment {
	private double cashReceived;
	private double changeGiven;

	public CashPayment(int paymentId, double amount, Date paymentDate, PaymentMethod method,
			IPriceStrategy priceStrategy, PaymentStatus status, double cashReceived, double changeGiven) {
		super(paymentId, amount, paymentDate, method, priceStrategy, status);
		this.cashReceived = cashReceived;
		this.changeGiven = changeGiven;
	}

	public double getCashReceived() {
		return cashReceived;
	}

	public void setCashReceived(double cashReceived) {
		this.cashReceived = cashReceived;
	}

	public double getChangeGiven() {
		return changeGiven;
	}

	public void setChangeGiven(double changeGiven) {
		this.changeGiven = changeGiven;
	}

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		if (cashReceived >= amount) {
			changeGiven = cashReceived - amount;
			setStatus(PaymentStatus.SUCCESS);
			return true;
		}
		setStatus(PaymentStatus.FAILED);
		return false;
	}

}