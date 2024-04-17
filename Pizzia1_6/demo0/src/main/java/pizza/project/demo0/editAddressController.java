package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for editing an address in the application.
 */
public class editAddressController extends Controller implements Initializable {

    @FXML private TextField aptTxtField, cityTxtField, phoneNumTxtField, streetAddressTxtField, zipTxtField;
    @FXML private RadioButton billingRadio, bothRadio, deliveryRadio;
    @FXML private Pane colorDisplayPane;
    @FXML private ChoiceBox<String> stateDropDown;
    @FXML private Label topMessage;

    @FXML private Text labelApt, labelCity, labelOptional, labelPhoneNum, labelState, labelStreet, labelZip;

    private final String[] states = { /* Your state array here */ };

    /**
     * Initializes the controller class. Sets up UI components and loads current address data.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupUI();
    }

    /**
     * Sets up UI components with values and colors based on the current user settings.
     */
    private void setupUI() {
        Background background = new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY));
        colorDisplayPane.setBackground(background);

        Text[] labels = {labelZip, labelStreet, labelState, labelPhoneNum, labelOptional, labelCity, labelApt};
        for (Text label : labels) {
            label.setFill(getCurrentUser().getFontcolor());
        }
        topMessage.setTextFill(getCurrentUser().getFontcolor());

        RadioButton[] radios = {billingRadio, deliveryRadio, bothRadio};
        for (RadioButton radio : radios) {
            radio.setTextFill(getCurrentUser().getFontcolor());
        }

        stateDropDown.getItems().addAll(states);
        loadCurrentAddress();
    }

    /**
     * Loads the current address data into the form fields.
     */
    private void loadCurrentAddress() {
        Addresses currentAddress = getCurrentAddress();
        streetAddressTxtField.setText(currentAddress.getStreetAddress());
        aptTxtField.setText(currentAddress.getApartment());
        cityTxtField.setText(currentAddress.getCity());
        zipTxtField.setText(String.valueOf(currentAddress.getZip()));
        phoneNumTxtField.setText(currentAddress.getPhone());
        stateDropDown.setValue(currentAddress.getState());
        selectAddressType(currentAddress.getAddressType());
    }

    /**
     * Selects the appropriate radio button based on the address type.
     * @param type The address type: 1 for billing, 2 for delivery, 3 for both.
     */
    private void selectAddressType(int type) {
        if (type == 1) {
            billingRadio.setSelected(true);
        } else if (type == 2) {
            deliveryRadio.setSelected(true);
        } else if (type == 3) {
            bothRadio.setSelected(true);
        }
    }

    /**
     * Saves the edited address to the file and updates the address list.
     * @param event The event that triggered this method.
     * @throws IOException If an error occurs during file operations or navigation.
     */
    @FXML
    void saveAddress(ActionEvent event) throws IOException {
        if (validateInputs()) {
            Addresses newAddress = createAddressFromInputs();
            ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
            updateAddresses(addresses, newAddress);
            addressLoader.writeResponseToFile(addresses);
            toDefault(event, "AddressBook.fxml", "Address Book");
        }
    }

    /**
     * Validates input fields and displays appropriate error messages.
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInputs() {
        if (streetAddressTxtField.getText().isEmpty()) {
            topMessage.setText("Street Address Empty");
            return false;
        }
        if (stateDropDown.getValue() == null || stateDropDown.getValue().isEmpty()) {
            topMessage.setText("State Is Empty");
            return false;
        }
        if (cityTxtField.getText().isEmpty()) {
            topMessage.setText("City Is Empty");
            return false;
        }
        if (zipTxtField.getText().isEmpty()) {
            topMessage.setText("Zip Is Empty");
            return false;
        }
        if (phoneNumTxtField.getText().isEmpty()) {
            topMessage.setText("Phone Number is empty");
            return false;
        }
        if (!billingRadio.isSelected() && !deliveryRadio.isSelected() && !bothRadio.isSelected()) {
            topMessage.setText("Select Address Type");
            return false;
        }
        return true;
    }

    /**
     * Creates a new address object from the input fields.
     * @return The newly created address.
     */
    private Addresses createAddressFromInputs() {
        int addressType = billingRadio.isSelected() ? 1 : (deliveryRadio.isSelected() ? 2 : 3);
        return new Addresses(
                getCurrentUser().getUserID(),
                addressType,
                streetAddressTxtField.getText(),
                aptTxtField.getText(),
                cityTxtField.getText(),
                stateDropDown.getValue(),
                Integer.parseInt(zipTxtField.getText()),
                phoneNumTxtField.getText());
    }

    /**
     * Updates the list of addresses by removing the current address and adding the new one.
     * @param addresses The list of addresses.
     * @param newAddress The new address to add.
     */
    private void updateAddresses(ArrayList<Addresses> addresses, Addresses newAddress) {
        addresses.removeIf(addr -> addr.getTitle().equals(getCurrentAddress().getTitle()));
        addresses.add(newAddress);
        setCurrentAddress(newAddress);
    }

    /**
     * Cancels the editing process and returns to the address book page.
     * @param event The event that triggered this method.
     * @throws IOException If navigation fails.
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
        toDefault(event, "AddressBook.fxml", "Address Book Page");
    }

    /**
     * Navigates back to the main menu page.
     * @param event The event that triggered this method.
     * @throws IOException If navigation fails.
     */
    @FXML
    void toMenuPage(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
