package model;

//Code Reference: https://coderanch.com/t/671937/java/Address-Book-Java-beginner
//Code Reference: Eric Newton

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook implements Writable {
    String locationName;
    ArrayList<Location> locations;

    //Constructor
    public AddressBook(String locationName) {
        locations = new ArrayList<Location>();
        this.locationName = locationName;

    }

    public AddressBook() {
        locations = new ArrayList<>();
        this.locationName = "";
    }

    //MODIFIES: locations
    //EFFECTS: add location to locations list
    public void addLocation(Location location) {
        locations.add(location);
    }

    //EFFECTS: return information of the searched location
    public String searchLocation(String locationName) {
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            if (locationName.equals(location.locationName)) {
                return location.printLocationInfo();
            }
        }
        return "No Search Results";
    }

    //REQUIREMENT: array list already contained that location
    //MODIFIES: locations
    //EFFECTS: remove location and its information from the array list
    public boolean removeLocation(Location location) {
        for (int i = 0; i < locations.size(); i++) {
            Location location1 = locations.get(i);
            if (location.equals(location1)) {
                locations.remove(i);
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return locations in locations array list
    public ArrayList<Location> getLocations() {
        return this.locations;
    }

    //EFFECTS: return location names in the locations array list
    public List<String> getLocationNames() {
        List<String> addressBook = new ArrayList<>();
        for (Location location: locations) {
            addressBook.add(location.getLocationName());
        }
        return addressBook;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("location name", locationName);
        json.put("locations", locationsToJson());
        return json;
    }

    //returns locations in this addressBook as a Json array
    private JSONArray locationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location l : locations) {
            jsonArray.put(l.toJson());
        }
        return jsonArray;

    }






}






