package pizza.project.demo0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Set the stage in the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        loginController controller = loader.getController();
        controller.setStage(primaryStage);

        // Other initialization code
        primaryStage.setTitle("Pizza Project - Login Page");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("File:resources\\pizzaIcon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {

        //loads the passwords into the system
        IDandPasswords idandPasswords = new IDandPasswords();

        //feed direct file path or it will make its own file
        String fileName = "database.txt";

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(999,12,"yuh","body", 1));
        questions.add(new Question(11,13,"t2","testing testing 123",2));
        questions.add(new Question(22,80,"yum","hello world",11));
        questions.add(new Question(22,2,"forHonor","cats are cool"));

        //QuestionLoader.writeQuestionsToFile(questions);
        QuestionLoader.readQuestionsFromFile();

        ArrayList<Response> responses = new ArrayList<>();

        responses.add(new Response(999,12,"reply","this is a message from the goventment",1,999));
        responses.add(new Response(22,2,"re","sisopcbc are cool",2,999));
        responses.add(new Response(11,40,"7122348"," szcvsz cats",11,22));

        //ResponseLoader.writeResponseToFile(responses);
        ResponseLoader.readResponsesFromFile();

        ArrayList<Addresses> addresses = new ArrayList<>();

        addresses.add(new Addresses(22, 2, "114 heather glen blvd", "", "Kathleen", "GA", 31005, "478-217-6276"));
        addresses.add(new Addresses(22, 2, "256 bonares estate blvd", "420", "Warner Robins", "GA", 30067, "478-217-6276"));

        addressLoader.writeResponseToFile(addresses);
        addressLoader.readResponsesFromFile();
        launch();
    }

}