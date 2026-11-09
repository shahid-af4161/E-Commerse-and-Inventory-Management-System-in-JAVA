package models;

import products.Product;

public class OrderItem {
    private final int productId;
    private final String productName;
    private final double unitPrice;
    private final int quantity;

    public OrderItem(Product product, int quantity){
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");

        this.productId = product.getProductId();
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }
    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    // Calculate subtotal
    public double getSubtotal() {
        return unitPrice * quantity;
    }

    // Display order item
    public void displayOrderItem() {

        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Unit Price: Rs. " + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: Rs. " + getSubtotal());
    }
}
