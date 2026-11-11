package payments;

public class CardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card payment...");
        System.out.println("Amount: Rs. " + amount);

        // Simulated transaction
        return true;
    }
}
