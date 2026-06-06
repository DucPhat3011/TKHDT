package coure_code;

import java.util.Date;

public class Payment {
	private int paymentId;
	private double amount;
	private Date paymentDate;
	private PaymentMethod method; 
	private IPriceStrategy priceStrategy; 

	public Payment() {
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.method = method;
		this.priceStrategy = priceStrategy;
	}

	public boolean processPayment(double amount) {
		System.out.println("Processing payment of " + amount + " via " + method);
		return true;
	}

    public double getAmount() {
        return amount;
    }
}
