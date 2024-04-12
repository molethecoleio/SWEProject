package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class editAddressController extends Controller implements Initializable {

    @FXML
    private TextField aptTxtField;

    @FXML
    private RadioButton billingRadio;

    @FXML
    private RadioButton bothRadio;

    @FXML
    private TextField cityTxtFeild;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private RadioButton deliveryRadio;

    @FXML
    private TextField phoneNumTxtField;

    @FXML
    private ChoiceBox<String> stateDropDown;

    @FXML
    private TextField streetAddressTxtField;

    @FXML
    private Text topMessage;

    @FXML
    private TextField zipTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateDropDown.getItems().addAll(states);
        stateDropDown.setOnAction(this::getState);

        streetAddressTxtField.setText(getCurrentAddress().getStreetAddress());
        aptTxtField.setText(getCurrentAddress().getApartment());
        stateDropDown.setValue(getCurrentAddress().getState());
        cityTxtFeild.setText(getCurrentAddress().getCity());
        zipTxtField.setText(String.valueOf(getCurrentAddress().getZip()));
        phoneNumTxtField.setText(getCurrentAddress().getPhone());

        int temp = getCurrentAddress().getAddressType();

        if(temp == 1){
            billingRadio.setSelected(true);
        }
        else if(temp == 2){
            deliveryRadio.setSelected(true);
        }
        else{
            bothRadio.setSelected(true);
        }
    }

    public void getState(ActionEvent event){
        String state = stateDropDown.getValue();

    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        toDefault(event, "AddressBook.fxml", "Address Book Page");
    }

    @FXML
    void saveAddress(ActionEvent event) throws IOException {
        int addressType = 0;
        boolean bool = true;
        if(streetAddressTxtField.equals("")){
            topMessage.setText("Street Address Empty");
            bool = false;
        }

        if(stateDropDown.getValue() == null || stateDropDown.getValue().equals("")){
            topMessage.setText("State Is Empty");
            bool = false;
        }

        if(cityTxtFeild.getText().equals("")){
            topMessage.setText("City Is Empty");
            bool = false;
        }

        if(zipTxtField.getText().equals("")){
            topMessage.setText("State Is Empty");
            bool = false;
        }

        if(phoneNumTxtField.getText().equals("")){
            topMessage.setText("Phone Number is empty");
            bool = false;
        }

        if(billingRadio.isSelected()){
            addressType = 1;
        }
        if(deliveryRadio.isSelected()){
            addressType = 2;
        }
        if(bothRadio.isSelected()){
            addressType = 3;
        }
        if(addressType == 0){
            topMessage.setText("Select Address Type");
            bool = false;
        }

        if(bool){
            Addresses newAddress = new Addresses(getCurrentUser().getUserID(),addressType,streetAddressTxtField.getText(), aptTxtField.getText(), cityTxtFeild.getText(), stateDropDown.getValue(), Integer.parseInt(zipTxtField.getText()), phoneNumTxtField.getText());

            ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
            System.out.println("size before remove: " + addresses.size());
            //addresses.remove(getCurrentAddress());


            for(int x = 0; x < addresses.size(); x++){
                if(addresses.get(x).getTitle().equals(getCurrentAddress().getTitle())){
                    addresses.remove(x);
                }
            }
            System.out.println("size after remove: " + addresses.size());
            setCurrentAddress(newAddress);
            addresses.add(newAddress);
            addressLoader.writeResponseToFile(addresses);

            toDefault(event, "AddressBook.fxml", "Address Book");
        }
    }

    @FXML
    void toMenuPage(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

    String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
            "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
            "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };

}
