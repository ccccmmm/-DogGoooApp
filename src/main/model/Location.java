package model;
// Code reference: Eric Newton & AccountNotRobust & https://www.geeksforgeeks.org/java-program-check-valid-mobile-number/


import org.json.JSONObject;
import persistence.Writable;
import exceptions.InvalidPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Represents a location having a location name, address and phone number
public class Location implements Writable {
    String locationName;
    String address;
    String phoneNumber;
    String locationCategory;

    // Constructor

    public Location(String locationName, String address, String phoneNumber, String locationCategory)
            throws InvalidPhoneException {
        this.locationName = locationName;
        this.address = address;

        if (phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new InvalidPhoneException("Please type a valid phone number");
        }

        this.locationCategory = locationCategory;

    }

    //EFFECTS: return location's name
    public String getLocationName() {
        return locationName;
    }

    //EFFECTS: return location's address
    public String getAddress() {
        return address;
    }

    //EFFECTS: return location's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }



    //EFFECTS: return location category of location
    public String getLocationCategory() {
        return locationCategory;
    }

    //EFFECTS: returns string representation of location
    public String printLocationInfo() {
        return "Location Name:" + locationName + "Address:" + address + "Phone Number:"
                + phoneNumber  + "Location Category" + locationCategory;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("location name", locationName);
        json.put("address", address);
        json.put("phone number", phoneNumber);
        json.put("location category", locationCategory);
        return json;
    }



}
