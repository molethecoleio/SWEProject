package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class newAddressController extends Controller implements Initializable {

    @FXML
    private TextField aptTxtField;

    @FXML
    private RadioButton billingRadio;

    @FXML
    private RadioButton bothRadio;

    @FXML
    private ChoiceBox<String> cityDropDown;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private RadioButton deliveryRadio;

    @FXML
    private Text emailMessage;

    @FXML
    private Text emailMessage1;

    @FXML
    private Text emailMessage11;

    @FXML
    private Text passwordMessage;

    @FXML
    private TextField phoneNumTxtField;

    @FXML
    private Text repasswordMessage;

    @FXML
    private ChoiceBox<String> stateDropDown;

    @FXML
    private TextField streetAddressTxtField;

    @FXML
    private Text topMessage;

    @FXML
    private Text usernameMessage;

    @FXML
    private Text usernameMessage1;

    @FXML
    private TextField zipTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateDropDown.getItems().addAll(states);
        String[] selectState = {"Select State First"};
        cityDropDown.getItems().addAll(selectState);
        stateDropDown.setOnAction(this::getState);
    }

    public void getState(ActionEvent event){
        String state = stateDropDown.getValue();
        cityDropDown.getItems().clear();
        //cityDropDown.getItems().addAll(getCities.getCity(state));
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveAddress(ActionEvent event) {

    }

    @FXML
    void toMenuPage(ActionEvent event) {

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
