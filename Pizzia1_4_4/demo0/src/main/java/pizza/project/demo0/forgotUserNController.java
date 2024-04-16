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
 * Controller class for handling the username recovery process.
 */
public class forgotUserNController extends Controller{

    private ArrayList<User> loginInfo; // List storing login information of all users.

    /**
     * Constructor initializes login information from IDandPasswords class.
     */
    public forgotUserNController() {
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    private TextField emailTxtField; // Text field for user's email input.

    @FXML
    private Label errorTxt; // Text field for displaying error messages.

    @FXML
    private TextField pwdTxtField; // Text field for user's password input.

    /**
     * Attempts to recover the username for the user based on email and password provided.
     * Redirects to reset username page if credentials are found, otherwise shows error.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void recoverUsername(ActionEvent event) throws IOException {
        boolean found = false;
        if(emailTxtField.getText().isEmpty()){
            errorTxt.setText("E-Mail Is Empty");
            errorTxt.setTextFill(Paint.valueOf("red"));
        } else {
            for (User user : loginInfo) {
                if (user.getEmail().equals(emailTxtField.getText()) && user.getPassword().equals(pwdTxtField.getText())) {
                    found = true;
                    setCurrentUser(user);
                    toDefault(event, "resetUserN.fxml", "Reset Username Page");
                }
            }
        }

        if(!found){
            errorTxt.setText("E-Mail/Password Incorrect or Not Found");
            errorTxt.setTextFill(Paint.valueOf("red"));
        }
    }

    /**
     * Redirects the user to the login page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event,"login.fxml", "Login Page", null);
    }
}
