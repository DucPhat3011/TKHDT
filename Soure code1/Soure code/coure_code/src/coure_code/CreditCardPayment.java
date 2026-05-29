package coure_code;

import java.util.Date;

public class CreditCardPayment extends Payment {
	private String cardHolderName;
	private String last4Digits;
	private Date expiryDate;

	public CreditCardPayment(String cardHolderName, String last4Digits, Date expiryDate) {
		this.cardHolderName = cardHolderName;
		this.last4Digits = last4Digits;
		this.expiryDate = expiryDate;
	}

	@Override
	public boolean processPayment(double amount) {
		Date today = new Date();
		if (expiryDate != null && expiryDate.after(today)) {
			System.out.println("Đang xử lý thanh toán thẻ tín dụng...");
			System.out.println("Chủ thẻ: " + cardHolderName);
			System.out.println("Số tiền: " + amount);
			return true;
		} else {
			System.out.println("Thẻ đã hết hạn hoặc thông tin không hợp lệ!");
			return false;
		}
	}
}
