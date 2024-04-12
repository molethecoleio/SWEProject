package pizza.project.demo0;
import java.net.URL;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.text.Text;

public class oneTimePinController extends Controller implements Initializable {

    @FXML
    private TextField otpTxtFeild;

    @FXML
    private Text emailSentTxt;

    private int pin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        emailSentTxt.setText("An Email has been sent to " + getCurrentUser().getEmail());
        pin = genPin();
        System.out.println("OTP: " + pin);
    }


    public int genPin(){
        Random random = new Random();
        // Generate a random number and ensure it's always 7 digits by adding 1000000
        return 1000000 + random.nextInt(9000000);
    }


    @FXML
    void testPin(ActionEvent event) throws IOException {
        if(Integer.parseInt(otpTxtFeild.getText()) == pin){
            if(placeHolder.equals("forgotPass")){
                toResetPass(event);
            }
            else if(placeHolder.equals("forgotUserN")){
                toResetUserN(event);
            }
        }
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml","Login Page", null);
    }

    @FXML
    void toResetPass(ActionEvent event) throws IOException {
        toDefault(event, "resetPass.fxml","Reset Password Page");
    }

    @FXML
    void toResetUserN(ActionEvent event) throws IOException {
        toDefault(event, "resetUserN.fxml","Reset Username Page");
    }

}
