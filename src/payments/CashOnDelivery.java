package payments;

public class CashOnDelivery implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.println("Cash on Delivery selected.");
        System.out.println("Amount payable on delivery: Rs. " + amount);

        // COD is not collected at order placement.
        return true;
    }
}
