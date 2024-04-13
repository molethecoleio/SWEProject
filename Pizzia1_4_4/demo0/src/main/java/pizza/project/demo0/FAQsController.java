package pizza.project.demo0;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FAQsController extends Controller implements Initializable{

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea questionContent;

    @FXML
    private Label questionTitle;

    @FXML
    private Text userOrPasNotFound;

    @FXML
    private Label welcomeUser;

    @FXML
    private Button viewResponsesButton;

    @FXML
    private RadioButton dislikeRadioButton;

    @FXML
    private RadioButton likeRadioButton;

    ArrayList<Question> questions = QuestionLoader.readQuestionsFromFile();

    ArrayList<Response> responses = ResponseLoader.readResponsesFromFile();

    String currentQuestionTitle = null;
    Question currentQuestion = null;
    Response currentResponse = null;

    Boolean showingQuestions = true;
    String[] questionTitles = new String[questions.size()];
    ArrayList<String> responseCollector;
    String[] filteredResponseTitles;
    int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));

        questionTitle.setTextFill(getCurrentUser().getFontcolor());
        welcomeUser.setTextFill(getCurrentUser().getFontcolor());

        likeRadioButton.setTextFill(getCurrentUser().getFontcolor());
        dislikeRadioButton.setTextFill(getCurrentUser().getFontcolor());

        for(int x = 0; x < questions.size(); x++){
            questionTitles[x] = questions.get(x).getTitle() + "\nby:" + User.getUserWithId(questions.get(x).getPosterUserId()).getUsername() + "\t\t " + questions.get(x).getLikes() + " Likes";
        }
        listView.getItems().addAll(questionTitles);
        //responseListView.getItems().addAll(rTitles);
        questionContent.setText("Select A Question");
        questionTitle.setText("Select A Question");
        //welcomeUser.setText("Select A Question");
        String[] responseTitles;
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                String temp = listView.getSelectionModel().getSelectedItem();
                for (Question x : questions) {
                    if (temp.contains((Integer.toString(x.getLikes()))) && temp.contains(x.getTitle())) {
                        if (temp.contains(User.getUserWithId(x.getPosterUserId()).getUsername())) {
                            currentQuestion = x;
                        }
                    }
                }
                if (currentQuestion != null) { // Check if currentQuestion is not null
                    questionTitle.setText(currentQuestion.getTitle());
                    questionContent.setText(currentQuestion.getBody());
                    viewResponsesButton.setDisable(false);
                    likeRadioButton.setDisable(false);
                    dislikeRadioButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "login Page");
    }

    @FXML
    void toMenu(ActionEvent event)  throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

    @FXML
    void viewResponses(ActionEvent event) {
        System.out.println("eloos yay it wokrs ");
    }

}



/*
package pizza.project.demo0;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FAQsController extends Controller implements Initializable{


    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> responseListView;

    @FXML
    private TextArea questionContent;

    @FXML
    private Text questionTitle;

    @FXML
    private Text userOrPasNotFound;

    @FXML
    private Text welcomeUser;

    ArrayList<Question> questions = QuestionLoader.readQuestionsFromFile();

    ArrayList<Response> responses = ResponseLoader.readResponsesFromFile();

    String currentQuestionTitle = null;
    Question currentQuestion = null;
    Response currentResponse = null;

    Boolean showingQuestions = true;
    String[] questionTitles = new String[questions.size()];
    ArrayList<String> responseCollector;
    String[] filteredResponseTitles;
    int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int x = 0; x < questions.size(); x++){
            questionTitles[x] = questions.get(x).getTitle() + "\t\t\tby:" + User.getUserWithId(questions.get(x).getPosterUserId()).getUsername() + "\t\t " + questions.get(x).getLikes() + " Likes";
        }
        listView.getItems().addAll(questionTitles);
        //responseListView.getItems().addAll(rTitles);
        questionContent.setText("Select A Question");
        String[] responseTitles;
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                String temp = listView.getSelectionModel().getSelectedItem();
                for (Question x : questions) {
                    if (temp.contains((Integer.toString(x.getLikes()))) && temp.contains(x.getTitle())) {
                        if (temp.contains(User.getUserWithId(x.getPosterUserId()).getUsername())) {
                            currentQuestion = x;
                        }
                    }
                }
                if(currentQuestion != null) { // Check if currentQuestion is not null
                    questionTitle.setText(currentQuestion.getTitle());
                    questionContent.setText(currentQuestion.getBody());
                    listView.setDisable(true);
                    for(int y = 0; y < responses.size(); y++){
                        if(responses.get(y).getOPQuestionID() == currentQuestion.getQuestionId()){
                            responseCollector.add(responses.get(y).getTitle());
                        }
                    }
                    filteredResponseTitles = null;
                    filteredResponseTitles = new String[responseCollector.size()];
                    for(int y = 0; y < responses.size(); y++){
                        if(responses.get(y).getOPQuestionID() == currentQuestion.getQuestionId()){
                            filteredResponseTitles[y] = responses.get(y).getTitle();
                            responseCollector.add(responses.get(y).getTitle());
                        }
                    }
                    responseListView.setDisable(false);
                    responseListView.getItems().clear();
                    responseListView.getItems().addAll(filteredResponseTitles);
                }
            }
        });
        responseListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String temp = responseListView.getSelectionModel().getSelectedItem();
                for (Response x : responses) {
                    if (temp.contains((Integer.toString(x.getLikes()))) && temp.contains(x.getTitle())) {
                        if (temp.contains(User.getUserWithId(x.getPosterUserId()).getUsername())) {
                            currentResponse = x;
                        }
                    }
                }

                if(currentQuestion != null && currentResponse != null) { // Check if currentQuestion is not null
                    questionTitle.setText(currentResponse.getTitle());
                    questionContent.setText(currentResponse.getBody());
                    listView.setDisable(true);
                }
            }
        });
    }





    @FXML
    void backToQuestions(ActionEvent event) {
        responseListView.setDisable(true);
        listView.setDisable(false);
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "login Page");
    }

    @FXML
    void toMenu(ActionEvent event)  throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

}

 */
