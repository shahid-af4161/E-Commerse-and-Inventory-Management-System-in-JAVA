package products;

public class Book extends Product {
    private String author;
    private String publisher;

    public Book(int productId, String name,
                String description, double price,
                int stock, String author, String publisher) {

        super(productId, name, description, price, stock);

        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        if (publisher == null || publisher.trim().isEmpty())
            throw new IllegalArgumentException("Publisher cannot be empty");

        this.author = author;
        this.publisher = publisher;
    }

    // Getters :retrieves the value of a private variable.
    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    // Method overriding
    @Override
    public void displayDetails() {

        System.out.println("\n--- BOOK DETAILS ---");
        System.out.println("Product ID: " + getProductId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Price: Rs. " + getPrice());
        System.out.println("Available Stock: " + getStock());
    }
}
