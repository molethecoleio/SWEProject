package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuController extends Controller implements Initializable {

    @FXML
    private Text userOrPasNotFound;

    @FXML
    private Text welcomeUser;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ColorPicker BGColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));

        userOrPasNotFound.setFill(getCurrentUser().getFontcolor());
        welcomeUser.setFill(getCurrentUser().getFontcolor());
    }

    @FXML
    void toAccountSettings(ActionEvent event) throws IOException {
        toDefault(event,"toAcctSettings.fxml", "Login Page");
    }

    @FXML
    void toAddressBook(ActionEvent event) throws IOException {
        toDefault(event, "AddressBook.fxml", "Address Book Page");
    }

    @FXML
    void toCreateOrder(ActionEvent event) throws IOException {
        toDefault(event, "createOrder.fxml", "Create Order Page");
    }

    @FXML
    void toFAQs(ActionEvent event) throws IOException {
        toDefault(event, "FAQs.fxml", "FAQs Page");
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event,"login.fxml", "Login Page", null);
    }

    @FXML
    void toOrderHistoryOrder(ActionEvent event) {

    }

}
