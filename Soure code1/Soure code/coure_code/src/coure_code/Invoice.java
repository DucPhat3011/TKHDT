package coure_code;

import java.util.Date;

public class Invoice {
    private int invoiceId;
    private double totalAmount;
    private StayRecord stayRecord;
    private Promotion promotion;
    private double roomFee;
    private double serviceFee;
    private Date createdAt;
    private double tax;
    private InvoiceStatus status;
    
    public Invoice(int invoiceId, double totalAmount, StayRecord stayRecord, Promotion promotion, double roomFee,
			double serviceFee, Date createdAt, double tax, InvoiceStatus status) {
		super();
		this.invoiceId = invoiceId;
		this.totalAmount = totalAmount;
		this.stayRecord = stayRecord;
		this.promotion = promotion;
		this.roomFee = roomFee;
		this.serviceFee = serviceFee;
		this.createdAt = createdAt;
		this.tax = tax;
		this.status = status;
	}

	public double calculateGrandTotal() {
        double subTotal = roomFee + serviceFee;
        double discount = (promotion != null) ? (subTotal * promotion.getDiscountPercent() / 100) : 0;
        this.totalAmount = subTotal + tax - discount;
        return this.totalAmount;
    }

    public void printInvoice() {
        System.out.println("--- HÓA ĐƠN KHÁCH SẠN ---");
        System.out.println("Mã hóa đơn: " + invoiceId);
        System.out.println("Ngày tạo: " + createdAt);
        System.out.println("Tiền phòng: " + roomFee);
        System.out.println("Phí dịch vụ: " + serviceFee);
        System.out.println("Thuế: " + tax);
        System.out.println("TỔNG CỘNG: " + totalAmount);
        System.out.println("Trạng thái: " + status);
    }

    public void applyPromotion(Promotion promo) {
        if (promo != null && promo.isValid()) {
            this.promotion = promo;
            System.out.println("Đã áp dụng mã giảm giá: " + promo.getPromoCode());
            calculateGrandTotal();
        } else {
            System.out.println("Mã giảm giá không hợp lệ hoặc đã hết hạn.");
        }
    }
}
