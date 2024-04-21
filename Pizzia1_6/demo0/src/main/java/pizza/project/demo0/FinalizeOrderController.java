package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static pizza.project.demo0.drink.readDrinkFromFile;

public class FinalizeOrderController extends Controller implements Initializable {

    @FXML
    private Label addressLabel;

    @FXML
    private Label cardLabel;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label orderLabel;

    @FXML
    private TextField signature;


    @FXML
    private Label updateWholePrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<pizza> thePizza = pizzaLoader.readPizzaFromFile();
        ArrayList<drink> theDrink = readDrinkFromFile();
        if(!thePizza.isEmpty()) {
            fillListWithTextFile(thePizza, theDrink);
        }

        addressLabel.setText("Address: \n" + getCurrentAddress().getTitle());
        cardLabel.setText("Card: \n" + getCurrentCard().getTitle());
        nameLabel.setText("Card Holders Name: \n" + getCurrentCard().getName());
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        toDefault(event, "PaymentProcessor.fxml", "Payment");
    }

    @FXML
    void finalOrder(ActionEvent event) throws IOException {
        if(signature.getText().equals("")){
            orderLabel.setText("Must Sign");
        }
        else{
            toDefault(event,"Receipt.fxml", "Receipt");
        }
    }

    public void fillListWithTextFile(ArrayList<pizza> customPizzas, ArrayList<drink> customDrinks){
        if(!listView.getItems().isEmpty()){
            listView.getItems().clear();
        }

        int totalItems = customPizzas.size() + customDrinks.size() + 2;
        String[] itemTitles = new String[totalItems];
        int counter = 0;
        double totalPrice = 0.0f;

        for(pizza newPizza: customPizzas){
            totalPrice += newPizza.getPrice();
            itemTitles[counter++] = newPizza.getTitle();
        }

        for(drink newDrink: customDrinks){
            totalPrice += newDrink.getPrice();
            itemTitles[counter++] = drink.getTitle(newDrink);
            System.out.println(drink.getTitle(newDrink));
        }

        DecimalFormat df = new DecimalFormat("#.##");
        itemTitles[counter++] = "State Sales Tax(4%): " + df.format(totalPrice * 0.04);
        totalPrice += totalPrice * 0.04;
        itemTitles[counter] = "Total Price: " + df.format(totalPrice);

        listView.getItems().addAll(itemTitles);
        updateWholePrice.setText("Total Cost: $" + df.format(totalPrice));
    }


}
