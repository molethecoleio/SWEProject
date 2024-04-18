package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class newCardController {

    @FXML
    private ToggleGroup addressType;

    @FXML
    private ToggleGroup addressType1;

    @FXML
    private RadioButton americanRadio;

    @FXML
    private TextField cardNumTxtField;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private TextField cvvTxtField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton discoverRadio;

    @FXML
    private Text labelApt1;

    @FXML
    private Text labelCVV;

    @FXML
    private Text labelCard;

    @FXML
    private Text labelDate;

    @FXML
    private RadioButton masterRadio;

    @FXML
    private TextField nameTxtField;

    @FXML
    private Label topMessage;

    @FXML
    private RadioButton visaRadio;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveCard(ActionEvent event) {

    }

    @FXML
    void toMenuPage(ActionEvent event) {

    }

}
