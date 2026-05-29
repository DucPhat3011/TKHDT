package coure_code;

import java.util.Date;

public class BankTransferPayment extends Payment {
    public BankTransferPayment(int paymentId, double amount, Date paymentDate, PaymentMethod method,
			IPriceStrategy priceStrategy) {
		super();
		// TODO Auto-generated constructor stub
	}

	private String bankName;
    private String transactionId;

    public boolean processPayment(double amount) {
        return true;
    }
}
