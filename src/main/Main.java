
package main;

import products.*;
import inventory.InventoryManager;
import cart.ShoppingCart;
import services.OrderService;
import models.Order;
import enums.OrderStatus;

public class Main {

    public static void main(String[] args) {

        InventoryManager inventory = new InventoryManager();

        OrderService orderService = new OrderService(inventory);

        ShoppingCart cart = new ShoppingCart();

        // Create products
        Product laptop = new Electronics(
            101, "Laptop", "HP Laptop",
            55000, 10, "HP", 2
        );

        Product book = new Book(
            102, "Java Programming", "Java Guide",
            650, 15, "Herbert Schildt", "McGraw Hill"
        );

        inventory.addProduct(laptop);
        inventory.addProduct(book);

        System.out.println("\n===== INITIAL STOCK =====");
        inventory.displayAllProducts();

        // Add products to cart
        System.out.println("\n===== ADD TO CART =====");

        cart.addToCart(laptop, 2);
        cart.addToCart(book, 3);

        // Create order
        System.out.println("\n===== CREATE ORDER =====");

        Order order = orderService.createOrder(1001, 501, cart);

        order.displayOrder();

        // Verify stock deduction
        System.out.println("\n===== STOCK AFTER ORDER =====");
        inventory.displayAllProducts();

        // Find order
        System.out.println("\n===== FIND ORDER =====");

        Order found = orderService.findOrderById(1001);

        if (found != null)
            System.out.println("Order found: " + found.getOrderId());

        // Customer order history
        System.out.println("\n===== CUSTOMER ORDER HISTORY =====");

        orderService.displayCustomerOrders(501);

        // Update order status
        System.out.println("\n===== UPDATE STATUS =====");

        orderService.updateOrderStatus(
            1001, OrderStatus.CONFIRMED
        );

        // Cancel order
        System.out.println("\n===== CANCEL ORDER =====");

        orderService.cancelOrder(1001);

        // Verify stock restoration
        System.out.println("\n===== STOCK AFTER CANCELLATION =====");
        inventory.displayAllProducts();

        // Try cancelling again
        System.out.println("\n===== REPEATED CANCELLATION =====");

        orderService.cancelOrder(1001);

        // Display all orders
        System.out.println("\n===== ALL ORDERS =====");

        orderService.displayAllOrders();
    }
}
