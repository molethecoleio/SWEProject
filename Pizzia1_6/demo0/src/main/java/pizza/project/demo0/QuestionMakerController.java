package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionMakerController extends Controller implements Initializable {

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private Label noteLabel;

    @FXML
    private TextArea questionBobyTxt;

    @FXML
    private TextField questionTitleTxt;

    @FXML
    private Label welcomeUser;

    /**
     * Initializes the controller class, sets up the user interface and event listeners.
     * @param url The location used to resolve relative paths for the root object, or null if unknown.
     * @param resourceBundle The resources used to localize the root object, or null.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        welcomeUser.setTextFill(getCurrentUser().getFontcolor());
        noteLabel.setTextFill(getCurrentUser().getFontcolor());
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        toDefault(event, "FAQs.fxml", "FAQs");
    }

    @FXML
    void saveQuestion(ActionEvent event) throws IOException {
        if(questionTitleTxt.getText().equals("") || questionTitleTxt.getText() == null){
            welcomeUser.setText("Must Enter A Question Title");
        }
        else if(questionBobyTxt.getText().equals("") || questionBobyTxt.getText() == null){
            welcomeUser.setText("Must Enter A Question Body");
        }
        else{
            Question x = new Question(getCurrentUser().getUserID(), 0, questionTitleTxt.getText(), questionBobyTxt.getText());
            ArrayList<Question> questions = QuestionLoader.readQuestionsFromFile();
            questions.add(x);
            QuestionLoader.writeQuestionsToFile(questions);
            cancel(event);
        }
    }
}
