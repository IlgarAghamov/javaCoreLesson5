package org.example.onlineShop;

import org.example.onlineShop.exception.AmountException;
import org.example.onlineShop.exception.CustomerException;
import org.example.onlineShop.exception.ProductException;

import java.util.ArrayList;
import java.util.List;

class ShoppingManager {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    static {

        customers.add(new Customer("Kiril Melnikov", 30, "1234567890", Gender.MALE));
        customers.add(new Customer("Alisa Bogotova", 25, "9876543210", Gender.FEMALE));

        products.add(new Product("Laptop", 1000.0));
        products.add(new Product("Smartphone", 500.0));
        products.add(new Product("Headphones", 50.0));
        products.add(new Product("Tablet", 300.0));
        products.add(new Product("Mouse", 20.0));
    }

    public static void congratulateCustomers(Customer[] customers, Holiday holiday) {
        for (Customer customer : customers) {
            switch (holiday) {
                case NEW_YEAR:
                    System.out.println("Happy New Year, " + customer.getFullName() + "!");
                    break;
                case WOMENS_DAY:
                    if (customer.getGender() == Gender.FEMALE) {
                        System.out.println("Happy Women's Day, " + customer.getFullName() + "!");
                    }
                    break;
                case MENS_DAY:
                    if (customer.getGender() == Gender.MALE) {
                        System.out.println("Happy Men's Day, " + customer.getFullName() + "!");
                    }
                    break;

            }
        }
    }

    public static Order makePurchase(String customerName, String productName, int quantity) {
        try {
            Customer customer = findCustomerByName(customerName);
            Product product = findProductByName(productName);

            if (customer == null) {
                throw new CustomerException("Customer not found: " + customerName);
            }

            if (product == null) {
                throw new ProductException("Product not found: " + productName);
            }

            if (quantity < 0 || quantity > 100) {
                throw new AmountException("Invalid quantity: " + quantity);
            }


            Order order = new Order(customer, product, quantity);
            orders.add(order);

            return order;
        } catch (CustomerException | ProductException | AmountException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    private static Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getFullName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    private static Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        makePurchase("Kiril Melnikov", "Laptop", 2);
        makePurchase("Alisa Bogotova", "Smartphone", 1);
        makePurchase("Roma Bukin", "Tablet", 3);
        makePurchase("Alisa Bogotova", "Mouse", -1);

        System.out.println("Total purchases: " + orders.size());

        Customer[] customersArray = {
                new Customer("Kiril Melnikov", 30, "1234567890", Gender.MALE),
                new Customer("Alisa Bogotova", 25, "9876543210", Gender.FEMALE),
                new Customer("Roma Bukin", 40, "5555555555", Gender.MALE),
                new Customer("Eva Mursalova", 35, "6666666666", Gender.FEMALE)
        };

        congratulateCustomers(customersArray, Holiday.NEW_YEAR);


        congratulateCustomers(customersArray, Holiday.WOMENS_DAY);


        congratulateCustomers(customersArray, Holiday.MENS_DAY);
    }
}
