package ui;



import exceptions.InvalidPhoneException;
import model.AddressBook;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Observable;

//Reference: https://www.youtube.com/watch?v=iE8tZ0hn2Ws

public class GUI extends Observable implements ActionListener {
    private AddressBook addressBook;
    private static JPanel panel;
    private static JLabel addLocationLabel;
    private static JLabel locationLabel;
    private static JTextField locationText;
    private static JLabel addressLabel;
    private static JTextField addressText;
    private static JLabel phoneLabel;
    private static JTextField phoneText;
    private static JLabel categoryLabel;
    private static JTextField categoryText;
    private static JButton addButton;
    private static JButton showButton;
    private static JLabel searchLocationLabel;
    private static JLabel searchLabel;
    private static JTextField searchText;
    private static JButton searchButton;
    private static JLabel removeLocationLabel;
    private static JLabel removeLabel;
    private static JTextField removeText;
    private static JButton removeButton;
    private static JButton saveButton;
    private static JButton loadButton;
    private static JLabel image;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/addressbook.json";
    private static JLabel resultsLabel;
    private static JLabel showLabel;

    //EFFECTS: constructor
    public GUI() {
        String locationName = "locationBook";
        addressBook = new AddressBook(locationName);


    }

    //EFFECTS: creates GUI with all components
    public JPanel createPanel() {

        panel = new JPanel();
        panel.setLayout(null);

        createAddLocationPanel();
        createSearchLocationPanel();
        createRemoveLocationLabel();
        createSaveButton();
        createLoadButton();

        addButton.addActionListener(this);
        searchButton.addActionListener(this);
        removeButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        showButton.addActionListener(this);

        addPicture();
        createResultsPanel();
        createListLocationsPanel();

        return panel;

    }

