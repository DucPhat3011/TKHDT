import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {
	private int invoiceId;
	private StayRecord stayRecord;
	private double roomFee;
	private double serviceFee;
	private double subtotal;
	private double discount;
	private double tax;
	private double totalAmount;
	private Promotion appliedPromotion;
	private Date paidDate;
	private InvoiceStatus status;

	public Invoice(int invoiceId, StayRecord stayRecord, double roomFee, double serviceFee, double subtotal,
			double discount, double tax, double totalAmount, Promotion appliedPromotion, Date paidDate,
			InvoiceStatus status) {
		this.invoiceId = invoiceId;
		this.stayRecord = stayRecord;
		this.roomFee = roomFee;
		this.serviceFee = serviceFee;
		this.subtotal = subtotal;
		this.discount = discount;
		this.tax = tax;
		this.totalAmount = totalAmount;
		this.appliedPromotion = appliedPromotion;
		this.paidDate = paidDate;
		this.status = status;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public StayRecord getStayRecord() {
		return stayRecord;
	}

	public void setStayRecord(StayRecord stayRecord) {
		this.stayRecord = stayRecord;
	}

	public double getRoomFee() {
		return roomFee;
	}

	public void setRoomFee(double roomFee) {
		this.roomFee = roomFee;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Promotion getAppliedPromotion() {
		return appliedPromotion;
	}

	public void setAppliedPromotion(Promotion appliedPromotion) {
		this.appliedPromotion = appliedPromotion;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	// Tinh tien tam tinh (tien phong + tien dich vu)
	public double calculateSubTotal() {
		subtotal = roomFee + serviceFee;
		return subtotal;
	}

	// Tinh thue VAT (10%)
	public double calculateTax() {
		tax = subtotal * 0.1;

		return tax;
	}

	// Ap dung ma khuyen mai
	public void applyPromotion(Promotion promo) {
		calculateSubTotal();

		if (promo != null && promo.isValid()) {

			this.appliedPromotion = promo;

			discount = subtotal * (promo.getDiscountPercent() / 100);
		}
	}

	// Tinh tong thanh toan cuoi cung
	public double calculateFinalAmount() {
		calculateSubTotal();
		calculateTax();
		totalAmount = subtotal + tax - discount;
		return totalAmount;
	}

	// Tra ve chuoi hoa don da dinh dang
	public String getFormattedInvoice() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    String formattedDate = (paidDate != null) ? sdf.format(paidDate) : "---";
	    
	    return "===== HÓA ĐƠN THANH TOÁN =====" +
	           "\nMã hóa đơn: " + invoiceId +
	           "\nTiền phòng: " + formatMoney(roomFee) +
	           "\nTiền dịch vụ: " + formatMoney(serviceFee) +
	           "\nTạm tính: " + formatMoney(subtotal) +
	           "\nGiảm giá: " + formatMoney(discount) +
	           "\nThuế VAT: " + formatMoney(tax) +
	           "\nTổng thanh toán: " + formatMoney(totalAmount) +
	           "\nMã khuyến mãi: " + (appliedPromotion != null ? appliedPromotion.getPromoCode() : "Không có") +
	           "\nNgày thanh toán: " + formattedDate +
	           "\nTrạng thái: " + status;
	}
	
	// Dinh dang tien te
	private String formatMoney(double amount) {
	    return String.format("%,.0f VND", amount);
	}

	// Tao file PDF hoa don
	public File generatePDF() {
		return new File("Invoice_" + invoiceId + ".pdf");
	}
}
