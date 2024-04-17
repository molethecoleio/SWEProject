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
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the FAQs page, managing interactions with questions and likes.
 */
public class FAQsController extends Controller implements Initializable {

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

    @FXML
    private TextArea responseContent;

    @FXML
    private Label responseTilte;

    Question currentQuestion;
    private ArrayList<Question> questions = QuestionLoader.readQuestionsFromFile();
    private String[] questionTitles = new String[questions.size()];

    /**
     * Initializes the controller class, sets up the user interface and event listeners.
     * @param url The location used to resolve relative paths for the root object, or null if unknown.
     * @param resourceBundle The resources used to localize the root object, or null.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupUI();
        updateLikeCounts();
        populateQuestionsListView();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String temp = listView.getSelectionModel().getSelectedItem();
                for (Question x : questions) {
                    if (temp != null && temp.contains((Integer.toString(x.getLikes()))) && temp.contains(x.getTitle())) {
                        if (temp.contains(User.getUserWithId(x.getPosterUserId()).getUsername())) {
                            currentQuestion = x;
                            setCurrentQuestion(x);
                            updateQuestionDetails();
                            ArrayList<Response> responses = currentQuestion.getResponses();
                            responseContent.clear();

                            if(responses.isEmpty()){
                                responseContent.setText("No one has responded to this question yet. You could be the one that changes that!");
                            }
                            else{
                                for (Response xy: responses) {
                                    responseContent.setText( responseContent.getText() + xy.getTitle() + ",");
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Configures initial UI properties based on user settings.
     */
    private void setupUI() {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        responseTilte.setTextFill(getCurrentUser().getFontcolor());
        questionTitle.setTextFill(getCurrentUser().getFontcolor());
        welcomeUser.setTextFill(getCurrentUser().getFontcolor());
        likeRadioButton.setTextFill(getCurrentUser().getFontcolor());
        dislikeRadioButton.setTextFill(getCurrentUser().getFontcolor());
    }

