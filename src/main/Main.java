
package main;

import cart.ShoppingCart;
import enums.OrderStatus;
import enums.PaymentType;
import inventory.InventoryManager;
import models.Payment;
import payments.CashOnDelivery;
import products.Electronics;
import products.Product;
import services.OrderService;
import services.PaymentService;

public class Main {

    public static void main(String[] args) {

        // 1. Initialize services
        InventoryManager inventory = new InventoryManager();
        OrderService orderService = new OrderService(inventory);
        PaymentService paymentService = new PaymentService(orderService);

        // 2. Add products to inventory
        Product laptop = new Electronics(
            101,
            "Laptop",
            "HP Laptop",
            55000,
            10,
            "HP",
            2
        );
        inventory.addProduct(laptop);

        // 3. Create shopping cart
        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(laptop, 2);

        // 4. Create order
        orderService.createOrder(
            1001,
            501,
            cart
        );

        // 5. Process COD payment
        Payment payment = paymentService.processPayment(
            501,
            1001,
            PaymentType.CASH_ON_DELIVERY,
            new CashOnDelivery()
        );

        // 6. Check payment status
        payment.displayPayment();

        // 7. Ship order
        orderService.updateOrderStatus(
            1001,
            OrderStatus.SHIPPED
        );

        // 8. Deliver order
        orderService.updateOrderStatus(
            1001,
            OrderStatus.DELIVERED
        );

        // 9. Collect COD payment
        paymentService.collectCODPayment(1001);

        // 10. Display final payment status
        payment.displayPayment();
    }
}
