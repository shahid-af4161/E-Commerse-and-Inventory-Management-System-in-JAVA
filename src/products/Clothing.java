
package products;

public class Clothing extends Product {

    private String size;
    private String fabric;

    public Clothing(int productId, String name,
                    String description, double price,
                    int stock, String size, String fabric) {

        super(productId, name, description, price, stock);

        if (size == null || size.trim().isEmpty())
            throw new IllegalArgumentException("Size cannot be empty");

        if (fabric == null || fabric.trim().isEmpty())
            throw new IllegalArgumentException("Fabric cannot be empty");

        this.size = size;
        this.fabric = fabric;
    }

    public String getSize() {
        return size;
    }

    public String getFabric() {
        return fabric;
    }

    // Method overriding
    @Override
    public void displayDetails() {
        System.out.println("\n--- CLOTHING DETAILS ---");
        System.out.println("Product ID: " + getProductId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Size: " + size);
        System.out.println("Fabric: " + fabric);
        System.out.println("Price: Rs. " + getPrice());
        System.out.println("Available Stock: " + getStock());
    }
}
