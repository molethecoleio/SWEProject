package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class signupController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<User> loginInfo;

    public signupController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }


    @FXML
    private TextField emailTxtFeild;

    @FXML
    private TextField confirmPasswordTxtField;

    @FXML
    private Text errorsTxt;

    @FXML
    private TextField passwordTxtField;

    @FXML
    private TextField usernameTxtField;


    @FXML
    public void toDefault(ActionEvent event, String fileName, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fileName));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Project - " + title);
        stage.show();
    }

    @FXML
    void Signup(ActionEvent event) throws IOException {
        boolean checker = false;
        for (User x: loginInfo) {
            if(x.getEmail().equals(emailTxtFeild.getText())){
                checker = true;
                errorsTxt.setText("Email being used on a different account");
                errorsTxt.setFill(Paint.valueOf("Red"));
                resetTxtFeilds();
            }
        }
        if(!confirmPasswordTxtField.getText().equals(passwordTxtField.getText())){
            errorsTxt.setText("Passwords Dont Match");
            errorsTxt.setFill(Paint.valueOf("Red"));
            resetTxtFeilds();
        }
        else if(!checker){
            errorsTxt.setText("Account Made Go To Login Screen.");
            errorsTxt.setFill(Paint.valueOf("Black"));
            Color white = Color.WHITE;
            Color black = Color.BLACK;
            User newUser = new User(usernameTxtField.getText(), passwordTxtField.getText(), emailTxtFeild.getText(), white, black);
            setCurrentUser(newUser);
            loginInfo.add(newUser);
            System.out.println("New User Being added, User Data: " + newUser);
            IDandPasswords.writeUserToFile("database.txt", newUser);
            toMenu(event);
        }
     }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
        //welcomeUser.setVisible(false);
    }

    void resetTxtFeilds(){
        emailTxtFeild.setText("");
        usernameTxtField.setText("");
        passwordTxtField.setText("");
    }
}
