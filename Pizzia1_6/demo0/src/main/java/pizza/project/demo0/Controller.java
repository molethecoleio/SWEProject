package pizza.project.demo0;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Base controller class providing navigation functionalities and state management
 * across various parts of the application.
 */
public class Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static User currentUser = null;
    private static Addresses currentAddress = null;
    private static Question currentQuestion = null;

<<<<<<< Updated upstream
=======
    private static Cards currentCard = null;

    public static boolean isPaymentProcessing = false;

    public static boolean isEditing = false;

    public static String pizzaContext = "custom";

>>>>>>> Stashed changes
    /**
     * Navigates to a specified view.
     * @param event The event that triggered the navigation.
     * @param fileName The FXML file name of the target view.
     * @param title The title to set for the new stage.
     * @throws IOException If loading the FXML resource fails.
     */
    @FXML
    public void toDefault(ActionEvent event, String fileName, String title) throws IOException {
        navigate(event, fileName, title, null);
    }

    /**
     * Navigates to a specified view and updates the current user.
     * @param event The event that triggered the navigation.
     * @param fileName The FXML file name of the target view.
     * @param title The title to set for the new stage.
     * @param user The user to set as the current user.
     * @throws IOException If loading the FXML resource fails.
     */
    @FXML
    public void toDefault(ActionEvent event, String fileName, String title, User user) throws IOException {
        setCurrentUser(user);
        navigate(event, fileName, title, user);
    }

    /**
     * Handles the actual navigation logic, factoring out common code from the overloaded toDefault methods.
     * @param event The event that triggered the navigation.
     * @param fileName The FXML file name of the target view.
     * @param title The title to set for the new stage.
     * @param user The user to update as current, if not null.
     * @throws IOException If loading the FXML resource fails.
     */
    private void navigate(ActionEvent event, String fileName, String title, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Project - " + title);
        stage.show();
    }

    // Getters and setters for current user, address, and question
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public Addresses getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Addresses address) {
        currentAddress = address;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question question) {
        currentQuestion = question;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
