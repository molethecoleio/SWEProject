package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.*;

import static pizza.project.demo0.drink.readDrinkFromFile;
import static pizza.project.demo0.drink.wipeDrinksFile;

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
    private Label wholePriceLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;
    @FXML
    private Button coffeeSelection;
    @FXML
    private Button halfAndHalfSelection;
    @FXML
    private Button lemonadeSelection;
    @FXML
    private Button sodaSelection;
    @FXML
    private Button sweetTeaSelection;
    @FXML
    private Button unsweetTeaSelection;
    @FXML
    private Button waterSelection;

    @FXML
    private RadioButton smallRadioDrinks;
    @FXML
    private RadioButton mediumRadioDrinks;
    @FXML
    private RadioButton largeRadioDrinks;
    @FXML
    private RadioButton exLRadioDrinks;

    @FXML
    private RadioButton smallPizzaRadio;
    @FXML
    private RadioButton mediumPizzaRadio;

    @FXML
    private RadioButton LargeRadio;

    @FXML
    private RadioButton exLPizzaRadio;

    @FXML private Line line0;

    @FXML private Line line1;

    @FXML private Line line2;

    @FXML private Line line3;

    @FXML private Line line4;

    @FXML private Line line5;

    @FXML private Line line6;

    @FXML private Line line7;

    @FXML private Line line8;

    int drinkSize = 0;
    int pizzaSize = 0;


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
        totalLabel.setTextFill(getCurrentUser().getFontcolor());
        wholePriceLabel.setTextFill(getCurrentUser().getFontcolor());

        smallRadioDrinks.setTextFill(getCurrentUser().getFontcolor());
        mediumRadioDrinks.setTextFill(getCurrentUser().getFontcolor());
        largeRadioDrinks.setTextFill(getCurrentUser().getFontcolor());
        exLRadioDrinks.setTextFill(getCurrentUser().getFontcolor());

        smallPizzaRadio.setTextFill(getCurrentUser().getFontcolor());
        mediumPizzaRadio.setTextFill(getCurrentUser().getFontcolor());
        LargeRadio.setTextFill(getCurrentUser().getFontcolor());
        exLPizzaRadio.setTextFill(getCurrentUser().getFontcolor());

        line0.setStroke(getCurrentUser().getFontcolor());
        line1.setStroke(getCurrentUser().getFontcolor());
        line2.setStroke(getCurrentUser().getFontcolor());
        line3.setStroke(getCurrentUser().getFontcolor());
        line4.setStroke(getCurrentUser().getFontcolor());
        line5.setStroke(getCurrentUser().getFontcolor());
        line6.setStroke(getCurrentUser().getFontcolor());
        line7.setStroke(getCurrentUser().getFontcolor());
        line8.setStroke(getCurrentUser().getFontcolor());

        ArrayList<pizza> thePizza = pizzaLoader.readPizzaFromFile();
        ArrayList<drink> theDrink = readDrinkFromFile();
        if(!thePizza.isEmpty()) {
            fillListWithTextFile(thePizza, theDrink);
        }
    }

    @FXML
    void toPaymentProcessor(ActionEvent event) throws IOException {
        toDefault(event, "PaymentProcessor.fxml", "YUH");
    }


    @FXML
    void drinkSizeChange(MouseEvent event) {
        if(smallRadioDrinks.isSelected()){
            drinkSize = 0;
        }
        if(mediumRadioDrinks.isSelected()){
            drinkSize = 1;
        }
        if(largeRadioDrinks.isSelected()){
            drinkSize = 2;
        }
        if(exLRadioDrinks.isSelected()){
            drinkSize = 3;
        }

    }


    @FXML
    void changePizzaSize(MouseEvent event) {
        if(smallPizzaRadio.isSelected()){
            pizzaSize = 0;
        }
        if(mediumPizzaRadio.isSelected()){
            pizzaSize = 1;
        }
        if(LargeRadio.isSelected()){
            drinkSize = 2;
        }
        if(exLPizzaRadio.isSelected()){
            pizzaSize = 3;
        }
        System.out.println("testint234");
    }

    @FXML
    void changePizzaSizeOnAction(ActionEvent event) {
        if(smallPizzaRadio.isSelected()){
            pizzaSize = 0;
        }
        if(mediumPizzaRadio.isSelected()){
            pizzaSize = 1;
        }
        if(LargeRadio.isSelected()){
            drinkSize = 2;
        }
        if(exLPizzaRadio.isSelected()){
            pizzaSize = 3;
        }
        System.out.println("yuhh");
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
    /*public void fillListWithTextFile(ArrayList<pizza> customPizzas){
        //LOAD FILE AND CHECK
        boolean drinkFlag = false;
        ArrayList<drink> drinks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("temp_drinks.txt"))) {
            String line = reader.readLine();
            if (!line.isEmpty() || line == null)
            {
                drinkFlag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!listView.getItems().isEmpty() && !drinkFlag){
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
        updateWholePrice(totalPrice);
    }*/

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
        updateWholePrice(totalPrice);
    }