    /**
     * Populates the list view with questions and their associated likes.
     */
    private void populateQuestionsListView() {
        ArrayList<likes> allLikes = likeLoader.readLikesFromFile();
        listView.getItems().clear();
        sortByLikes();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String title = question.getTitle() + "\nby:" + User.getUserWithId(question.getPosterUserId()).getUsername() + "\t\t " + question.getLikes() + " Likes";

            // Check if the current user liked this specific question
            for(likes x : allLikes) {
                if (x.userID == getCurrentUser().getUserID() && x.getQuestionID() == question.getQuestionId()) {
                    if(x.isDislike){
                        title = title + " -1 Likes(Including you)";
                    }
                    else{
                        title = title + " +1 Likes(Including you)";
                    }
                    break; // No need to continue the loop once we found the like/dislike for this question
                }
            }

            questionTitles[i] = title;
            listView.getItems().add(title);
        }
        questionContent.setText("Select A Question");
        questionTitle.setText("Select A Question");
    }


    /**
     * Updates the question details area with the selected question's data.
     */
    private void updateQuestionDetails() {
        likeRadioButton.setSelected(false);
        dislikeRadioButton.setSelected(false);
        questionTitle.setText(currentQuestion.getTitle());
        questionContent.setText(currentQuestion.getBody());
        viewResponsesButton.setDisable(false);
        likeRadioButton.setDisable(false);
        dislikeRadioButton.setDisable(false);

        ArrayList<likes> allLikes = likeLoader.readLikesFromFile();

        for(likes x: allLikes){
            if(x.userID == getCurrentUser().getUserID() && x.getQuestionID() == currentQuestion.getQuestionId()){
                if(x.isDislike){
                    dislikeRadioButton.setSelected(true);
                }
                else{
                    likeRadioButton.setSelected(true);
                }
            }
        }
    }

    @FXML
    void toLoginPage(ActionEvent event) throws IOException {
        toDefault(event, "login.fxml", "login Page");
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

    @FXML
    void viewResponses(ActionEvent event) {
        System.out.println("Responses viewed");
    }

    @FXML
    void userReviewed(MouseEvent event) {
        boolean dislike = dislikeRadioButton.isSelected();
        likes review = new likes(dislike, true, getCurrentUser().getUserID(), currentQuestion.getQuestionId());
        likeLoader.writeLikeToFile(review);
        updateLikeCounts();
        refreshQuestionsListView(); // Refresh ListView after updating like
    }

    /**
     * Refreshes the ListView after like or dislike actions to show updated likes counts.
     */
    private void refreshQuestionsListView() {
        populateQuestionsListView();
    }


    /**
     * Updates the like counts for each question based on the likes file.
     */
    private void updateLikeCounts() {
        ArrayList<likes> allLikes = likeLoader.readLikesFromFile();
        // Reset like counts
        for (Question q : questions) {
            q.setLikes(0);
        }
        // Count likes
        for (likes like : allLikes) {
            for (Question q : questions) {
                if (q.getQuestionId() == like.getQuestionID()) {
                    if(like.isDislike){
                        q.setLikes(q.getLikes() - 1);
                    }
                    else{
                        q.setLikes(q.getLikes() + 1);
                    }
                }
            }
        }
    }


    /**
     * Sorts the ArrayList of questions by their likes using the Quick Sort algorithm.
     * @param questions The ArrayList of questions to be sorted.
     * @param low The starting index of the ArrayList.
     * @param high The ending index of the ArrayList.
     */
    private void quickSort(ArrayList<Question> questions, int low, int high) {
        if (low < high) {
            int pi = partition(questions, low, high);
            quickSort(questions, low, pi - 1);
            quickSort(questions, pi + 1, high);
        }
    }

    /**
     * Sorts the ArrayList of questions by their likes using the Quick Sort algorithm.
     * @param responses The ArrayList of questions to be sorted.
     * @param low The starting index of the ArrayList.
     * @param high The ending index of the ArrayList.
     */
    private void quickSort1(ArrayList<Response> responses, int low, int high) {
        if (low < high) {
            int pi = partition1(responses, low, high);
            quickSort1(responses, low, pi - 1);
            quickSort1(responses, pi + 1, high);
        }
    }

    /**
     * Partitions the ArrayList of questions for Quick Sort.
     * @param questions The ArrayList of questions to be partitioned.
     * @param low The starting index of the partition.
     * @param high The ending index of the partition.
     * @return The partitioning index.
     */
    private int partition(ArrayList<Question> questions, int low, int high) {
        int pivot = questions.get(high).getLikes();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (questions.get(j).getLikes() > pivot) {
                i++;
                // Swap questions[i] and questions[j]
                Question temp = questions.get(i);
                questions.set(i, questions.get(j));
                questions.set(j, temp);
            }
        }
        // Swap questions[i+1] and questions[high] (or pivot)
        Question temp = questions.get(i + 1);
        questions.set(i + 1, questions.get(high));
        questions.set(high, temp);
        return i + 1;
    }

    /**
     * Partitions the ArrayList of questions for Quick Sort.
     * @param responses The ArrayList of questions to be partitioned.
     * @param low The starting index of the partition.
     * @param high The ending index of the partition.
     * @return The partitioning index.
     */
    private int partition1(ArrayList<Response> responses, int low, int high) {
        int pivot = questions.get(high).getLikes();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (questions.get(j).getLikes() > pivot) {
                i++;
                // Swap questions[i] and questions[j]
                Question temp = questions.get(i);
                questions.set(i, questions.get(j));
                questions.set(j, temp);
            }
        }
        // Swap questions[i+1] and questions[high] (or pivot)
        Question temp = questions.get(i + 1);
        questions.set(i + 1, questions.get(high));
        questions.set(high, temp);
        return i + 1;
    }

    /**
     * Sorts the questions ArrayList by likes using Quick Sort.
     */
    private void sortByLikes() {
        quickSort(questions, 0, questions.size() - 1);
    }

}