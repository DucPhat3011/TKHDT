package coure_code;

public class CashPayment extends Payment {
	private double cashReceived;
    private double changeGiven;

    public CashPayment(double cashReceived, double changeGiven) {
		super();
		this.cashReceived = cashReceived;
		this.changeGiven = changeGiven;
	}

	@Override
    public boolean processPayment(double amount) {
        // amount là số tiền cần thanh toán (tổng bill)
        if (cashReceived >= amount) {
            this.changeGiven = cashReceived - amount;
            System.out.println("Thanh toán tiền mặt thành công!");
            System.out.println("Tiền thối lại: " + this.changeGiven);
            return true;
        } else {
            System.out.println("Thanh toán thất bại: Tiền khách đưa không đủ!");
            return false;
        }
    }
}
