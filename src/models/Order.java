package models;

import enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int customerId;
    private ArrayList<OrderItem> items;
    private LocalDateTime orderDate;
    private double totalAmount;
    private OrderStatus status;

    public Order(int orderId, int customerId, ArrayList<OrderItem> items) {
        if (orderId <= 0)
            throw new IllegalArgumentException("Invalid order ID.");

        if (customerId <= 0)
            throw new IllegalArgumentException("Invalid customer ID.");

        if (items == null || items.isEmpty())
            throw new IllegalArgumentException("Order cannot be empty.");

        this.orderId = orderId;
        this.customerId = customerId;
        this.items = new ArrayList<>(items);

        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;

        calculateTotal();
    }

    private void calculateTotal(){
        totalAmount = 0;
        for (OrderItem item : items) {
            totalAmount += item.getSubtotal();
        }
    }

    public int getOrderId() {
    return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null)
        throw new IllegalArgumentException("Invalid order status.");

        boolean validTransition = false;

        switch(status) {
            case PENDING:
                validTransition = newStatus == OrderStatus.CONFIRMED ||
                                newStatus == OrderStatus.CANCELLED;
                break;

            case CONFIRMED:
                validTransition = newStatus == OrderStatus.SHIPPED ||
                                newStatus == OrderStatus.CANCELLED;
                break;

            case SHIPPED:
                validTransition = newStatus == OrderStatus.DELIVERED;
                break;

            case DELIVERED:
            case CANCELLED:
                validTransition = false;
                break;
    }

    if (!validTransition)
        throw new IllegalStateException(
            "Invalid order status transition: " + status + " to " + newStatus
        );

    this.status = newStatus;
    }

    public boolean canBeCancelled() {
        return status == OrderStatus.PENDING || status == OrderStatus.CONFIRMED;
    }

    public void displayOrder() {

        System.out.println("\n========== ORDER DETAILS ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Order Status: " + status);

        System.out.println("\n---------- ORDER ITEMS ----------");

        for (OrderItem item : items) {
            item.displayOrderItem();
            System.out.println("-------------------------------");
        }

        System.out.println("Total Amount: Rs. " + totalAmount);
        System.out.println("===================================");
    }
}
