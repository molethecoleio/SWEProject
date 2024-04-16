package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class createOrderController extends Controller implements Initializable {

    @FXML
    private Label customPizzaLabel;

    @FXML
    void printOut(ActionEvent event) throws IOException {
        toDefault(event, "createPizza.fxml", "Pizza Builder Page");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customPizzaLabel.setText("Custom Order \n$8.99");
        System.out.println("wprking");
        //System.out.println(pizzaLoader.readPizzaFromFile().toString());
    }

}
