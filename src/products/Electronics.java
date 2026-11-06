package products;

public class Electronics extends Product {

    private String brand;
    private int warrantyYears;

    // Parameterized constructor
    public Electronics(int productId, String name,
                       String description, double price,
                       int stock, String brand, int warrantyYears) {

        super(productId, name, description, price, stock);

        if (brand == null || brand.trim().isEmpty())
            throw new IllegalArgumentException("Brand cannot be empty");

        if (warrantyYears < 0)
            throw new IllegalArgumentException("Invalid warranty period");

        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    // Method overriding
    @Override
    public void displayDetails() {

        System.out.println("\n--- ELECTRONICS DETAILS ---");
        System.out.println("Product ID: " + getProductId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyYears + " years");
        System.out.println("Price: Rs. " + getPrice());
        System.out.println("Available Stock: " + getStock());
    }
}
