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
        primaryStage.getIcons().add(new Image("/pizzaIcon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {

        //loads the passwords into the system
        IDandPasswords idandPasswords = new IDandPasswords();

        //feed direct file path or it will make its own file
        String fileName = "database.txt";
        launch();
    }

}