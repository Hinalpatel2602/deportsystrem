package  src;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private static List<Customer> customers = new ArrayList<>();
	private String id;
    private String name;
    private String contactDetails;

    public Customer(String  id, String name, String contactDetails) {
        this.id = id;
        this.name = name;
        this.contactDetails = contactDetails;
        customers.add(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public String getDetails() {
        return "Customer ID: " + id + ", Name: " + name + ", Contact: " + contactDetails;
    }

	public static int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void addCustomer(Customer newCustomer) {
		// TODO Auto-generated method stub
		
	}

	public static Customer removeCustomer() {
		// TODO Auto-generated method stub
		return null;
	}
	  public static List<Customer> getCustomers() {
	        return customers; // Return the list of customers
	    }

}

