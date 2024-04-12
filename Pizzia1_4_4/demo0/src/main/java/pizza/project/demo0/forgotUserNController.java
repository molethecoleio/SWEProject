package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class forgotUserNController extends Controller{

    private ArrayList<User> loginInfo;

    public forgotUserNController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    private TextField emailTxtField;

    @FXML
    private Text errorTxt;

    @FXML
    private TextField pwdTxtField;

    @FXML
    void recoverUsername(ActionEvent event) throws IOException {
        boolean bool = false;
        for (User x: loginInfo) {
            if(x.getEmail().equals(emailTxtField.getText()) && x.getPassword().equals(pwdTxtField.getText())){
                bool = true;
                setCurrentUser(x);
                placeHolder = "forgotUserN";
                toDefault(event, "oneTimePin.fxml","One Time Pin Page");
            }
        }
        if(!bool){
            errorTxt.setText("E-Mail/Password Incorrect or Not Found");
            errorTxt.setFill(Paint.valueOf("red"));
        }

    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event,"login.fxml", "Login Page", null);
    }

}
