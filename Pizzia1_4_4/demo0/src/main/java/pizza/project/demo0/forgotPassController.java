package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class forgotPassController extends Controller {

    @FXML
    private TextField emailTxtField;


    @FXML
    private TextField usernameTxtField;


    @FXML
    private Text errorTxt;

    private ArrayList<User> loginInfo;

    public forgotPassController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    void recoverPassword(ActionEvent event) throws IOException {
        boolean bool = false;
        for (User x: loginInfo) {
            if(x.getEmail().equals(emailTxtField.getText()) && x.getUsername().equals(usernameTxtField.getText())){
                bool = true;
                placeHolder = "forgotPass";
                setCurrentUser(x);
                toDefault(event,"oneTimePin.fxml", "One Time Pin");
            }
        }
        if(!bool){
            errorTxt.setFill(Paint.valueOf("red"));
            errorTxt.setText("Email or Username Not Found");
            resetTxtFeilds();
        }
    }

    void resetTxtFeilds(){
        emailTxtField.setText("");
        usernameTxtField.setText("");
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

    @FXML
    void toOneTimePin(ActionEvent event) throws IOException {
        toDefault(event, "oneTimePin.fxml", "OTP Page");
    }
}
