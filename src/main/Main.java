
package main;

import models.Customer;
import services.CustomerService;

public class Main {

    public static void main(String[] args) {

        CustomerService service = new CustomerService();

        System.out.println("===== CUSTOMER REGISTRATION =====");

        Customer c1 = new Customer(
            101, "Rahul Kumar",
            "rahul@gmail.com", "rahul1234",
            "9876543210", "Patna, Bihar"
        );

        Customer c2 = new Customer(
            102, "Aman Singh",
            "aman@gmail.com", "aman1234",
            "9876543211", "Gaya, Bihar"
        );

        System.out.println("Registration 1: "
                + service.registerCustomer(c1));

        System.out.println("Registration 2: "
                + service.registerCustomer(c2));

        System.out.println("\n===== DUPLICATE ID TEST =====");

        Customer duplicateId = new Customer(
            101, "Another User",
            "another@gmail.com", "another123",
            "9876543212", "Delhi"
        );

        System.out.println("Registration: "
                + service.registerCustomer(duplicateId));

        System.out.println("\n===== DUPLICATE EMAIL TEST =====");

        Customer duplicateEmail = new Customer(
            103, "Another User",
            "rahul@gmail.com", "another123",
            "9876543213", "Delhi"
        );

        System.out.println("Registration: "
                + service.registerCustomer(duplicateEmail));

        System.out.println("\n===== DISPLAY CUSTOMERS =====");

        service.displayAllCustomers();

        System.out.println("\n===== CUSTOMER SEARCH =====");

        Customer result = service.findCustomerById(101);

        if (result != null) {
            System.out.println("Found: " + result.getName());
        } else {
            System.out.println("Customer not found.");
        }

        System.out.println("\n===== SUCCESSFUL LOGIN =====");

        Customer loggedIn = service.login(
            "rahul@gmail.com", "rahul1234"
        );

        if (loggedIn != null) {
            System.out.println("Welcome, " + loggedIn.getName());
        }

        System.out.println("\n===== WRONG PASSWORD TEST =====");

        service.login("rahul@gmail.com", "wrongpassword");

        System.out.println("\n===== INVALID EMAIL TEST =====");

        service.login("unknown@gmail.com", "password123");
    }
}
