package pizza.project.demo0;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import static pizza.project.demo0.IDandPasswords.writeUsersFile;

public class resetUserNController extends Controller {

    private ArrayList<User> loginInfo;

    public resetUserNController() {
        // Initialize loginInfo with the HashMap from IDandPasswords class
        this.loginInfo = IDandPasswords.getLoginInfo();
    }

    @FXML
    private Text messageTxt;

    @FXML
    private TextField newUserOne;

    @FXML
    private TextField newUserTwo;

    @FXML
    void changeUsername(ActionEvent event) throws IOException {
        if (newUserOne.getText().equals(newUserTwo.getText())){
            System.out.println("working username reset...");
            for (User x:loginInfo) {
                if(x == getCurrentUser()){
                    x.setUsername(newUserOne.getText());
                    getCurrentUser().setUsername(newUserOne.getText());
                }
            }
            startCountdown(event);
            writeUsersFile("database.txt", loginInfo);
        } else {
            messageTxt.setText("Usernames Don't Match");
        }
    }

    private void startCountdown(ActionEvent event) {
        final int[] countdown = {5}; // Countdown from 5
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            if (countdown[0] > 1) {
                messageTxt.setText("Username Changed, sending you to login page in " + --countdown[0]);
                delay.playFromStart();
            } else {
                messageTxt.setText("And awayyyy we go!");
                try {
                    toLoginPage(event);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        delay.play();
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "Login Page");
    }

}
