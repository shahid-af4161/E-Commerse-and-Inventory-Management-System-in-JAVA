package services;

import cart.ShoppingCart;
import enums.OrderStatus;
import inventory.InventoryManager;
import java.util.ArrayList;
import models.CartItem;
import models.Order;
import models.OrderItem;
import products.Product;
  
public class OrderService {
    private ArrayList<Order> orders;
    private InventoryManager inventory;

    public OrderService(InventoryManager inventory) {
        if (inventory == null)
            throw new IllegalArgumentException("Inventory cannot be null.");

        this.orders = new ArrayList<>();
        this.inventory = inventory;
    }

    public Order createOrder(int orderId, int customerId, ShoppingCart cart){
        if (findOrderById(orderId) != null)
            throw new IllegalArgumentException("Order ID already exists.");
        if(cart == null || cart.isEmpty())
            throw new IllegalArgumentException("Shopping cart is empty.");

        ArrayList<CartItem> cartItems = cart.getItems();

        // Step 1: Validate stock and prepare order items
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cartItems){
            Product product = inventory.findProduct(cartItem.getProduct().getProductId());

            if(product == null)
                throw new IllegalStateException("Product no longer exists.");

            if(cartItem.getQuantity() > product.getStock())
                throw new IllegalStateException(
                    "Insufficient stock for: " + product.getName()
                );
            
            orderItems.add(new OrderItem(product, cartItem.getQuantity()));

        }

        //step 2: create Order
        Order order = new Order(orderId, customerId, orderItems);

        //step 3: Deduct stock
        for(CartItem cartItem : cartItems){
            Product product = inventory.findProduct(cartItem.getProduct().getProductId());

            product.decreaseStock(cartItem.getQuantity());
        }

        //step 4: Store order
        orders.add(order);
        // Step 5: Clear cart
        cart.clearCart();

        return order;

    }

    public Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId)
                return order;
        }

        return null;
    }

    public void displayCustomerOrders(int customerId) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getCustomerId() == customerId) {
                order.displayOrder();
                found = true;
            }
        }
        if (!found)
            System.out.println("No orders found for this customer.");
    }

    public boolean cancelOrder(int orderId){
        Order order = findOrderById(orderId);

        if(order == null){
            System.out.println("Order not found.");
            return false;
        }

        if(!order.canBeCancelled()){
            System.out.println("This order cannot be cancelled.");
            return false;
        }
        // Validate that all products still exist
        for(OrderItem item : order.getItems()){
            Product product = inventory.findProduct(item.getProductId());

            if (product == null) {
                throw new IllegalStateException(
                    "Cannot restore stock. Product ID "
                    + item.getProductId() + " not found."
                );
            }
        }
        // Restore stock
        for (OrderItem item : order.getItems()) {
            Product product = inventory.findProduct(item.getProductId());
            product.increaseStock(item.getQuantity());
        }   

        order.updateStatus(OrderStatus.CANCELLED);
        System.out.println("Order cancelled successfully.");
        return true;
    }

    public boolean updateOrderStatus(int orderId, OrderStatus newStatus){
        Order order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return false;
        }

        try {
            order.updateStatus(newStatus);
            System.out.println("Order status updated successfully.");
            return true;
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Status update failed: " + e.getMessage());
            return false;
        }
    }


    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            order.displayOrder();
        }
    }
}

