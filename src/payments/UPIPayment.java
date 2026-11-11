package payments;

public class UPIPayment implements PaymentMethod{
    @Override 
        public boolean processPayment(double amount) {

        System.out.println("Processing UPI payment...");
        System.out.println("Amount: Rs. " + amount);

        // Simulated transaction
        return true;
    }

}
