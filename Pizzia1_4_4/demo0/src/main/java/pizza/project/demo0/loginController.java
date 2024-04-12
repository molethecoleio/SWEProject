package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.ArrayList;

public class loginController extends Controller{

    @FXML
    private Text userOrPasNotFound;

    @FXML
    private Text welcomeUser;

    @FXML
    private ImageView imageVIew;

    private ArrayList<User> loginInfo;

    @FXML
    private TextField usernameTxtField;

    @FXML
    private TextField passwordTxtField;


    public loginController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        //Reading from the file
        IDandPasswords.readFromFile("database.txt");
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    public void toSignupPage(ActionEvent event) throws IOException {
        toDefault(event, "signUp.fxml", "Recover Password Page");
    }

    @FXML
    void toForgotPass(ActionEvent event) throws IOException {
        toDefault(event, "forgotPass.fxml", "Login Page");
    }

    @FXML
    void toForgotUser(ActionEvent event) throws IOException {
        toDefault(event, "forgotUser.fxml", "Recover Username Page");
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
        //welcomeUser.setVisible(false);
    }

    @FXML
    void testLogin(ActionEvent event) throws IOException {
        //getting the text from both text fields and storing them in variables, the password was done differently because it is "hidden"(all chars are replaced with '*') and needed to have the .getPassword() method to be called to decode it
        String userInput = usernameTxtField.getText();
        String passwordInput = passwordTxtField.getText();

        // Verifying user ID and password
        boolean userFound = false;
        for (User user : loginInfo) {
            if (user.getUsername().equals(userInput)) {
                userFound = true;
                if (user.getPassword().equals(passwordInput)) {

                    setCurrentUser(user);
                    toMenu(event);

                    return; // Exit method
                } else {
                    // Username found but incorrect password
                    System.out.println("Incorrect password");
                    userOrPasNotFound.setVisible(true);
                    userOrPasNotFound.setText("Incorrect Password");
                    return; // Exit method
                }
            }
        }
        // Username not found
        if (!userFound) {
            System.out.println("Username not found");
            userOrPasNotFound.setVisible(true);
            userOrPasNotFound.setText("Username Not Found");
        }
    }

    void printArr() {
        for(User cole: loginInfo){
            System.out.println(cole.getUsername() + cole.getPassword());
        }
    }

}
