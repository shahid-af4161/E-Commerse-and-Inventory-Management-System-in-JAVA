package services;

import java.util.ArrayList;
import models.Customer;

public class CustomerService {
    private ArrayList<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    // Register customer
    public boolean registerCustomer(Customer customer) {

        if (findCustomerById(customer.getCustomerId()) != null ||
            findCustomerByEmail(customer.getEmail()) != null) {

            System.out.println("Customer ID or email already exists.");
            return false;
        }

        customers.add(customer);
        System.out.println("Customer registered successfully.");
        return true;
    }

    // Find customer by ID
    public Customer findCustomerById(int customerId) {
        for( Customer c: customers){
            if(c.getCustomerId() == customerId)
                return c;
        }
        return null;
    }

    // Find customer by email
    public Customer findCustomerByEmail(String email) {
        if (email == null || !email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Invalid email address.");
        
        for( Customer c: customers){
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    // Customer login
    public Customer login(String email, String password) {
        Customer customer = findCustomerByEmail(email);

        if (customer == null) {
            System.out.println("Invalid Email.");
            return null;
        }

        if (!customer.verifyPassword(password)) {
            System.out.println("Wrong Password.");
            return null;
        }

        System.out.println("Login successful.");
        return customer;
    }

    // Display all customers
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }

        for (Customer c : customers) {
            System.out.println("\n----- CUSTOMER DETAILS -----");
            System.out.println("Customer ID : " + c.getCustomerId());
            System.out.println("Name        : " + c.getName());
            System.out.println("Email       : " + c.getEmail());
            System.out.println("Phone       : " + c.getPhone());
            System.out.println("Address     : " + c.getAddress());
        }
    }
}
