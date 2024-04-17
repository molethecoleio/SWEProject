package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for handling the login process.
 */
public class loginController extends Controller {

    @FXML
    private Text userOrPasNotFound; // Text field for displaying login errors.

    @FXML
    private Text welcomeUser; // Text field for displaying welcome message to the user.

    @FXML
    private ImageView imageView; // Image view for displaying user-related images.

    private ArrayList<User> loginInfo; // List storing login information of all users.

    @FXML
    private TextField usernameTxtField; // Text field for user's username input.

    @FXML
    private TextField passwordTxtField; // Text field for user's password input.

    /**
     * Constructor initializes login information from IDandPasswords class.
     * Reads user information from the database file.
     */
    public loginController() {
        IDandPasswords.readFromFile("database.txt");
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    /**
     * Redirects the user to the sign-up page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    public void toSignupPage(ActionEvent event) throws IOException {
        toDefault(event, "signUp.fxml", "Sign Up Page");
    }

    /**
     * Redirects the user to the forgot password page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void toForgotPass(ActionEvent event) throws IOException {
        toDefault(event, "forgotPass.fxml", "Forgot Password Page");
    }

    /**
     * Redirects the user to the forgot username page.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void toForgotUser(ActionEvent event) throws IOException {
        toDefault(event, "forgotUser.fxml", "Forgot Username Page");
    }

    /**
     * Redirects the user to the main menu page after a successful login.
     * This method is called when the user's credentials have been verified to be correct,
     * transitioning them from the login page to the menu page where user-specific functionalities are available.
     *
     * @param event the event that triggered this method, typically a button click
     * @throws IOException if an error occurs during the page transition
     */
    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

    /**
     * Attempts to log the user in with the provided username and password.
     * Redirects to the menu page if credentials are correct, otherwise displays error.
     *
     * @param event the event that triggered this method
     * @throws IOException if an input/output error occurs
     */
    @FXML
    void testLogin(ActionEvent event) throws IOException {
        String userInput = usernameTxtField.getText();
        String passwordInput = passwordTxtField.getText();

        boolean userFound = false;
        for (User user : loginInfo) {
            if (user.getUsername().equals(userInput)) {
                userFound = true;
                if (user.getPassword().equals(passwordInput)) {
                    setCurrentUser(user);
                    toMenu(event);
                    return; // Exit method
                } else {
                    userOrPasNotFound.setVisible(true);
                    userOrPasNotFound.setText("Incorrect Password");
                    return; // Exit method
                }
            }
        }
        if (!userFound) {
            userOrPasNotFound.setVisible(true);
            userOrPasNotFound.setText("Username Not Found");
        }
    }

    /**
     * Utility method for debugging purposes to print all usernames and passwords in the loginInfo list.
     */
    void printArr() {
        for(User user: loginInfo) {
            System.out.println(user.getUsername() + user.getPassword());
        }
    }


}
