package src;

public class Worker {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;

    public Worker(QueueOfCustomers customerQueue, ParcelMap parcelMap) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
    }

    public void processNextCustomer() {
        Customer customer = customerQueue.getNextCustomer();
        if (customer == null) {
            System.out.println("No customers in the queue.");
            Log.getInstance().addEntry("No customers in the queue.");
            return;
        }

        Parcel parcel = parcelMap.getParcel(customer.getId());
        if (parcel == null) {
            System.out.println("No parcel found for customer: " + customer.getName());
            Log.getInstance().addEntry("No parcel found for customer: " + customer.getName());
            return;
        }

        double fee = calculateFee(parcel);
        parcel.markAsProcessed();
        customerQueue.removeCustomer();

        Log.getInstance().addEntry("Processed Parcel " + parcel.getId() + " for Customer " + customer.getName() + ". Fee: $" + fee);
        System.out.println("Processed parcel for customer: " + customer.getName() + ", Fee: $" + fee);
    }

    private double calculateFee(Parcel parcel) {
        return parcel.getWeight() * 2.5; // Example fee calculation
    }

	public static void processCustomer(Customer customer, Parcel parcel) {
		// TODO Auto-generated method stub
		
	}
}
