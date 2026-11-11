
package services;

import enums.OrderStatus;
import enums.PaymentType;
import java.util.ArrayList;
import models.Order;
import models.Payment;
import payments.CardPayment;
import payments.CashOnDelivery;
import payments.PaymentMethod;
import payments.UPIPayment;

public class PaymentService {
    private ArrayList<Payment> payments;
    private OrderService orderService;

    public PaymentService(OrderService orderService) {
        if (orderService == null)
            throw new IllegalArgumentException(
                "OrderService cannot be null."
            );

        this.orderService = orderService;
        this.payments = new ArrayList<>();
    }

    public Payment processPayment(
            int paymentId,
            int orderId,
            PaymentType paymentType,
            PaymentMethod paymentMethod) {

        // Step 1: Validate payment method
        if (paymentType == null || paymentMethod == null)
            throw new IllegalArgumentException(
                "Invalid payment method."
            );

        // Step 2: Validate payment type and implementation
        if (paymentType == PaymentType.UPI &&
            !(paymentMethod instanceof UPIPayment)) {

            throw new IllegalArgumentException(
                "Payment type does not match payment method."
            );
        }

        if (paymentType == PaymentType.CARD &&
            !(paymentMethod instanceof CardPayment)) {

            throw new IllegalArgumentException(
                "Payment type does not match payment method."
            );
        }

        if (paymentType == PaymentType.CASH_ON_DELIVERY &&
            !(paymentMethod instanceof CashOnDelivery)) {

            throw new IllegalArgumentException(
                "Payment type does not match payment method."
            );
        }

        // Step 3: Check duplicate payment ID
        if (paymentId <= 0)
            throw new IllegalArgumentException(
                "Invalid payment ID."
            );

        if (findPaymentById(paymentId) != null)
            throw new IllegalArgumentException(
                "Payment ID already exists."
            );

        // Step 4: Find order
        Order order = orderService.findOrderById(orderId);
        if (order == null)
            throw new IllegalArgumentException(
                "Order not found."
            );

        if (order.getStatus() != OrderStatus.PENDING)
            throw new IllegalStateException(
                "Payment can only be processed for pending orders."
            );

        // Step 5: Create payment record
        Payment payment = new Payment(
            paymentId,
            orderId,
            order.getTotalAmount(),
            paymentType
        );
        payments.add(payment);

        // Step 6: Handle Cash on Delivery
        if (paymentType == PaymentType.CASH_ON_DELIVERY) {

            if (!orderService.updateOrderStatus(
                    orderId, OrderStatus.CONFIRMED)) {

                throw new IllegalStateException(
                    "Unable to confirm COD order."
                );
            }

            // Payment remains PENDING
            System.out.println(
                "Order confirmed. Payment will be collected on delivery."
            );

            return payment;
        }

        // Step 7: Process online payment
        boolean success = paymentMethod.processPayment(
            payment.getAmount()
        );
        if (success) {
            // Mark payment successful
            payment.markSuccessful();
            // Confirm order
            if (!orderService.updateOrderStatus(
                    orderId, OrderStatus.CONFIRMED)) {

                throw new IllegalStateException(
                    "Payment succeeded, but order confirmation failed."
                );
            }
            System.out.println("Payment successful.");
            System.out.println("Order confirmed.");

        } else {
            // Mark payment failed
            payment.markFailed();
            // Cancel order and restore inventory
            boolean cancelled = orderService.cancelOrder(orderId);
            if (!cancelled) {
                throw new IllegalStateException(
                    "Payment failed, but order cancellation failed."
                );
            }
            System.out.println("Payment failed.");
            System.out.println(
                "Order cancelled and stock restored."
            );
        }
        return payment;
    }

    // Find payment using payment ID
    public Payment findPaymentById(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId)
                return payment;
        }
        return null;
    }

    // Display all payment records
    public void displayAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payment records available.");
            return;
        }
        for (Payment payment : payments) {
            payment.displayPayment();
        }
    }

    // Display payments associated with an order
    public void displayOrderPayments(int orderId) {
        boolean found = false;
        for (Payment payment : payments) {
            if (payment.getOrderId() == orderId) {
                payment.displayPayment();
                found = true;
            }
        }

        if (!found)
            System.out.println("No payment records found.");
    }
}
