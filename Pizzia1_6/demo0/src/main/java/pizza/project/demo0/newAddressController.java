package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Controller class for managing the creation of new addresses within the application.
 */
public class newAddressController extends Controller implements Initializable {

    @FXML private TextField aptTxtField;
    @FXML private RadioButton billingRadio;
    @FXML private RadioButton bothRadio;

    @FXML
    private TextField cityTxtField;

    @FXML private Pane colorDisplayPane;
    @FXML private RadioButton deliveryRadio;
    @FXML private TextField phoneNumTxtField;
    @FXML private ChoiceBox<String> stateDropDown;
    @FXML private TextField streetAddressTxtField;
    @FXML private Label topMessage;
    @FXML private TextField zipTxtField;
    @FXML private Text labelApt, labelCity, labelOptional, labelPhoneNum, labelState, labelStreet, labelZip;


    private final String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void navigate(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        if(isEditing){
            loadCurrentAddress();
        }
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
     * Handles state selection from the dropdown.
     *
     * @param event The action event triggered when a state is selected.
     */
    public void getState(ActionEvent event) {

    }

    /**
     * Cancels the creation of a new address and navigates back to the address book page.
     *
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during navigation.
     */
    @FXML
    void cancel(ActionEvent event) throws IOException {
        if(isPaymentProcessing){
            toDefault(event, "PaymentProcessor.fxml", "Payment Processor");
        } else{
            toDefault(event, "AddressBook.fxml", "Address Book");
        }
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

            if(isEditing) {
                Addresses removeAddress = getCurrentAddress();
                for (Iterator<Addresses> iterator = addresses.iterator(); iterator.hasNext(); ) {
                    Addresses address = iterator.next();
                    // Check the condition to match the address you want to remove
                    if (address.getTitle().equals(removeAddress.getTitle()) && // replace getField1 with actual method
                            address.getOwnerID() == (removeAddress.getOwnerID())) { // replace getField2 with actual method
                        iterator.remove();
                        break;  // Remove this line if there can be multiple matching addresses
                    }
                }
            }

            addresses.add(newAddress);
            addressLoader.writeResponseToFile(addresses);
            if(isPaymentProcessing){
                toDefault(event, "PaymentProcessor.fxml", "Payment Processor");
            } else{
                toDefault(event, "AddressBook.fxml", "Address Book");
            }

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
        if(isPaymentProcessing){
            toDefault(event, "PaymentProcessor.fxml", "Payment Processor");
        } else{
            toDefault(event, "menu.fxml", "Menu Page");
        }
    }
}
