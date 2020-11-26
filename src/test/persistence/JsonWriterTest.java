package persistence;

import model.Location;
import model.AddressBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("checkstyle:OuterTypeFilename")
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            AddressBook  ab = new AddressBook("My address book");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAddressBook() {
        try {
            AddressBook ab = new AddressBook("My address book");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAddressBook.json");
            writer.open();
            writer.write(ab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAddressBook.json");
            ab = reader.read();
            assertEquals(new ArrayList<>(), ab.getLocationNames());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAddressBook() {
        ArrayList<String> locationTest = new ArrayList<>();
        locationTest.add("A");
        locationTest.add("B");

        try {
            AddressBook ab = new AddressBook("My address book");
            ab.addLocation(new Location("A", "123 Road, Vancouver BC V6T123",
                    "7771112345", "Restaurant"));
            ab.addLocation(new Location("B", "456 Road, Vancouver BC V56789",
                    "7661234567", "Boutique"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAddressBook.json");
            writer.open();
            writer.write(ab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAddressBook.json");
            ab = reader.read();
            assertEquals(locationTest, ab.getLocationNames());
            List<Location> locations = ab.getLocations();
            assertEquals(2, locations.size());
            checkLocation("A", "123 Road, Vancouver BC V6T123", "7771112345", "Restaurant",
                    locations.get(0));
            checkLocation("B", "456 Road, Vancouver BC V56789","7661234567", "Boutique",
                    locations.get(1));

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}