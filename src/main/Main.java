package main;

import products.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    PRODUCT HIERARCHY TEST");
        System.out.println("==================================");

        Product[] products = new Product[3];
         products[0] = new Electronics(
            101, "Laptop", "HP Laptop",
            55000, 10, "HP", 2
        );

        products[1] = new Clothing(
            102, "T-Shirt", "Cotton T-Shirt",
            500, 30, "L", "Cotton"
        );

        products[2] = new Book(
            103, "Java Programming",
            "Complete Java Guide", 650,
            15, "Herbert Schildt", "McGraw Hill"
        );

        for( Product product : products){
            product.displayDetails();
        }

        // Testing encapsulation
        System.out.println("\n--- ENCAPSULATION TEST ---");

        System.out.println("Laptop Price : "+ products[0].getPrice());
        products[0].updatePrice(60000);

        products[0].increaseStock(5);
        System.out.println("Updated Stock : "+ products[0].getStock());

        products[0].decreaseStock(2);
        System.out.println("Stock after purchase : "+products[0].getStock());
    }
}
