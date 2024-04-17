package pizza.project.demo0;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static pizza.project.demo0.IDandPasswords.writeUsersFile;

/**
 * Controller for handling the reset of a user's username in the application.
 */
public class resetUserNController extends Controller implements Initializable {

    private ArrayList<User> loginInfo;

    /**
     * Constructs a ResetUserNController, initializing the list of users from IDandPasswords.
     */
    public resetUserNController() {
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    private Label messageTxt;

    @FXML
    private Text confirmUserN;

    @FXML
    private Text userTxt;

    @FXML
    private TextField newUserOne;

    @FXML
    private TextField newUserTwo;

    @FXML
    private Pane colorDisplayPane;

    /**
     * Initializes the controller class, setting up the initial state of UI components based on the current user's settings.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageTxt.setTextFill(getCurrentUser().getFontcolor());
        confirmUserN.setFill(getCurrentUser().getFontcolor());
        userTxt.setFill(getCurrentUser().getFontcolor());
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Handles the action of changing the username.
     * Validates the two inputs for username and updates it if they match, providing user feedback through the process.
     * @param event The event that triggered this method.
     * @throws IOException If there is an error writing to the file or navigating pages.
     */
    @FXML
    void changeUsername(ActionEvent event) throws IOException {
        if (newUserOne.getText().isEmpty() || newUserTwo.getText().isEmpty()) {
            messageTxt.setText("Username fields cannot be empty.");
            return;
        }

        if (!newUserOne.getText().equals(newUserTwo.getText())) {
            messageTxt.setText("Usernames don't match.");
            return;
        }

        System.out.println("Working username reset...");
        getCurrentUser().setUsername(newUserOne.getText());
        writeUsersFile("database.txt", loginInfo);
        startCountdown(event);
    }

    /**
     * Starts a countdown after updating the username, then navigates to the menu page.
     * @param event The event that triggered this method.
     */
    private void startCountdown(ActionEvent event) {
        final int[] countdown = {5}; // Countdown from 5
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            if (countdown[0] > 1) {
                messageTxt.setText("Username changed, signing in " + --countdown[0]);
                delay.playFromStart();
            } else {
                messageTxt.setText("And awayyyy we go!");
                try {
                    toMenu(event);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        delay.play();
    }

    /**
     * Redirects the user to the login page.
     * @param event The event that triggered this method.
     * @throws IOException If there is an error during the navigation.
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    /**
     * Redirects the user to the main menu page after a successful username update.
     * @param event The event that triggered this method.
     * @throws IOException If there is an error during the navigation.
     */
    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
