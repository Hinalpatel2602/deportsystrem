package src;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppView extends JFrame {
   
	private static final long serialVersionUID = 1L;
	private JTextArea customerQueueArea;
    private JTextArea parcelListArea;
    private JTextArea processingArea;
    private JTextArea logArea;

    private JButton addCustomerButton;
    private JButton removeCustomerButton;
    private JButton processCustomerButton;
    private JButton addParcelButton;
    private JButton exitButton;

    public AppView() {
      
        setTitle("Parcel Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

   
        customerQueueArea = new JTextArea(10, 20);
        customerQueueArea.setEditable(false);
        JScrollPane customerScrollPane = new JScrollPane(customerQueueArea);
        JPanel customerPanel = new JPanel(new BorderLayout());
        customerPanel.add(new JLabel("Customer Queue"), BorderLayout.NORTH);
        customerPanel.add(customerScrollPane, BorderLayout.CENTER);

       
        parcelListArea = new JTextArea(10, 20);
        parcelListArea.setEditable(false);
        JScrollPane parcelScrollPane = new JScrollPane(parcelListArea);
        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.add(new JLabel("Parcel List"), BorderLayout.NORTH);
        parcelPanel.add(parcelScrollPane, BorderLayout.CENTER);

       
        processingArea = new JTextArea(5, 40);
        processingArea.setEditable(false);
        JScrollPane processingScrollPane = new JScrollPane(processingArea);
        JPanel processingPanel = new JPanel(new BorderLayout());
        processingPanel.add(new JLabel("Processing Details"), BorderLayout.NORTH);
        processingPanel.add(processingScrollPane, BorderLayout.CENTER);

      
        logArea = new JTextArea(10, 40);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.add(new JLabel("Log"), BorderLayout.NORTH);
        logPanel.add(logScrollPane, BorderLayout.CENTER);

 
        addCustomerButton = new JButton("Add Customer");
        removeCustomerButton = new JButton("Remove Customer");
        processCustomerButton = new JButton("Process Next Customer");
        addParcelButton = new JButton("Add Parcel");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(removeCustomerButton);
        buttonPanel.add(processCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(exitButton);

     
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(customerPanel);
        topPanel.add(parcelPanel);

        add(topPanel, BorderLayout.NORTH);
        add(processingPanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

   
    public JTextArea getCustomerQueueArea() {
        return customerQueueArea;
    }

    public JTextArea getParcelListArea() {
        return parcelListArea;
    }

    public JTextArea getProcessingArea() {
        return processingArea;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

   
    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JButton getRemoveCustomerButton() {
        return removeCustomerButton;
    }

    public JButton getProcessCustomerButton() {
        return processCustomerButton;
    }

    public JButton getAddParcelButton() {
        return addParcelButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

 
    public void addListener(ActionListener listener) {
        addCustomerButton.addActionListener(listener);
        removeCustomerButton.addActionListener(listener);
        processCustomerButton.addActionListener(listener);
        addParcelButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

    public static class AppController implements ActionListener {
        private QueueOfCustomers customerQueue;
        private ParcelMap parcelMap;
        private Log log;
        private AppView view;

        public AppController(QueueOfCustomers customerQueue, ParcelMap parcelMap, Worker worker, AppView view) {
            this.customerQueue = customerQueue;
            this.parcelMap = parcelMap;
            this.log = Log.getInstance();
            this.view = view;

            view.addListener(this); 
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getAddCustomerButton()) {
                // Add a new customer
                Customer newCustomer = new Customer(
                        "C" + (Customer.size() + 1), // Generate unique ID
                        "New Customer",
                        "contact@example.com"
                );
                Customer.addCustomer(newCustomer);
                updateCustomerQueue();
            } else if (e.getSource() == view.getRemoveCustomerButton()) {
                // Remove the next customer in the queue
                customerQueue.removeCustomer();
                updateCustomerQueue();
            } else if (e.getSource() == view.getProcessCustomerButton()) {
                processCustomer();
            } else if (e.getSource() == view.getAddParcelButton()) {
                
                Parcel newParcel = new Parcel(
                        "P" + (ParcelMap.size() + 1),
                        5.0, 
                        "New Destination" 
                );
                parcelMap.addParcel(newParcel);
                updateParcelList();
            } else if (e.getSource() == view.getExitButton()) {
                Log.writeLogToFile("log.txt");
                System.exit(0);
            }
        }

        
        private void processCustomer() {
            Customer customer = Customer.removeCustomer();
            if (customer != null) {
                Parcel parcel = getFirstAvailableParcel();
                if (parcel != null) {
                    Worker.processCustomer(customer, parcel);
                    markParcelAsCollected(parcel);
                    updateProcessingArea(customer, parcel);
                    updateParcelList();
                    updateCustomerQueue();
                } else {
                    view.getProcessingArea().setText("No parcels available for the customer: " + customer.getName());
                    log.addEntry("No parcels available for customer " + customer.getName());
                }
            } else {
                view.getProcessingArea().setText("No customers in the queue to process.");
            }
        }

       
        private Parcel getFirstAvailableParcel() {
            for (Parcel parcel : parcelMap.values()) {
                return parcel; // Return the first available parcel
            }
            return null; 
        }

        
        private void markParcelAsCollected(Parcel parcel) {
            parcelMap.removeParcel(parcel.getId());
            log.addEntry("Parcel " + parcel.getId() + " collected by a customer.");
        }

        // Update the customer queue in the GUI
        public void updateCustomerQueue() {
            view.getCustomerQueueArea().setText("");
            for (Customer customer : Customer.getCustomers()) {
                view.getCustomerQueueArea().append(customer.getDetails() + "\n");
            }
        }

   
        public void updateParcelList() {
            view.getParcelListArea().setText("");
            for (Parcel parcel : parcelMap.values()) {
                view.getParcelListArea().append(parcel.getDetails() + "\n");
            }
        }

        
        private void updateProcessingArea(Customer customer, Parcel parcel) {
            view.getProcessingArea().setText("");
            if (customer != null && parcel != null) {
                view.getProcessingArea().append("Processed Customer: " + customer.getName() + "\n");
                view.getProcessingArea().append("Parcel Details: " + parcel.getDetails() + "\n");
            } else {
                view.getProcessingArea().append("No customer or parcel to process.\n");
            }
        }
    }
}
