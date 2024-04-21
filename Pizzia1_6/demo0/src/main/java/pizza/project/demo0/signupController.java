package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for handling the sign-up functionality of the application.
 */
public class signupController extends Controller {

    @FXML
    private TextField emailTxtField; // TextField for user email input.

    @FXML
    private TextField confirmPasswordTxtField; // TextField for confirming the password.

    @FXML
    private TextField passwordTxtField; // TextField for user password input.

    @FXML
    private TextField usernameTxtField; // TextField for user username input.

    @FXML
    private Text errorsTxt; // Text display for showing error messages.

    private ArrayList<User> loginInfo; // List of user credentials.

    /**
     * Constructor that initializes the login information from stored data.
     */
    public signupController() {
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    /**
     * Attempts to sign up a new user with the information provided in the form.
     * Redirects to the menu page upon successful sign-up.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void Signup(ActionEvent event) throws IOException {
        if (!validateFields()) {
            resetTxtFields();
            return;
        }

        User newUser = new User(usernameTxtField.getText(), passwordTxtField.getText(), emailTxtField.getText(), Color.LIGHTGRAY, Color.BLACK);
        setCurrentUser(newUser);
        loginInfo.add(newUser);
        System.out.println("New User Being added, User Data: " + newUser);
        IDandPasswords.writeUserToFile("database.txt", newUser);
        toMenu(event);
    }

    /**
     * Validates all input fields for new user registration.
     * Sets error messages and returns false if any validation fails.
     *
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateFields() {
        if (emailTxtField.getText().isEmpty() || !emailTxtField.getText().contains("@") || !emailTxtField.getText().contains(".") || emailTxtField.getText().indexOf('@') > emailTxtField.getText().indexOf('.')) {
            errorsTxt.setText("Invalid Email Format");
            errorsTxt.setFill(Paint.valueOf("Red"));
            return false;
        }

        if (usernameTxtField.getText().isEmpty()) {
            errorsTxt.setText("Username Is Empty");
            errorsTxt.setFill(Paint.valueOf("Red"));
            return false;
        }

        if (usernameTxtField.getText().contains("admin") || usernameTxtField.getText().contains("Admin")) {
            errorsTxt.setText("Username Cannot Contain The Word \"Admin\"");
            errorsTxt.setFill(Paint.valueOf("Red"));
            return false;
        }

        if (passwordTxtField.getText().isEmpty()) {
            errorsTxt.setText("Password is Empty");
            errorsTxt.setFill(Paint.valueOf("Red"));
            resetTxtFields();
            return false;
        }

        if (!passwordTxtField.getText().equals(confirmPasswordTxtField.getText())) {
            errorsTxt.setText("Passwords Must Match");
            errorsTxt.setFill(Paint.valueOf("Red"));

            return false;
        }

        for (User user : loginInfo) {
            if (user.getEmail().equals(emailTxtField.getText())) {
                errorsTxt.setText("Email already in use");
                errorsTxt.setFill(Paint.valueOf("Red"));
                return false;
            }
        }

        //resetTxtFields();
        return true;
    }

    /**
     * Clears all text fields.
     */
    void resetTxtFields() {
        emailTxtField.setText("");
        usernameTxtField.setText("");
        passwordTxtField.setText("");
        confirmPasswordTxtField.setText("");
    }

    /**
     * Redirects to the menu page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

    /**
     * Redirects to the login page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

}
