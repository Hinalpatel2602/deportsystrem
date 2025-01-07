package src;

public class Main {
    public static void main(String[] args) {
        
        QueueOfCustomers customerQueue = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();
        Worker worker = new Worker(customerQueue, parcelMap);
        
        loadCustomers(customerQueue);
        loadParcels(parcelMap);
        

       
        AppView view = new AppView();
        AppView.AppController controller = new AppView.AppController(customerQueue, parcelMap, worker, view);
        
        controller.updateCustomerQueue();
        controller.updateParcelList();
        
        view.setVisible(true);

    }
    
    private static void loadCustomers(QueueOfCustomers customerQueue) {
        String[] customers = {
                "Andrew Robertson,X919",
                "Ann Jones,X064",
                "Blair Foster,X682",
                "Bob Dawson,X780",
                "Chris Smith,X782",
                "Dave Jackson,X316",
                "David Hunter,X278",
                "Donald Murray,X720",
                "Fiona Thoms,X475",
                "Gillian Hamilton,X386",
                "Harry Johnston,X857",
                "Helen Webster,X309",
                "Jack Houston,X733",
                "Jo Hill,X285",
                "Joe Woods,X213",
                "John Brown,X009",
                "Judy Hilman,X904",
                "Keith Burns,X552",
                "Lily Watson,X121",
                "Lucy Grey,X025",
                "Mary Brown,X198",
                "Pamela Field,X521",
                "Peter White,X036",
                "Robert Murray,X606",
                "Susan Turner,X214",
                "Thomas Young,X507",
                "Tim Smith,X020",
                "Tony Lawson,X086",
                "Ursula Milton,X746",
                "Viola Nicholson,X540"
                
        };
        for (String customerData : customers) {
            String[] parts = customerData.split(",");
            customerQueue.addCustomer(new Customer(parts[1].trim(), parts[0].trim(), "contact@example.com"));
        }
    }
        private static void loadParcels(ParcelMap parcelMap) {
            String[] parcels = {
                    "X009,9,1,9,9,7",
                    "X020,1,1,6,4,14",
                    "X025,7,1,4,9,9",
                    "X036,8,4,6,9,12",
                    "X064,8,4,1,8,15",
                    "X086,7,4,1,7,13",
                    "X121,3,7,2,3,6",
                    "X198,9,4,8,0,10",
                    "X213,4,8,5,2,15",
                    "X214,1,8,1,1,15",
                    "X278,5,3,1,0,11",
                    "X285,1,4,3,1,10",
                    "X309,1,2,8,5,11",
                    "X316,9,5,4,0,11",
                    "X386,9,1,6,5,9",
                    "X475,4,3,8,1,11",
                    "X507,5,3,9,8,13",
                    "X521,6,4,4,4,8",
                    "X540,9,2,5,4,5",
                    "X552,4,5,7,8,12",
                    "X606,8,8,4,2,13",
                    "X682,3,6,4,4,12",
                    "X720,4,2,1,3,8",
                    "X733,6,6,5,7,11",
                    "X746,4,4,9,5,7",
                    "X780,4,1,2,5,12",
                    "X782,5,3,2,7,12",
                    "X857,2,6,6,3,9",
                    "X904,4,1,4,9,15",
                    "X919,5,8,7,4,10"
            };

        // Display log entries
        System.out.println("\nLog Entries:");
        System.out.println(Log.getInstance().getEntries());
       
        for (String parcelData : parcels) {
            String[] parts = parcelData.split(",");
            parcelMap.addParcel(new Parcel(
                    parts[0].trim(),                         // Parcel ID
                    Double.parseDouble(parts[1].trim()),    // Weight
                    "Destination " + parts[0].trim()        // Destination (use parcel ID for simplicity)
            ));
    }
        }
}

    

