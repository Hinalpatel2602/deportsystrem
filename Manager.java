package src;

import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Depot System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Add Parcel");
            System.out.println("4. Find Parcel by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Details: ");
                    String contact = scanner.nextLine();
                    queueOfCustomers.addCustomer(new Customer(customerId, name, contact));
                }
                case 2 -> {
                    queueOfCustomers.removeCustomer();
                }
                case 3 -> {
                    System.out.print("Enter Parcel ID: ");
                    String parcelId = scanner.nextLine();
                    System.out.print("Enter Parcel Weight: ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Parcel Destination: ");
                    String destination = scanner.nextLine();
                    parcelMap.addParcel(new Parcel(parcelId, weight, destination));
                }
                case 4 -> {
                    System.out.print("Enter Parcel ID to Find: ");
                    String parcelId = scanner.nextLine();
                    Parcel parcel = parcelMap.getParcel(parcelId);
                    if (parcel != null) {
                        System.out.println(parcel.getDetails());
                    } else {
                        System.out.println("Parcel not found.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
