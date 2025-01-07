package src;

import java.util.HashMap;

public class ParcelMap {
    private HashMap<String, Parcel> parcelMap;

    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
        Log.getInstance().addEntry("Parcel " + parcel.getId() + " added with destination " + parcel.getDestination());
    }

    public Parcel getParcel(String id) {
        return parcelMap.get(id);
    }

    public void removeParcel(String id) {
        Parcel removedParcel = parcelMap.remove(id);
        if (removedParcel != null) {
            Log.getInstance().addEntry("Parcel " + id + " removed from the map.");
        } else {
            Log.getInstance().addEntry("Attempted to remove Parcel " + id + " but it was not found.");
        }
    }

    public void displayParcels() {
        System.out.println("Current parcels:");
        for (Parcel parcel : parcelMap.values()) {
            System.out.println(parcel.getDetails());
        }
    }

    public Iterable<Parcel> values() {
    	if (parcelMap == null) {
            return new HashMap<String, Parcel>().values(); // Return an empty collection if null
        }
        return parcelMap.values(); 
	}

	public static int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
