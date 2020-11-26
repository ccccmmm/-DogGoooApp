package persistence;

import exceptions.InvalidPhoneException;
import model.AddressBook;
import model.Location;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;



// Code Reference: JsonSerializationDemo

// Represents a reader that reads AddressBook from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads AddressBook from file and returns it
    // throws IOException if an error occurs reading data from file
    public AddressBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAddressBook(jsonObject);
    }

    //EFFECTS: reads source dile as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses addressBook from JSON  object and returns it
    private AddressBook parseAddressBook(JSONObject jsonObject) {
        String locationName = jsonObject.getString("location name");
        AddressBook ab = new AddressBook(locationName);
        addLocations(ab, jsonObject);
        return ab;
    }

    // MODIFIES: ab
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addLocations(AddressBook ab, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("locations");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocation(ab, nextLocation);
        }
    }

    // MODIFIES: ab
    // EFFECTS: parses thingy from JSON object and adds it to addressBook
    private void addLocation(AddressBook ab, JSONObject jsonObject) {
        String locationName  = jsonObject.getString("location name");
        String address = jsonObject.getString("address");
        String phoneNumber = jsonObject.getString("phone number");
        String locationCategory = jsonObject.getString("location category");

        Location location = null;
        try {
            location = new Location(locationName,  address, phoneNumber, locationCategory);
            ab.addLocation(location);
        } catch (InvalidPhoneException e) {
            //
        }

    }



}