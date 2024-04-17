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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static pizza.project.demo0.IDandPasswords.writeUsersFile;

/**
 * Controller class to manage the user interface for resetting a user's password.
 */
public class resetPassController extends Controller implements Initializable {

    @FXML
    private TextField pasTwoTxt;

    @FXML
    private TextField passOneTxt;

    @FXML
    private Text confirmPass;

    @FXML
    private Label messageTxt;

    @FXML
    private Text newPass;

    @FXML
    private Pane colorPane;

    private ArrayList<User> loginInfo;

    /**
     * Constructor to initialize the controller with user data.
     */
    public resetPassController() {
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    /**
     * Initializes UI components with properties specific to the current user's settings.
     * @param url The URL location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        confirmPass.setFill(getCurrentUser().getFontcolor());
        newPass.setFill(getCurrentUser().getFontcolor());
        messageTxt.setTextFill(getCurrentUser().getFontcolor());
    }

    /**
     * Handles the password change operation with validation and feedback.
     * @param event The event that triggered this method.
     * @throws IOException If there is an error writing to the file or during the page transition.
     */
    @FXML
    void changePass(ActionEvent event) throws IOException {
        if (passOneTxt.getText().isEmpty() || pasTwoTxt.getText().isEmpty()) {
            messageTxt.setText("Both password fields must be filled.");
            return;
        }

        if (!passOneTxt.getText().equals(pasTwoTxt.getText())) {
            messageTxt.setText("Passwords don't match.");
            return;
        }

        getCurrentUser().setPassword(passOneTxt.getText());
        writeUsersFile("database.txt", loginInfo);
        messageTxt.setText("Password successfully updated.");
        startCountdown(event);
    }

    /**
     * Starts a countdown after successful password update, leading to a page transition.
     * @param event The event that triggered this method.
     */
    private void startCountdown(ActionEvent event) {
        final int[] countdown = {5}; // Countdown from 5
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            if (countdown[0] > 1) {
                messageTxt.setText("Signing in " + --countdown[0]);
                delay.playFromStart();
            } else {
                redirectToMenu(event);
            }
        });
        delay.play();
    }

    /**
     * Redirects the user to the main menu page after successful password update.
     * @param event The event that triggered this method.
     */
    private void redirectToMenu(ActionEvent event) {
        try {
            toMenu(event);
        } catch (IOException e) {
            e.printStackTrace();
            messageTxt.setText("Error during redirection to menu.");
        }
    }

    /**
     * Directs the user back to the login page.
     * @param event The event that triggered this redirection.
     * @throws IOException If there is an error during the navigation.
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    /**
     * Directs the user to the main menu after successful operations.
     * @param event The event that triggered this redirection.
     * @throws IOException If there is an error during the navigation.
     */
    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
