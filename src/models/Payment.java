package models;

import enums.PaymentStatus;
import enums.PaymentType;
import java.time.LocalDateTime;

public class Payment {
    private final int paymentId;
    private final int orderId;
    private final double amount;
    private final PaymentType paymentType;
    private final LocalDateTime paymentDate;

    private PaymentStatus status;

    public Payment(int paymentId, int orderId,
                   double amount, PaymentType paymentType) {

        if (paymentId <= 0)
            throw new IllegalArgumentException("Invalid payment ID.");

        if (orderId <= 0)
            throw new IllegalArgumentException("Invalid order ID.");

        if (amount <= 0)
            throw new IllegalArgumentException("Payment amount must be positive.");

        if (paymentType == null)
            throw new IllegalArgumentException("Payment type cannot be null.");

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentDate = LocalDateTime.now();

        // Every payment starts as pending.
        this.status = PaymentStatus.PENDING;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void markSuccessful() {
        if (status != PaymentStatus.PENDING)
            throw new IllegalStateException(
                "Only pending payments can be marked successful."
            );

        status = PaymentStatus.SUCCESS;
    }

    public void markFailed() {
        if (status != PaymentStatus.PENDING)
            throw new IllegalStateException(
                "Only pending payments can be marked failed."
            );
        status = PaymentStatus.FAILED;
    }

    public void markRefunded() {

        if (status != PaymentStatus.SUCCESS)
            throw new IllegalStateException(
                "Only successful payments can be refunded."
            );

        status = PaymentStatus.REFUNDED;
    }

    public void displayPayment() {

        System.out.println("\n===== PAYMENT DETAILS =====");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Order ID: " + orderId);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Payment Method: " + paymentType);
        System.out.println("Payment Status: " + status);
        System.out.println("Payment Date: " + paymentDate);
    }
}
