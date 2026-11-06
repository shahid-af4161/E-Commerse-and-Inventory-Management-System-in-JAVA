package inventory;

import java.util.ArrayList;
import products.Product;

public class InventoryManager {
    private ArrayList<Product> products;

    public InventoryManager() {
        products = new ArrayList<>();
    }

    // add product
    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductId() == product.getProductId()) {
                System.out.println("Product ID already exists.");
                return;
            }
        }
        products.add(product);
        System.out.println("Product added successfully.");
    }

    // find product
    public Product findProduct(int productId) {
        for (Product p : products) {
            if (p.getProductId() == productId)
                return p;
        }
        return null;
    }

    // display products
    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("The Inventory is empty.");
            return;
        }

        for (Product p : products) {
            p.displayDetails();
        }
    }

    // remove product
    public boolean removeProduct(int productId) {
        Product product = findProduct(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }
        products.remove(product);
        System.out.println("Product removed successfully.");
        return true;
    }

    public void restockProduct(int productId, int quantity) {
        Product product = findProduct(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        product.increaseStock(quantity);
        System.out.println("Stock increased successfully.");
    }

    public void displayLowStockProducts(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold cannot be negative.");
        }
        
        boolean found = false;
        for (Product p : products) {
            if (p.getStock() <= threshold) {
                p.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No low-stock products found.");
        }
    }
}
