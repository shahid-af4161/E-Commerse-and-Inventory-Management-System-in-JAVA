package cart;

import java.util.ArrayList;
import models.CartItem;
import products.Product;

public class ShoppingCart {
    private ArrayList<CartItem> items;

    public ShoppingCart(){
        items = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity){
        if(product == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");

        if(quantity > product.getStock())
            throw new IllegalArgumentException("Insufficient stock.");

          // Check whether product already exists in cart
        for( CartItem item : items){
            if(item.getProduct().getProductId() == product.getProductId()){
                int newQuantity = item.getQuantity() + quantity;

                if(newQuantity > product.getStock())
                     throw new IllegalArgumentException("Insufficient stock.");
                
                item.updateQuantity(newQuantity);

                System.out.println("Cart quantity updated.");
                return;
            }
        }

        items.add(new CartItem(product, quantity));
        System.out.println("Product added to cart successfully.");
    }

    // Remove product from cart
    public boolean removeFromCart(int productId){
        java.util.Iterator<CartItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            CartItem item = iterator.next();

            if (item.getProduct().getProductId() == productId) {
                iterator.remove();
                System.out.println("Product removed from cart.");
                return true;
            }
        }
        System.out.println("Product not found in cart.");
        return false;
    }

    //Update cart item quantity
    public void updateCartItem(int productId, int quantity){
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive.");

        for( CartItem item : items){

            if(item.getProduct().getProductId() == productId){
                if (quantity > item.getProduct().getStock())
                    throw new IllegalArgumentException("Insufficient stock.");

                item.updateQuantity(quantity);
                System.out.println("Cart updated successfully.");
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

     // Display cart
    public void displayCart() {

        if (items.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }

        System.out.println("\n========== SHOPPING CART ==========");

        for (CartItem item : items) {

            Product product = item.getProduct();

            System.out.println("\nProduct ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: Rs. " + product.getPrice());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Subtotal: Rs. " + item.getSubtotal());
        }

        System.out.println("\nTotal: Rs. " + calculateTotal());
        System.out.println("===================================");
    }
    
    // Calculate total cart value

    public double calculateTotal(){

        double total = 0;

        for(CartItem item : items){
            total += item.getSubtotal();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
        System.out.println("Shopping cart cleared.");
    }

    // Check whether cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int getItemCount() {
        return items.size();
    }
}
