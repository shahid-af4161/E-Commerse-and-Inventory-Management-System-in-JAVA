package payments;

public interface PaymentMethod {
    boolean processPayment(double amount);
}