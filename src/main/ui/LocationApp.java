package ui;

import exceptions.InvalidPhoneException;
import model.AddressBook;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Code reference: Eric Newton
// Location Application
public class LocationApp {
    private AddressBook addressBook;
    Scanner scanner = new Scanner(System.in);
    private static final String JSON_STORE = "./data/addressbook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: run the location application
    public LocationApp() throws FileNotFoundException {
        addressBook = new AddressBook("My Address Book");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        run();
    }

    //MODIFIES: this
    //EFFECTS: run the location application
    private void run() {
        System.out.println("Welcome to DogGooo");
        String response;
        do {
            showMenu("Please select an option", "1. Add Location", "2. Search Location",
                    "3. Remove Location");
            System.out.println("4. Exit DogGooo");
            response = scanner.nextLine();

            if (response.equals("1")) {
                addLocation();
            } else if (response.equals("2")) {
                searchLocation();
            } else if (response.equals("3")) {
                removeLocation();
            } else if (response.equals("5")) {
                saveAddressBook();
            } else if (response.equals("6")) {
                loadAddressBook();
            }
        } while (!response.equals("4"));
    }

    private void showMenu(String pleaseSelectAnOption, String s, String s1, String s2) {
        System.out.println(pleaseSelectAnOption);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("5. Save AddressBook");
        System.out.println("6. Load AddressBook");

    }

    //MODIFIES: this
    //EFFECTS: add new location to address book
    private void addLocation() {
        System.out.println("Please enter location name");
        String locationName = scanner.nextLine();
        System.out.println("Please enter the location's address");
        String locationAddress = scanner.nextLine();
        System.out.println("Please enter the location's phone number");
        String locationPhone = scanner.nextLine();
        System.out.println("Please enter the location's category");
        String locationCategory = scanner.nextLine();
        try {
            addressBook.addLocation(new Location(locationName, locationAddress, locationPhone, locationCategory));
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }
    }



    //REQUIREMENT: location already exists in address book
    //MODIFIES: this
    //EFFECTS: removes location from the address book
    private void removeLocation() {
        System.out.println("Please enter location name to be removed");
        String removeLocationName = scanner.nextLine();
        for (int i = 0; i < addressBook.getLocations().size(); i++) {
            if (addressBook.getLocations().get(i).getLocationName().equals(removeLocationName)) {
                addressBook.removeLocation(addressBook.getLocations().get(i));
            }
        }
    }

    //EFFECTS: return location information of searched information
    private void searchLocation() {
        System.out.println("Please enter location name to be searched");
        String searchedLocationName = scanner.nextLine();
        for (int i = 0; i < addressBook.getLocations().size(); i++) {
            if (addressBook.getLocations().get(i).getLocationName().equals(searchedLocationName)) {
                //addressBook.searchLocation(searchedLocationName);
                System.out.println(addressBook.searchLocation(searchedLocationName));
            }
        }
    }

    // EFFECTS: saves the addressBook to file

    private void saveAddressBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(addressBook);
            jsonWriter.close();
            System.out.println("Saved " + addressBook.getLocations() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads addressBook from file
    private void loadAddressBook() {
        try {
            addressBook = jsonReader.read();
            System.out.println("Loaded " + addressBook.getLocations() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
