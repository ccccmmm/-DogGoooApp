package model;

import exceptions.InvalidPhoneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Code reference: Account Not Robust

class AddressBookTest {
    private AddressBook testAddressBook;
    private Location locationOne;
    private Location locationTwo;
    private Location locationThree;
    private Location locationFour;
    private Location locationFive;


    @BeforeEach
    void runBefore() {
        testAddressBook = new AddressBook();
        try {
            locationOne = new Location("The Latest Scoop", "305 Water St, Vancouver BC V6B1B8",
                    "6044285777", "Clothing Store");
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }
        try {
            locationTwo = new Location("Moonlight Dog Cafe Pet Store", "835 Beatty St, " +
                    "Vancouver BC V6B2M6",
                    "6045593680", "Cafe");
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }
        try {
            locationThree = new Location("Kings Mill Walk Park", "1122 Spirit Trail, North Vancouver "
                    +
                    "BC V7P3S1", "7781230000", "Park");
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }
        try {
            locationFour = new Location("Good Boy Collective", "3633 Main St, Vancouver BC V5V3N6",
                    "6048735500", "Pet Store");
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }
        try {
            locationFive = new Location("Hello", "123 Sesame St, Vancouver V6T1Z2",
                    "abcedghij", "Clothing Store");
        } catch (InvalidPhoneException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testAddOneLocation() {
        final int addressBookSizeBefore = testAddressBook.locations.size();
        testAddressBook.addLocation(locationOne);
        final int addressBookSizeAfter = testAddressBook.locations.size();
        assertEquals(addressBookSizeAfter - addressBookSizeBefore, 1);

    }

    @Test
    void testAddMultipleLocations() {
        final int addressBookSizeBefore = testAddressBook.locations.size();
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);
        testAddressBook.addLocation(locationThree);
        final int addressBookSizeAfter = testAddressBook.locations.size();
        assertEquals(addressBookSizeAfter - addressBookSizeBefore, 3);
    }

    @Test
    void testSearchLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);
        testAddressBook.addLocation(locationThree);


        assertEquals(locationOne.printLocationInfo(), testAddressBook.searchLocation(locationOne.getLocationName()));
        assertEquals(locationTwo.printLocationInfo(), testAddressBook.searchLocation(locationTwo.getLocationName()));
        assertEquals(locationThree.printLocationInfo(), testAddressBook.searchLocation(locationThree.getLocationName()));


        assertTrue(testAddressBook.locations.contains(locationOne));
        assertTrue(testAddressBook.locations.contains(locationTwo));
        assertTrue(testAddressBook.locations.contains(locationThree));
        assertFalse(testAddressBook.locations.contains(locationFour));

    }

    @Test
    void testSearchLocationNoResults() {
        assertFalse(testAddressBook.locations.contains(locationOne));
        assertFalse(testAddressBook.locations.contains(locationTwo));
        assertFalse(testAddressBook.locations.contains(locationThree));
        assertFalse(testAddressBook.locations.contains(locationFour));

        assertEquals("No Search Results", testAddressBook.searchLocation(locationOne.getLocationName()));


    }




    @Test
    void testRemoveExistingLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);
        testAddressBook.addLocation(locationThree);

        testAddressBook.removeLocation(locationOne);
        assertFalse(testAddressBook.locations.contains(locationOne));
        assertEquals(2, testAddressBook.locations.size());

        for(int i = 0; i < testAddressBook.locations.size(); i ++) {
            if (testAddressBook.locations.get(i).printLocationInfo().equals(locationOne.printLocationInfo())) {
                fail();
            }
        }
    }

    @Test
    void testRemoveNonExistingLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);
        testAddressBook.addLocation(locationThree);

        assertFalse(testAddressBook.removeLocation(locationFour));

    }

    @Test
    void testGetLocation() {
        testAddressBook.addLocation(locationOne);

        assertTrue(testAddressBook.locations.contains(locationOne));
    }

    @Test
    void testGetLocation1() {
        locationOne.getLocationName();
    }

    @Test
    void testGetLocation2() {
        locationTwo.getLocationName();
    }

    @Test
    void testGetAddress1() {
        locationOne.getAddress();
    }

    @Test
    void testGetPhoneNo1() {
        locationOne.getPhoneNumber();
    }

//    @Test
//    void testGetPhoneException() {
//        locationFive.getPhoneNumber();
//        fail("Invalid phone number");
//
//    }
    @Test
    void testPhoneNumberSuccess() {
        try {
            Location locationSix = new Location("Hello", "123 Barney St, Vancouver V6T0C7",
                    "7789939214", "Clothing store");
            locationSix.getPhoneNumber();
        } catch (InvalidPhoneException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testPhoneNumberNotSuccess() {
        try {
            Location locationSeven = new Location("Hello", "123 Barney St, Vancouver V6T0C7",
                    "abcdefghij", "Clothing store");
            locationSeven.getPhoneNumber();
        } catch (InvalidPhoneException e) {
            System.out.println("Please type a valid phone number");
        }
    }

    @Test
    void testPhoneNumberNotSuccessDigits() {
        try {
            Location locationSeven = new Location("Hello", "123 Barney St, Vancouver V6T0C7",
                    "7789949816789", "Clothing store");
        } catch (InvalidPhoneException e) {
            System.out.println("Please type a valid phone number");
        }
    }

    @Test
    void testGetLocationCategory() {
        locationOne.getLocationCategory();
    }

    @Test
    void testPrintLocationInfo() {
        locationOne.printLocationInfo();
    }

    @Test
    void testRemoveLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.removeLocation(locationOne);

        assertEquals(0, testAddressBook.locations.size());
    }

    @Test
    void testRemoveLocationNoLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.removeLocation(locationTwo);

        assertEquals(1, testAddressBook.locations.size());
    }

    @Test
    void testAddLocation() {
        testAddressBook.addLocation(locationOne);
        assertEquals(1, testAddressBook.locations.size());
    }

    @Test
    void testAddLocationWithLocation() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);
        assertEquals(2, testAddressBook.locations.size());
    }

    @Test
    void testGetLocationNames() {
        testAddressBook.addLocation(locationOne);
        testAddressBook.addLocation(locationTwo);


        for (int i = 0; i < testAddressBook.locations.size(); i++) {
            testAddressBook.getLocationNames();
        }
        assertEquals(2, testAddressBook.locations.size());
        assertTrue(testAddressBook.locations.contains(locationOne));
        assertTrue(testAddressBook.locations.contains(locationTwo));
     }

    @Test
    void testGetLocationNamesNone() {
        assertEquals(0, testAddressBook.locations.size());
        assertFalse(testAddressBook.locations.contains(locationOne));
    }

    @Test
    void testGetLocations() {
        testAddressBook.addLocation(locationTwo);
        testAddressBook.addLocation(locationThree);

        assertEquals(2, testAddressBook.locations.size());
        assertTrue(testAddressBook.locations.contains(locationTwo));

        assertTrue(testAddressBook.locations.contains(locationThree));

        for (int i = 0; i < testAddressBook.locations.size(); i++) {
            testAddressBook.getLocations();
        }




    }

    @Test
    void testGetLocationsNone() {
        assertEquals(0, testAddressBook.locations.size());

        assertFalse(testAddressBook.locations.contains(locationOne));


        assertFalse(testAddressBook.locations.contains(locationTwo));


    }
}

