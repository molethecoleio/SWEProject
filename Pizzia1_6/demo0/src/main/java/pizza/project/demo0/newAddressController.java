package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
 * Controller class for managing the creation of new addresses within the application.
 */
public class newAddressController extends Controller implements Initializable {

    @FXML private TextField aptTxtField;
    @FXML private RadioButton billingRadio;
    @FXML private RadioButton bothRadio;
    @FXML private TextField cityTxtField;
    @FXML private Pane colorDisplayPane;
    @FXML private RadioButton deliveryRadio;
    @FXML private TextField phoneNumTxtField;
    @FXML private ChoiceBox<String> stateDropDown;
    @FXML private TextField streetAddressTxtField;
    @FXML private Label topMessage;
    @FXML private TextField zipTxtField;
    @FXML private Text labelApt, labelCity, labelOptional, labelPhoneNum, labelState, labelStreet, labelZip;

    private final String[] states = {/* State names here */};

    /**
     * Initializes the controller, setting UI components to reflect current user settings and filling state dropdown.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        stateDropDown.getItems().addAll(states);
        stateDropDown.setOnAction(this::getState);

        Text[] textFields = {labelZip, labelStreet, labelState, labelPhoneNum, labelOptional, labelCity, labelApt};
        for (Text text : textFields) {
            text.setFill(getCurrentUser().getFontcolor());
        }

        topMessage.setTextFill(getCurrentUser().getFontcolor());

        RadioButton[] radios = {billingRadio, deliveryRadio, bothRadio};
        for (RadioButton radio : radios) {
            radio.setTextFill(getCurrentUser().getFontcolor());
        }
    }

    /**
     * Handles state selection from the dropdown.
     *
     * @param event The action event triggered when a state is selected.
     */
    public void getState(ActionEvent event) {
        // Implementation to handle state selection, currently unused
    }

    /**
     * Cancels the creation of a new address and navigates back to the address book page.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
        toDefault(event, "AddressBook.fxml", "Address Book Page");
    }

    /**
     * Saves the new address details entered by the user and navigates back to the address book.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during file operations or navigation.
     */
    @FXML
    void saveAddress(ActionEvent event) throws IOException {
        if (validateInputs()) {
            Addresses newAddress = new Addresses(getCurrentUser().getUserID(), determineAddressType(),
                    streetAddressTxtField.getText(), aptTxtField.getText(),
                    cityTxtField.getText(), stateDropDown.getValue(),
                    Integer.parseInt(zipTxtField.getText()), phoneNumTxtField.getText());

            ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
            addresses.add(newAddress);
            addressLoader.writeResponseToFile(addresses);
            toDefault(event, "AddressBook.fxml", "Address Book");
        }
    }

    /**
     * Validates the form inputs for creating a new address.
     *
     * @return true if all inputs are valid, otherwise false.
     */
    private boolean validateInputs() {
        if (streetAddressTxtField.getText().isEmpty() || stateDropDown.getValue().isEmpty() ||
                cityTxtField.getText().isEmpty() || zipTxtField.getText().isEmpty() || phoneNumTxtField.getText().isEmpty()) {
            topMessage.setText("Please fill all required fields.");
            return false;
        }
        if (billingRadio.isSelected() || deliveryRadio.isSelected() || bothRadio.isSelected()) {
            return true;
        } else {
            topMessage.setText("Please select an address type.");
            return false;
        }
    }

    /**
     * Determines the address type based on the radio button selection.
     *
     * @return the address type as an integer.
     */
    private int determineAddressType() {
        if (billingRadio.isSelected()) {
            return 1;
        } else if (deliveryRadio.isSelected()) {
            return 2;
        } else if (bothRadio.isSelected()) {
            return 3;
        } else {
            return 0; // Default or undefined
        }
    }

    /**
     * Navigates back to the main menu page.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If navigation fails.
     */
    @FXML
    void toMenuPage(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
