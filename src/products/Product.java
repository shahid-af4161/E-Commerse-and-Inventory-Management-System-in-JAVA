package products;

public abstract class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stock;

    // Constructor 1
    public Product(int productId, String name,
                   String description, double price) {

        this(productId, name, description, price, 0);
    }

    // Constructor 2
    public Product(int productId, String name,
                   String description, double price, int stock) {

        if (productId <= 0)
            throw new IllegalArgumentException("Invalid product ID");

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");

        if (price <= 0)
            throw new IllegalArgumentException("Price must be positive");

        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative");

        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Abstract method
    public abstract void displayDetails();

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


    // Setter : modifies the value of a private variable.
    // Update price
    public void updatePrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Invalid price");

        this.price = price;
    }

    // Increase stock
    public void increaseStock(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid quantity");

        stock += quantity;
    }

    // Decrease stock
    public void decreaseStock(int quantity) {
        if (quantity <= 0 || quantity > stock)
            throw new IllegalArgumentException("Invalid stock reduction");

        stock -= quantity;
    }
}
