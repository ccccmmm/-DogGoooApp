package persistence;

import model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkLocation(String locationName, String address, String phoneNumber, String locationCategory,
                                 Location location) {
        assertEquals(locationName, location.getLocationName());
        assertEquals(address, location.getAddress());
        assertEquals(phoneNumber, location.getPhoneNumber());
        assertEquals(locationCategory, location.getLocationCategory());

    }
}