    //MODIFIES: this
    //EFFECTS: adds picture to GUI
    private void addPicture() {
        image = new JLabel();
        image.setBounds(300, 0, 2000, 500);

        try {
            image.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("DogGooo Image.png"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(image);
    }

    //MODIFIES: this
    //EFFECTS: creates panel within GUI that shows the results when clicking on button
    private void createResultsPanel() {
        JPanel resultsPanel = new JPanel();
        resultsPanel.setPreferredSize(new Dimension(1000,400));
        resultsPanel.setBounds(0, 500, 1000, 25);

        resultsPanel.setVisible(true);

        resultsLabel = new JLabel();
        resultsPanel.add(resultsLabel);
        panel.add(resultsPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates panel with GUI that shows location names added to the address book
    private void createListLocationsPanel() {
        //create panel to show locations added
        JPanel listLocationsPanel = new JPanel();
        listLocationsPanel.setPreferredSize(new Dimension(1000,400));
        listLocationsPanel.setBounds(0,550,1000,25);

        listLocationsPanel.setVisible(true);

        showLabel = new JLabel();
        listLocationsPanel.add(showLabel);
        panel.add(listLocationsPanel);

    }

    //MODIFIES: this
    //EFFECTS: implements load button that allows user to load address book
    private void createLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(150, 480, 80, 25);
        loadButton.setActionCommand("Load");


        panel.add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: implements save button that allows user to save address book
    private void createSaveButton() {
        //save and load button
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 480, 80, 25);
        saveButton.setActionCommand("Save");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        panel.add(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: creates remove location panel
    private void createRemoveLocationLabel() {
        createRemoveTitle();
        createRemoveFields();
        panel.add(removeButton);
    }

    //MODIFIES: this
    //EFFECTS: creates components in remove location panel
    private void createRemoveFields() {
        removeLabel = new JLabel("Location");
        removeLabel.setBounds(10,380,80,25);
        panel.add(removeLabel);

        removeText = new JTextField();
        removeText.setBounds(100, 380, 165, 25);
        panel.add(removeText);

        removeButton = new JButton("Remove");
        removeButton.setBounds(190, 410, 80, 25);
        removeButton.setActionCommand("Remove");
    }

    //MODIFIES: this
    //EFFECTS: creates remove location header
    private void createRemoveTitle() {
        removeLocationLabel = new JLabel("Remove Location");
        removeLocationLabel.setBounds(10, 350, 165, 25);
        panel.add(removeLocationLabel);
    }

    //MODIFIES: this
    //EFFECTS: creates search location panel
    private void createSearchLocationPanel() {

        createSearchTitle();
        createSearchFields();
        panel.add(searchButton);
    }

    //MODIFIES: this
    //EFFECTS: creates components in search location panel

    private void createSearchFields() {
        searchLabel = new JLabel("Location");
        searchLabel.setBounds(10, 260, 80, 25);
        panel.add(searchLabel);
        ;

        searchText = new JTextField();
        searchText.setBounds(100, 260, 165, 25);
        panel.add(searchText);

        searchButton = new JButton("Search");
        searchButton.setBounds(190, 290, 80, 25);
        searchButton.setActionCommand("Search");
    }

    //MODIFIES: this
    //EFFECTS: creates search location title
    private void createSearchTitle() {
        searchLocationLabel = new JLabel("Search Location");
        searchLocationLabel.setBounds(10, 230, 165, 25);
        panel.add(searchLocationLabel);
    }

    //MODIFIES: this
    //EFFECTS: implement add location panel
    private void createAddLocationPanel() {
        createAddLocationTitle();

        createLocationFields();
        createAddressFields();
        createPhoneFields();
        createCategoryFields();

        addButton = new JButton("Add");
        addButton.setBounds(190, 170, 80, 25);
        addButton.setActionCommand("Add");

        showButton = new JButton("Show");
        showButton.setBounds(0, 170, 80, 25);
        showButton.setActionCommand("Show");

        panel.add(addButton);
        panel.add(showButton);
    }

    //EFFECTS: create category fields in add location panel
    private void createCategoryFields() {
        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(10, 140, 80, 25);
        panel.add(categoryLabel);

        categoryText = new JTextField();
        categoryText.setBounds(100, 140, 165, 25);
        panel.add(categoryText);
    }

    //EFFECTS: create phone fields in add location panel
    private void createPhoneFields() {
        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(10, 110, 80, 25);
        panel.add(phoneLabel);
        phoneText = new JTextField();
        phoneText.setBounds(100, 110, 165, 25);
        panel.add(phoneText);
    }

    //EFFECTS: create address fields in add location panel
    private void createAddressFields() {
        addressLabel = new JLabel("Address");
        addressLabel.setBounds(10, 70, 80, 25);
        panel.add(addressLabel);
        addressText = new JTextField();
        addressText.setBounds(100, 70, 165, 25);
        panel.add(addressText);
    }

    //EFFECTS: create location fields in add location panel
    private void createLocationFields() {
        locationLabel = new JLabel("Location");
        locationLabel.setBounds(10, 40, 165, 25);
        panel.add(locationLabel);
        locationText = new JTextField(20);
        locationText.setBounds(100, 40, 165, 25);
        panel.add(locationText);
    }

    //EFFECTS: create title in add location panel
    private void createAddLocationTitle() {
        addLocationLabel = new JLabel("Add Location");
        addLocationLabel.setBounds(10, 10, 165, 25);
        panel.add(addLocationLabel);
    }



    //EFFECTS: implement action listeners for each button
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add")) {
            addActionCommand();
        } else if (e.getActionCommand().equals("Search")) {
            String searchLocation = searchText.getText();
            for (int i = 0; i < addressBook.getLocations().size(); i++) {
                searchActionCommand(searchLocation, i);
            }
        } else if (e.getActionCommand().equals("Remove")) {
            String removeLocation = removeText.getText();
            for (int i = 0; i < addressBook.getLocations().size(); i++) {
                removeActionCommand(removeLocation, i);
            }
        } else if (e.getActionCommand().equals("Show")) {
            showActionCommand();
        } else if (e.getActionCommand().equals("Save")) {
            saveMethod();
        } else {
            loadMethod();
        }
    }

    //EFFECTS: when load button is clicked, loads address book
    private void loadMethod() {
        try {
            loadActionCommand();
        } catch (IOException a) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: when save button is clicked, saves address book
    private void saveMethod() {
        try {
            saveActionCommand();
        } catch (FileNotFoundException a) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: throws exception helper methods for load method
    private void loadActionCommand() throws IOException {
        addressBook = jsonReader.read();
        resultsLabel.setText("Loaded " + addressBook.getLocations() + " from " + JSON_STORE);
    }

    //EFFECTS: throws exception helper methods for save method
    private void saveActionCommand() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(addressBook);
        jsonWriter.close();
        System.out.println("Saved " + addressBook.getLocations() + " to " + JSON_STORE);
        resultsLabel.setText("Saved!");
    }

    //EFFECTS: when user click on remove button, removes location from address book
    private void removeActionCommand(String removeLocation, int i) {
        if (addressBook.getLocations().get(i).getLocationName().equals(removeLocation)) {
            addressBook.removeLocation(addressBook.getLocations().get(i));
            resultsLabel.setText("Removed Successfully!");
        }
    }

    //EFFECTS: when user click on search button, searches location from address book
    private void searchActionCommand(String searchLocation, int i) {
        if (addressBook.getLocations().get(i).getLocationName().equals(searchLocation)) {
            resultsLabel.setText(addressBook.searchLocation(searchLocation));
        }
    }

    //EFFECTS: when user click on add button, add location into address book
    private void addActionCommand() {
        String locationName = locationText.getText();
        String address = addressText.getText();
        String phone = phoneText.getText();
        String category = categoryText.getText();

        try {
            addressBook.addLocation(new Location(locationName, address, phone, category));
            resultsLabel.setText("Added Successfully!");
        } catch (InvalidPhoneException e) {
//            e.printStackTrace();
            resultsLabel.setText("Phone number is not valid");
        }
//        resultsLabel.setText("Phone number is not valid");
    }

    //EFFECTS: when user click on show button, prints location names of locations in address book
    private void showActionCommand() {
        String locations = "";
        for (Location l : addressBook.getLocations()) {
            locations += l.getLocationName() + "  ";
        }
        showLabel.setText("Locations in Address Book: " + locations);
    }


}
