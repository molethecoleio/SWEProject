package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static pizza.project.demo0.IDandPasswords.writeUsersFile;

public class acctSettingsController extends Controller implements Initializable {


    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ColorPicker fontColorPicker;

    @FXML
    private Text bgMessage;

    @FXML
    private Text emailMessage;

    @FXML
    private Text fontMessage;

    @FXML
    private Text passwordMessage;

    @FXML
    private Text usernameMessage;

    @FXML
    private TextField emailTxtFeild;

    @FXML
    private Text repasswordMessage;

    @FXML
    private Text topMessage;

    @FXML
    private TextField pwdTxtFeild;

    @FXML
    private TextField repwdTxtFeild;

    @FXML
    private TextField userNTxtFeild;

    private ArrayList<User> loginInfo;


    @FXML
    private ColorPicker BGColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        fontColorPicker.setValue(getCurrentUser().getFontcolor());
        BGColorPicker.setValue(getCurrentUser().getBGcolor());

        changeFonts();
    }

    public acctSettingsController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    public void changeFonts(){
        bgMessage.setFill(getCurrentUser().getFontcolor());
        emailMessage.setFill(getCurrentUser().getFontcolor());
        fontMessage.setFill(getCurrentUser().getFontcolor());
        passwordMessage.setFill(getCurrentUser().getFontcolor());
        usernameMessage.setFill(getCurrentUser().getFontcolor());
        topMessage.setFill(getCurrentUser().getFontcolor());
        repasswordMessage.setFill(getCurrentUser().getFontcolor());
    }


    @FXML
    void changeBGColor(ActionEvent event) {


        colorDisplayPane.setBackground(new Background(new BackgroundFill(BGColorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));

        for (User x:loginInfo) {
            if(x == getCurrentUser()){
                getCurrentUser().setBGcolor(BGColorPicker.getValue());
                x.setBGcolor(BGColorPicker.getValue());
            }
        }
        writeUsersFile("database.txt", loginInfo);
    }

    @FXML
    void changeFontColor(ActionEvent event) {



        for (User x:loginInfo) {
            if(x == getCurrentUser()){
                getCurrentUser().setFontcolor(fontColorPicker.getValue());
                changeFonts();
                x.setFontcolor(fontColorPicker.getValue());
            }
        }
        writeUsersFile("database.txt", loginInfo);
    }

    @FXML
    void changeEmail(ActionEvent event) {
        boolean bool = true;
        for (User user : loginInfo) {
            if (user.getEmail().equals(emailTxtFeild.getText())){
                bool = false;
                topMessage.setText("Email Already In Use");
                topMessage.setFill(Paint.valueOf("red"));
            }
        }
        if(emailTxtFeild.getText().equals("")){
            bool = false;
            topMessage.setText("Email cant be blank");
            topMessage.setFill(Paint.valueOf("red"));

        }
        if(bool){
            System.out.println("working Email Change...");
            for (User x:loginInfo) {
                if(x == getCurrentUser()){
                    x.setEmail(emailTxtFeild.getText());
                    getCurrentUser().setEmail(emailTxtFeild.getText());
                    topMessage.setText("Email Changed");
                }
            }
            writeUsersFile("database.txt", loginInfo);
        }
        emailTxtFeild.setText("");
    }

    @FXML
    void changePwd(ActionEvent event) {
        if (pwdTxtFeild.getText().equals(repwdTxtFeild.getText())) {
            if(!pwdTxtFeild.getText().isEmpty()) {
                for (User user : loginInfo) {
                    if (user == getCurrentUser()) {
                        user.setPassword(pwdTxtFeild.getText());
                        getCurrentUser().setPassword(pwdTxtFeild.getText());
                        topMessage.setFill(getCurrentUser().getFontcolor());
                        topMessage.setText("Password Changed");
                        pwdTxtFeild.setText("");
                        repwdTxtFeild.setText("");
                    }
                }
                writeUsersFile("database.txt", loginInfo);
            }else{
                topMessage.setText("Password Cannot Be Blank");
                topMessage.setFill(Paint.valueOf("red"));
            }
        } else {
            topMessage.setText("Passwords do not match");
            topMessage.setFill(Paint.valueOf("red"));
        }
    }

    @FXML
    void changeUserN(ActionEvent event) {
        if(getCurrentUser().getUsername().equals(userNTxtFeild.getText())){
            topMessage.setText("This is already your current username");
            topMessage.setFill(Paint.valueOf("red"));
        }
        else{

            System.out.println("changing username for user" + getCurrentUser().getUsername() + " ...");
            for (User user : loginInfo) {
                if (user == getCurrentUser()) {
                    if (userNTxtFeild.getText().equals("")) {
                        topMessage.setText("Username Cannot Be Blank");
                        topMessage.setFill(Paint.valueOf("#FF0000"));
                    } else {
                        user.setUsername(userNTxtFeild.getText());
                        getCurrentUser().setUsername(userNTxtFeild.getText());
                        System.out.println("username changed to " + getCurrentUser().getUsername() + " working database commit... ");
                        topMessage.setText("Changed Username");
                        userNTxtFeild.setText("");
                    }
                }
            }
            writeUsersFile("database.txt", loginInfo);
        }

    }

    @FXML
    void toMenuPage(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
