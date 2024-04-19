package pizza.project.demo0;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controls the main menu UI, handling navigation and dynamic UI updates based on the user settings.
 */
public class menuController extends Controller implements Initializable {

    @FXML
    private Text userOrPasNotFound;
    @FXML
    private Label welcomeUser;
    @FXML
    private Pane colorDisplayPane;
    @FXML
    private ColorPicker BGColorPicker;

    @FXML
    private Label timeLabel;

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
        welcomeUser.setTextFill(getCurrentUser().getFontcolor());
        timeLabel.setTextFill(getCurrentUser().getFontcolor());
        welcomeUser.setText("Welcome " + getCurrentUser().getUsername());
        updateTimeLabel();
        startClock();
    }

    private void updateTimeLabel() {
        // Simple date format for the time string in 12-hour format with AM/PM
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        // Set the text of the time label to the formatted current time
        timeLabel.setText(simpleDateFormat.format(new Date()));
    }

    private void startClock() {
        // Timeline that calls updateTimeLabel() every second
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> updateTimeLabel()),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
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

    /**
     * Navigates to the Create Order page.
     * @param event The action event that triggered this method.
     * @throws IOException If an error occurs during the page transition.
     */
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