/*
    public void fillListWithDrinkFile(ArrayList<drink> customDrinks){
        if(!listView.getItems().isEmpty()){
            listView.getItems().clear();
        }
        String[] drinkTitles = new String[customDrinks.size()+2];
        int counter = 0;
        double totalPrice = 0.0f;
        for(drink newDrink: customDrinks){
            totalPrice += newDrink.getPrice();
            drinkTitles[counter++] = drink.getTitle(newDrink);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        drinkTitles[counter++] = "State Sales Tax(4%): " + df.format(totalPrice * 0.04);
        totalPrice += totalPrice * 0.04;
        drinkTitles[counter] = "Total Price: " + df.format(totalPrice);
        listView.getItems().addAll(drinkTitles);
        updateWholePrice(totalPrice);
    }

 */

    void updateWholePrice(double price)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        wholePriceLabel.setText("$" + String.valueOf(df.format(price)));
    }

    /**
     * Wipes the temp file that all the data comes from.
     * @param event The action event that tri ggered the method.
     */
    @FXML
    void restartOrder(ActionEvent event) {
        pizzaLoader.wipeFile("temp.txt");
        pizzaLoader.wipeFile("toppings.txt");
        wipeDrinksFile("temp_drinks.txt");

        listView.getItems().clear();
        wholePriceLabel.setText("$0.00");
    }

    @FXML
    void printOut(ActionEvent event) {
        System.out.println("hello :)");
    }

    @FXML
    void addMeatLoversPizza(ActionEvent event)
    {
        ArrayList<String> toppingsMeatLovers = new ArrayList<String>();
        toppingsMeatLovers.add("Pepperoni");
        toppingsMeatLovers.add("Ham");
        toppingsMeatLovers.add("Bacon");

        //listView.getItems().add("Meat Lovers Pizza");
        System.out.println(pizzaSize);
        pizza currentPizza = pizza.createNewPizza((byte)pizzaSize, (byte)3, toppingsMeatLovers, pizzaLoader.readPizzaFromFile());
        pizzaLoader.writePizzaToFile(currentPizza);

        ArrayList<pizza> thePizza = pizzaLoader.readPizzaFromFile();
        ArrayList<drink> theDrink = readDrinkFromFile();
        if(!thePizza.isEmpty()) {
            fillListWithTextFile(thePizza, theDrink);
        }
    }

    @FXML
    void addSupremePizza(ActionEvent event)
    {
        ArrayList<String> toppingsMeatLovers = new ArrayList<String>();
        toppingsMeatLovers.add("Pepperoni");
        toppingsMeatLovers.add("Ham");
        toppingsMeatLovers.add("Bacon");

        //listView.getItems().add("Meat Lovers Pizza");
        pizza currentPizza = pizza.createNewPizza((byte)pizzaSize, (byte)3, toppingsMeatLovers, pizzaLoader.readPizzaFromFile());
        pizzaLoader.writePizzaToFile(currentPizza);

        ArrayList<pizza> thePizza = pizzaLoader.readPizzaFromFile();
        ArrayList<drink> theDrink = readDrinkFromFile();
        if(!thePizza.isEmpty()) {
            fillListWithTextFile(thePizza, theDrink);
        }
    }

    @FXML
    void addWater(ActionEvent event)
    {
        drink currentDrink = new drink(drinkSize,"Water");
        drink.writeDrinkToFile(currentDrink);
        ArrayList<drink> theDrink = readDrinkFromFile();
        ArrayList<pizza> allPizzas = pizzaLoader.readPizzaFromFile();
        if(!theDrink.isEmpty()) {
            fillListWithTextFile(allPizzas, theDrink);
        }
    }

}
