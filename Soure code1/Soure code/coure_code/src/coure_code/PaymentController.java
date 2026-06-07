
public class PaymentController {
	private Payment payment;

	public PaymentController(Payment payment) {
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void processWebPayment(int invoiceId, double amount, String method) {
	}

}
