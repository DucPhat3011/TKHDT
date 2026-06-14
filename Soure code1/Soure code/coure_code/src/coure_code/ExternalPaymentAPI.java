
public class ExternalPaymentAPI {
	public String executePayment(double total) {
		if (total > 0) {
			return "SUCCESS";
		}
		return "FAILED";
	}
}
