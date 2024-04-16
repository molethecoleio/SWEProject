package pizza.project.demo0;

import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class pizza {
    String size, crust;
    ArrayList<String> toppings;
    double price;

    public pizza(byte size, byte crust, ArrayList<String> toppings){
        this.size = getSizeFromInt(size);
        this.crust = getCrustFromInt(crust);
        this.toppings = toppings;

        price = getPrice();
    }

    public String getSizeFromInt(byte x) {
        if(x == 0){
            return "Small";
        }
        else if( x == 1){
            return "Medium";
        }
        else if(x == 2){
            return "Large";
        }
        else if(x == 3){
            return "Extra Large";
        }
        return "Biggg Boi";
    }

    public String getCrustFromInt(byte x) {
        if(x == 0){
            return "Thin Crust";
        }
        else if( x == 1){
            return "Thick Crust";
        }
        else if(x == 2){
            return "Stuffed Crust";
        }
        else if(x == 3){
            return "Garlic Parmesan Crust";
        }


        return "Gluten Free Crust";
    }

    public double getPrice(){
        double price = 0.0;
        if(size.equals("Small")){
            price = 8.99;
            price += toppings.size();
        }
        else if(size.equals("Medium")){
            price = 10.99;
            price += toppings.size() * 1.25;
        }
        else if(size.equals("Large")){
            price = 13.99;
            price += toppings.size() * 1.50;
        }
        else if(size.equals("Extra Large")){
            price = 14.99;
            price += toppings.size() * 2;
        }
        else if(size.equals("Biggg Boi")){
            price = 21.99;
            price += toppings.size() * 3.99;
        }

        if(crust!="Thin Crust"){
            price+= 1;
        }
        if(crust=="Gluten Free Crust"){
            price += 2;
        }

        // Formatting the price to two decimal places
        return Double.parseDouble(String.format("%.2f", price));
    }

}
