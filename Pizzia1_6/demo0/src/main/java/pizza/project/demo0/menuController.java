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

/**
 * Controls the main menu UI, handling navigation and dynamic UI updates based on the user settings.
 */
public class menuController extends Controller implements Initializable {

    @FXML
    private Text userOrPasNotFound;
    @FXML
    private Text welcomeUser;
    @FXML
    private Pane colorDisplayPane;
    @FXML
    private ColorPicker BGColorPicker;

    /**
     * Initializes the controller, setting up the UI components based on the current user's preferences.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        userOrPasNotFound.setFill(getCurrentUser().getFontcolor());
        welcomeUser.setFill(getCurrentUser().getFontcolor());
    }

    /**
     * Navigates to the Account Settings page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
    @FXML
    void toAccountSettings(ActionEvent event) throws IOException {
        toDefault(event, "toAcctSettings.fxml", "Account Settings Page");
    }

    /**
     * Navigates to the Address Book page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
    @FXML
    void toAddressBook(ActionEvent event) throws IOException {
        toDefault(event, "AddressBook.fxml", "Address Book Page");
    }

    /**
     * Navigates to the Create Order page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
    @FXML
    void toCreateOrder(ActionEvent event) throws IOException {
        toDefault(event, "createOrder.fxml", "Create Order Page");
    }

    @FXML
    void toCards(ActionEvent event) throws IOException {
        toDefault(event, "CardBook.fxml", "Create Order Page");
    }

    /**
     * Navigates to the FAQs page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
    @FXML
    void toFAQs(ActionEvent event) throws IOException {
        toDefault(event, "FAQs.fxml", "FAQs Page");
    }

    /**
     * Logs the user out and returns to the Login Page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    /**
     * Navigates to the Order History page.
     * @param event The action event that triggered this method.
     */
    @FXML
    void toOrderHistory(ActionEvent event) throws IOException {
        //toDefault(event, "OrderHistory.fxml", "Order History Page");
    }
}
