package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

public class pizzaBuilderController extends Controller implements Initializable {

    @FXML
    private RadioButton crustGarlic;

    @FXML
    private Label crustLabel;

    @FXML
    private RadioButton crustNoGluten;

    @FXML
    private RadioButton crustStuffed;

    @FXML
    private RadioButton crustThick;

    @FXML
    private RadioButton crustThin;

    @FXML
    private Label priceLabel;

    @FXML
    private RadioButton sizeBigBoi;

    @FXML
    private RadioButton sizeExLarge;

    @FXML
    private Label sizeLabel;

    @FXML
    private RadioButton sizeLarge;

    @FXML
    private RadioButton sizeMedium;

    @FXML
    private RadioButton sizeSmall;

    @FXML
    private Label taxLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private RadioButton topingAnchovies;

    @FXML
    private RadioButton topingBacon;

    @FXML
    private RadioButton toppingArtichokes;

    @FXML
    private RadioButton toppingBananaPeppers;

    @FXML
    private RadioButton toppingBeef;

    @FXML
    private RadioButton toppingBlackOlives;

    @FXML
    private RadioButton toppingBroccoli;

    @FXML
    private RadioButton toppingChedderCheese;

    @FXML
    private RadioButton toppingCheese;

    @FXML
    private RadioButton toppingChicken;

    @FXML
    private RadioButton toppingEggplant;

    @FXML
    private RadioButton toppingFedaCheese;

    @FXML
    private RadioButton toppingFreshSpinach;

    @FXML
    private RadioButton toppingGarlic;

    @FXML
    private RadioButton toppingGreenOlives;

    @FXML
    private RadioButton toppingGreenPeppers;

    @FXML
    private RadioButton toppingHam;

    @FXML
    private RadioButton toppingJalapeno;

    @FXML
    private RadioButton toppingMeatballs;

    @FXML
    private RadioButton toppingMushroom;

    @FXML
    private RadioButton toppingPepperoni;

    @FXML
    private RadioButton toppingPineapples;

    @FXML
    private RadioButton toppingRedOnions;

    @FXML
    private RadioButton toppingSausage;

    @FXML
    private RadioButton toppingTomatoes;

    @FXML
    private Label topppingsLabel;

    private ArrayList<RadioButton> toppings = new ArrayList<>();

    private String[] toppingTitles;

    private pizza currentPizza;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Adding the default topping
        toppings.add(toppingCheese);

        // Manually adding each topping radio button to the toppings list
        toppings.add(toppingArtichokes);
        toppings.add(toppingBananaPeppers);
        toppings.add(toppingBeef);
        toppings.add(toppingBlackOlives);
        toppings.add(toppingBroccoli);
        toppings.add(toppingChedderCheese);
        toppings.add(toppingChicken);
        toppings.add(toppingEggplant);
        toppings.add(toppingFedaCheese);
        toppings.add(toppingFreshSpinach);
        toppings.add(toppingGarlic);
        toppings.add(toppingGreenOlives);
        toppings.add(toppingGreenPeppers);
        toppings.add(toppingHam);
        toppings.add(toppingJalapeno);
        toppings.add(toppingMeatballs);
        toppings.add(toppingMushroom);
        toppings.add(toppingPepperoni);
        toppings.add(toppingPineapples);
        toppings.add(toppingRedOnions);
        toppings.add(toppingSausage);
        toppings.add(toppingTomatoes);
        toppings.add(topingAnchovies);
        toppings.add(topingBacon);
        toppingTitles = new String[toppings.size()];
        //yayyyy we did it
        titleLabel.setTextFill(getCurrentUser().getFontcolor());
        sizeLabel.setTextFill(getCurrentUser().getFontcolor());
        crustLabel.setTextFill(getCurrentUser().getFontcolor());
        topppingsLabel.setTextFill(getCurrentUser().getFontcolor());
        taxLabel.setTextFill(getCurrentUser().getFontcolor());
        priceLabel.setTextFill(getCurrentUser().getFontcolor());

        sizeSmall.setTextFill(getCurrentUser().getFontcolor());
        sizeMedium.setTextFill(getCurrentUser().getFontcolor());
        sizeLarge.setTextFill(getCurrentUser().getFontcolor());
        sizeExLarge.setTextFill(getCurrentUser().getFontcolor());
        sizeBigBoi.setTextFill(getCurrentUser().getFontcolor());

        crustThin.setTextFill(getCurrentUser().getFontcolor());
        crustThick.setTextFill(getCurrentUser().getFontcolor());
        crustGarlic.setTextFill(getCurrentUser().getFontcolor());
        crustStuffed.setTextFill(getCurrentUser().getFontcolor());
        crustNoGluten.setTextFill(getCurrentUser().getFontcolor());


        byte yummy = 0;
        for(RadioButton x: toppings){
            toppingTitles[yummy++] = x.getText();
            x.setTextFill(getCurrentUser().getFontcolor());
            x.setText(x.getText() + " + $0.99");
        }

        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    @FXML
    void cancelPizza(ActionEvent event) throws IOException {
        toDefault(event, "createOrder.fxml", "Order Builder");
    }

    @FXML
    void savePizza(ActionEvent event) throws IOException {
        pizzaLoader.writePizzaToFile(currentPizza);
        toDefault(event, "createOrder.fxml", "Order Builder");
    }


    @FXML
    byte sizeChange(MouseEvent event) {
        byte x1 = 0;
        String price = "";
        if(sizeSmall.isSelected()){
            price = " + $0.99";
        } else if(sizeMedium.isSelected()){
            price = " + $1.25";
            x1 = 1;
        } else if (sizeLarge.isSelected()) {
            price = " + $1.50";
            x1 = 2;
        }else if (sizeExLarge.isSelected()) {
            price = " + $2.00";
            x1 = 3;
        }else if (sizeBigBoi.isSelected()) {
            price = " + $3.99";
            x1 = 4;

        }
        byte yummy = 0;
        for(RadioButton x : toppings){
            x.setText(toppingTitles[yummy++] + price);
        }
        calcPrice(event);
        return x1;
    }

    @FXML
    double calcPrice(MouseEvent event) {
        buildPizza(event);
        double price = currentPizza.price;
        //System.out.println("Price = " + price);
        String priceTxt = "Pizza Price: " + price;
        priceLabel.setText(priceTxt);
        return price;
    }

    void buildPizza(MouseEvent event){
        byte size = 0;
        if(sizeMedium.isSelected()){
            size = 1;
        } else if (sizeLarge.isSelected()) {
            size = 2;
        }else if (sizeExLarge.isSelected()) {
            size = 3;
        }else if (sizeBigBoi.isSelected()) {
            size = 4;
        }

        byte crust = 0;
        if(crustThick.isSelected()){
            crust = 1;
        } else if(crustStuffed.isSelected()){
            crust = 2;
        }else if(crustGarlic.isSelected()){
            crust = 3;
        }else if(crustNoGluten.isSelected()){
            crust = 4;
        }

        ArrayList<String> stringToppings = new ArrayList<>();
        for(RadioButton radio : toppings){
            if(radio.isSelected()){
                stringToppings.add(radio.getText().substring(0,radio.getText().indexOf("+")-1));
            }
        }
        currentPizza = new pizza(size, crust, stringToppings);
    }
}
