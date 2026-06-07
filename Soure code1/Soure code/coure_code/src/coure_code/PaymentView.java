import javax.swing.JFrame;

public class PaymentView extends JFrame {
	private PaymentController paymentController;
    private String selectedMethod;

    public PaymentController getPaymentController() {
        return paymentController;
    }

    public void setPaymentController(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    public String getSelectedMethod() {
        return selectedMethod;
    }

    public void setSelectedMethod(String selectedMethod) {
        this.selectedMethod = selectedMethod;
    }
    
    public void renderPaymentForm() {
    }

    public void onConfirmPayment() {
    }

}
