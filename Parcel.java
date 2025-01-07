package src;

public class Parcel {
    private String id;
    private double weight;
    private String destination;
    private boolean processed;

    public Parcel(String id, double weight, String destination) {
        this.id = id;
        this.weight = weight;
        this.destination = destination;
        this.processed = false;
    }

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void markAsProcessed() {
        this.processed = true;
    }

    public String getDetails() {
        return "Parcel ID: " + id + ", Weight: " + weight + ", Destination: " + destination + ", Processed: " + processed;
    }
}
