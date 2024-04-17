package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.*;
/**
 * Controller for creating pizza orders.
 */
public class createOrderController extends Controller implements Initializable {

    @FXML
    private Label pizzaLabel;

    @FXML
    private Label specialPizzaLabel;

    @FXML
    private Label drinksLabel;


    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    /**
     * Handles action events triggered by UI to print details.
     * @param event The action event that triggered the method.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void createPizza(ActionEvent event) throws IOException {
        toDefault(event, "createPizza.fxml", "Pizza Builder Page");
    }
    /**
     * Initializes the controller class.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        pizzaLabel.setTextFill(getCurrentUser().getFontcolor());
        drinksLabel.setTextFill(getCurrentUser().getFontcolor());
        specialPizzaLabel.setTextFill(getCurrentUser().getFontcolor());
        //customPizzaLabel.setText("Custom Order \n$8.99");
        System.out.println("working");
        ArrayList<pizza> thePizza = pizzaLoader.readPizzaFromFile();
        if(!thePizza.isEmpty()) {
            fillListWithTextFile(thePizza);
        }
    }

    /**
     * Navigates back to the menu.
     * @param event The action event that triggered the method.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "menu");
    }

    /**
     * Fills the ListView with pizza names from a file.
     * @param customPizzas The list of pizzas to display in the ListView.
     */
    public void fillListWithTextFile(ArrayList<pizza> customPizzas){
        if(!listView.getItems().isEmpty()){
            listView.getItems().clear();
        }
        String[] pizzaTitles = new String[customPizzas.size()+2];
        int counter = 0;
        double totalPrice = 0.0f;
        for(pizza newPizza: customPizzas){
            totalPrice += newPizza.getPrice();
            pizzaTitles[counter++] = newPizza.getTitle();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        pizzaTitles[counter++] = "State Sales Tax(4%): " + df.format(totalPrice * 0.04);
        totalPrice += totalPrice * 0.04;
        pizzaTitles[counter] = "Total Price: " + df.format(totalPrice);
        listView.getItems().addAll(pizzaTitles);
    }
    /**
     * Wipes the temp file that all the data comes from.
     * @param event The action event that tri ggered the method.
     */
    @FXML
    void restartOrder(ActionEvent event) {
        pizzaLoader.wipeFile("temp.txt");
        pizzaLoader.wipeFile("toppings.txt");

        listView.getItems().clear();
    }

    @FXML
    void printOut(ActionEvent event) {
        System.out.println("hello :)");
    }
}
