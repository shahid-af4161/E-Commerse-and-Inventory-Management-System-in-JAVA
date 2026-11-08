
package main;

import cart.ShoppingCart;
import products.*;

public class Main {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        Product laptop = new Electronics(
            101, "Laptop", "HP Laptop",
            55000, 10, "HP", 2
        );

        Product tshirt = new Clothing(
            102, "T-Shirt", "Cotton T-Shirt",
            500, 20, "L", "Cotton"
        );

        Product book = new Book(
            103, "Java Programming", "Java Guide",
            650, 15, "Herbert Schildt", "McGraw Hill"
        );

        System.out.println("===== ADD PRODUCTS =====");

        cart.addToCart(laptop, 2);
        cart.addToCart(tshirt, 3);
        cart.addToCart(book, 1);

        System.out.println("\n===== ADD DUPLICATE PRODUCT =====");

        cart.addToCart(laptop, 1);

        System.out.println("\n===== DISPLAY CART =====");

        cart.displayCart();

        System.out.println("\n===== ITEM COUNT =====");

        System.out.println("Distinct products: " + cart.getItemCount());

        System.out.println("\n===== UPDATE QUANTITY =====");

        cart.updateCartItem(102, 5);

        System.out.println("\n===== REMOVE PRODUCT =====");

        cart.removeFromCart(103);

        System.out.println("\n===== FINAL CART =====");

        cart.displayCart();

        System.out.println("\n===== CLEAR CART =====");

        cart.clearCart();

        System.out.println("Is cart empty? " + cart.isEmpty());
    }
}
