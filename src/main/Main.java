package main;

import inventory.InventoryManager;
import products.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("===== ADDING PRODUCTS =====");
        InventoryManager inventory = new InventoryManager();
        
         Product p1 = new Electronics(
            101, "Laptop", "HP Laptop",
            55000, 10, "HP", 2
        );

        Product p2 = new Clothing(
            102, "T-Shirt", "Cotton T-Shirt",
            500, 3, "L", "Cotton"
        );

        Product p3 = new Book(
            103, "Java Programming", "Java Guide",
            650, 2, "Herbert Schildt", "McGraw Hill"
        );

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);


        inventory.addProduct(p1);

        System.out.println("\n===== ALL PRODUCTS =====");
        inventory.displayAllProducts();

        System.out.println("\n===== SEARCH PRODUCT =====");

        Product result = inventory.findProduct(102);

        if (result != null) {
            result.displayDetails();
        } else {
            System.out.println("Product not found.");
        }

        System.out.println("\n===== SEARCH INVALID ID =====");

        if (inventory.findProduct(999) == null) {
            System.out.println("Product not found.");
        }

        System.out.println("\n===== RESTOCKING =====");

        inventory.restockProduct(102, 10);

        System.out.println("\n===== LOW STOCK PRODUCTS =====");

        inventory.displayLowStockProducts(5);

        System.out.println("\n===== REMOVING PRODUCT =====");

        boolean removed = inventory.removeProduct(103);

        System.out.println("Removal successful: " + removed);

        System.out.println("\n===== FINAL INVENTORY =====");

        inventory.displayAllProducts();
    }
}
