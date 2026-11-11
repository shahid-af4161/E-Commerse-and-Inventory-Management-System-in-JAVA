
package main;

import enums.PaymentType;
import models.Payment;

public class Main {

    public static void main(String[] args) {

        Payment payment = new Payment(
            501,
            1001,
            55000,
            PaymentType.UPI
        );

        payment.displayPayment();

        payment.markSuccessful();

        System.out.println("\nAfter successful payment:");
        payment.displayPayment();

        payment.markRefunded();

        System.out.println("\nAfter refund:");
        payment.displayPayment();
    }
}
