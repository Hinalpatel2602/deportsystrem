package src;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    public Customer removeCustomer() {
        Customer customer = customerQueue.poll();
        if (customer != null) {
            System.out.println("Customer removed: " + customer.getName());
        } else {
            System.out.println("No customers in the queue.");
        }
        return customer;
    }

    public Customer getNextCustomer() {
        return customerQueue.peek();
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

	public Queue<Customer> getQueue() {
		// TODO Auto-generated method stub
		return customerQueue;
	}
}
