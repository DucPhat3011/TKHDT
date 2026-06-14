import java.sql.Date;

public class BankTransferPayment extends Payment {
	private String bankName;
	private String transactionId;

	public BankTransferPayment(int paymentId, double amount, Date paymentDate, PaymentMethod method,
			IPriceStrategy priceStrategy, PaymentStatus status, String bankName, String transactionId) {
		super(paymentId, amount, paymentDate, method, priceStrategy, status);
		this.bankName = bankName;
		this.transactionId = transactionId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		if (bankName != null && !bankName.trim().isEmpty() && transactionId != null
				&& !transactionId.trim().isEmpty()) {

			setStatus(PaymentStatus.SUCCESS);
			return true;
		}

		setStatus(PaymentStatus.FAILED);
		return false;
	}

}