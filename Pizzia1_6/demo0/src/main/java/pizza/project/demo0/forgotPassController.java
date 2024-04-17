package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for handling the password recovery process.
 */
public class forgotPassController extends Controller {

    @FXML
    private TextField emailTxtField; // Text field for user's email input.

    @FXML
    private TextField usernameTxtField; // Text field for user's username input.

    @FXML
    private Label errorTxt; // Text field for displaying error messages.

    private ArrayList<User> loginInfo; // List storing login information of all users.

    /**
     * Constructor initializes login information from IDandPasswords class.
     */
    public forgotPassController() {
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    /**
     * Attempts to recover the password for the user based on email and username provided.
     * Redirects to reset password page if credentials are found, otherwise shows error.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void recoverPassword(ActionEvent event) throws IOException {
        boolean found = false;
        if (emailTxtField.getText().isEmpty()) {
            errorTxt.setTextFill(Paint.valueOf("red"));
            errorTxt.setText("Email Is Empty");
            resetTxtFields();
        } else {
            for (User user : loginInfo) {
                if (user.getEmail().equals(emailTxtField.getText()) && user.getUsername().equals(usernameTxtField.getText())) {
                    found = true;
                    setCurrentUser(user);
                    toDefault(event, "resetPass.fxml", "Reset Password Page");
                }
            }
        }

        if (!found) {
            errorTxt.setTextFill(Paint.valueOf("red"));
            errorTxt.setText("Email or Username Not Found");
            resetTxtFields();
        }
    }

    /**
     * Clears all text fields in the form.
     */
    void resetTxtFields() {
        emailTxtField.setText("");
        usernameTxtField.setText("");
    }

    /**
     * Redirects the user to the login page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }
}