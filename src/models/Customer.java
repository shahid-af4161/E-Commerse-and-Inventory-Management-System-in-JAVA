
package models;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    public Customer(int customerId, String name,
                    String email, String password) {

        this(customerId, name, email, password, "", "");
    }

    public Customer(int customerId, String name,
                    String email, String password,
                    String phone, String address) {

        if (customerId <= 0)
            throw new IllegalArgumentException("Invalid customer ID.");

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");

        if (email == null || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Invalid email address.");

        if (password == null || password.length() < 8)
            throw new IllegalArgumentException(
                "Password must contain at least 8 characters.");

        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Controlled update methods

    public void updateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");

        this.name = name;
    }

    public void updatePhone(String phone) {
        if (phone == null || !phone.matches("[0-9]{10}"))
            throw new IllegalArgumentException(
                "Phone number must contain exactly 10 digits.");

        this.phone = phone;
    }

    public void updateAddress(String address) {
        if (address == null || address.trim().isEmpty())
            throw new IllegalArgumentException("Address cannot be empty.");

        this.address = address;
    }

    public void updateEmail(String email) {
        if (email == null || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Invalid email address.");

        this.email = email;
    }

    public void updatePassword(String password) {
        if (password == null || password.length() < 8)
            throw new IllegalArgumentException(
                "Password must contain at least 8 characters.");

        this.password = password;
    }

    // Authentication helper
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
