
public class PaymentAdapter implements IPaymentGateway {
	private ExternalPaymentAPI api;

	public PaymentAdapter(ExternalPaymentAPI api) {
		this.api = api;
	}

	public ExternalPaymentAPI getApi() {
		return api;
	}

	public void setApi(ExternalPaymentAPI api) {
		this.api = api;
	}

	@Override
	public boolean pay(double amount) {
		return false;
	}
}