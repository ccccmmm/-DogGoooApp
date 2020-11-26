package persistence;

import exceptions.InvalidPhoneException;
import model.AddressBook;
import model.Location;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AddressBook ab = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAddressBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAddressBook.json");
        try {
            AddressBook ab = reader.read();
            assertEquals(new ArrayList<>(), ab.getLocationNames());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidPhone() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidPhoneNumber.json");
        try {
            AddressBook ab = reader.read();
            assertEquals(1, ab.getLocationNames().size());
            assertTrue(ab.getLocationNames().contains("B"));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }




    @Test
    void testReaderGeneralAddressBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAddressBook.json");
        ArrayList<String> locationTest = new ArrayList<>();
        locationTest.add("A");
        locationTest.add("B");
        try {
            AddressBook ab = reader.read();
            assertEquals(locationTest, ab.getLocationNames());
            List<Location> locations = ab.getLocations();
            assertEquals(2, locations.size());
            checkLocation("A", "123 Road, Vancouver BC V6T123", "7771112345", "Restaurant",
                    locations.get(0));
            checkLocation("B", "456 Road, Vancouver BC V56789","7661234567", "Boutique",
                    locations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @SuppressWarnings("checkstyle:OuterTypeFilename")
    static
    class JsonWriterTest extends JsonTest {

        @org.junit.jupiter.api.Test
        void testWriterInvalidFile() {
            try {
                AddressBook ab = new AddressBook("My address book");
                JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
                writer.open();
                fail("IOException was expected");
            } catch (IOException e) {
                // pass
            }
        }

        @org.junit.jupiter.api.Test
        void testWriterEmptyAddressBook() {
            try {
                AddressBook ab = new AddressBook("My address book");
                JsonWriter writer = new JsonWriter("./data/testWriterEmptyAddressBook.json");
                writer.open();
                writer.write(ab);
                writer.close();

                JsonReader reader = new JsonReader("./data/testWriterEmptyAddressBook.json");
                ab = reader.read();
                assertEquals("My address book", ab.getLocationNames());

            } catch (IOException e) {
                fail("Exception should not have been thrown");
            }
        }

        @org.junit.jupiter.api.Test
        void testWriterGeneralAddressBook() {
            try {
                AddressBook ab = new AddressBook("My address book");
                try {
                    ab.addLocation(new Location("A", "123 Road, Vancouver BC V6T123",
                            "7771112345", "Restaurant"));
                } catch (InvalidPhoneException e) {
                    e.printStackTrace();
                }
                try {
                    ab.addLocation(new Location("B", "456 Road, Vancouver BC V56789",
                            "7661234567", "Boutique"));
                } catch (InvalidPhoneException e) {
                    //e.printStackTrace();
                }
                JsonWriter writer = new JsonWriter("./data/testWriterGeneralAddressBook.json");
                writer.open();
                writer.write(ab);
                writer.close();

                JsonReader reader = new JsonReader("./data/testWriterGeneralAddressBook.json");
                ab = reader.read();
                assertEquals("My address book", ab.getLocationNames());
                List<Location> locations = ab.getLocations();
                assertEquals(2, locations.size());
                checkLocation("A", "123 Road, Vancouver BC V6T123", "7771112345", "Restaurant",
                        locations.get(0));
                checkLocation("B", "456 Road, Vancouver BC V56789","7661234567", "Boutique",
                        locations.get(1));

            } catch (IOException e) {
                fail("Exception should not have been thrown");
            }
        }
    }
}
