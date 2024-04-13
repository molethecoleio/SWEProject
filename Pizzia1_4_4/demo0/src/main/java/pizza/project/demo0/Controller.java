package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    //private User currentUser = new User("errorYouShouldNotSeeMe","password","molethecole.io@gmail.com");
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String placeHolder;

    private static User currentUser = null;

    private static Addresses currentAddress = null;

    private static Question currentQuestion = null;

    @FXML
    private Pane colorDisplayPane;

    // Method to set the Stage object
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void toDefault(ActionEvent event, String fileName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        root = fxmlLoader.load(getClass().getResource(fileName));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Project - " + title);
        stage.show();
    }

    @FXML
    public void toDefault(ActionEvent event, String fileName, String title, User currentUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        this.currentUser = currentUser;
        root = fxmlLoader.load(getClass().getResource(fileName));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Project - " + title);
        stage.show();
    }

    User getCurrentUser(){
        return currentUser;
    }
    void setCurrentUser(User x){currentUser = x;}

    Addresses getCurrentAddress(){
        return currentAddress;
    }
    void setCurrentAddress(Addresses x){ currentAddress = x; }

    Question getCurrentQuestion(){ return currentQuestion; }
    void setCurrentQuestion(Question x){ currentQuestion = x; }
}
    /*
    @FXML
    private Text userOrPasNotFound;

    @FXML
    private Text welcomeUser;

    @FXML
    void toAccountSettings(ActionEvent event) {

    }

    @FXML
    void toAddressBook(ActionEvent event) {

    }

    @FXML
    void toCreateOrder(ActionEvent event) {

    }


    @FXML
    void toOrderHistory(ActionEvent event) {

    }

    @FXML
    public void toSignupPage(ActionEvent event) throws IOException {
        toDefault(event, "signUp.fxml", "Sign Up Page");
    }
    @FXML
    void toForgotPass(ActionEvent event) throws IOException {
        toDefault(event, "forgotPass.fxml", "Login Page");
    }

    @FXML
    void toForgotUser(ActionEvent event) throws IOException {
        toDefault(event, "signUp.fxml", "Sign Up Page");
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
        //welcomeUser.setVisible(false);
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    @FXML
    void recoverUsername(ActionEvent event) {

    }

    @FXML
    void recoverPassword(ActionEvent event) {

    }

    @FXML
    void Signup(ActionEvent event) {

    }



    private ArrayList<User> loginInfo;

    public Controller() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    private TextField usernameTxtField;

    @FXML
    private TextField passwordTxtField;

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
     */