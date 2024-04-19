package pizza.project.demo0;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a pizza with customizable options like size, crust, and toppings.
 */
public class pizza {
    private String size, crust;
    private int pizzaId;
    private ArrayList<String> toppings;
    private double price;

    /**
     * Constructs a new Pizza object with specified attributes.
     *
     * @param size The size code of the pizza.
     * @param crust The crust code of the pizza.
     * @param toppings A list of toppings.
     * @param pizzaId Unique identifier for the pizza.
     */
    public pizza(byte size, byte crust, ArrayList<String> toppings, int pizzaId){
        this.size = getSizeFromByte(size);
        this.crust = getCrustFromByte(crust);
        this.toppings = new ArrayList<>(toppings);
        this.pizzaId = pizzaId;
        this.price = calculatePrice();
    }

    /**
     * Factory method to create a new Pizza instance.
     *
     * @param size Size code of the pizza.
     * @param crust Crust code of the pizza.
     * @param toppings List of toppings for the pizza.
     * @param allPizzas Existing list of pizzas to ensure unique ID.
     * @return A new Pizza object.
     */
    public static pizza createNewPizza(byte size, byte crust, ArrayList<String> toppings, List<pizza> allPizzas) {
        int newPizzaID = generateNewPizzaID(allPizzas);
        return new pizza(size, crust, toppings, newPizzaID);
    }

    /**
     * Generates a unique ID for a new pizza.
     *
     * @param allPizzas Existing list of pizzas.
     * @return A unique pizza ID.
     */
    public static int generateNewPizzaID(List<pizza> allPizzas) {
        Random random = new Random();
        int num;
        do {
            num = random.nextInt(9000000) + 1000000;
            final int finalNum = num;
            if (allPizzas.stream().noneMatch(p -> p.pizzaId == finalNum)) {
                return finalNum;
            }
        } while (true);
    }

    /**
     * Converts a size code to its corresponding string value.
     *
     * @param x The byte code for size.
     * @return The string representation of the size.
     */
    private String getSizeFromByte(byte x) {
        return switch (x) {
            case 0 -> "Small";
            case 1 -> "Medium";
            case 2 -> "Large";
            case 3 -> "Extra Large";
            default -> "Biggg Boi";
        };
    }

    /**
     * Converts a crust code to its corresponding string value.
     *
     * @param x The byte code for crust.
     * @return The string representation of the crust.
     */
    private String getCrustFromByte(byte x) {
        return switch (x) {
            case 0 -> "Thin Crust";
            case 1 -> "Thick Crust";
            case 2 -> "Stuffed Crust";
            case 3 -> "Garlic Parmesan Crust";
            default -> "Gluten Free Crust";
        };
    }

    /**
     * Calculates the price of the pizza based on its size, crust, and toppings.
     *
     * @return The price of the pizza.
     */
    private double calculatePrice() {
        double basePrice = switch (size) {
            case "Small" -> 8.99;
            case "Medium" -> 10.99;
            case "Large" -> 13.99;
            case "Extra Large" -> 14.99;
            case "Biggg Boi" -> 21.99;
            default -> 0.0;
        };
        basePrice += toppings.size() * switch (size) {
            case "Medium" -> 1.25;
            case "Large" -> 1.50;
            case "Extra Large" -> 2.00;
            case "Biggg Boi" -> 3.99;
            default -> 1.00;
        };

        if (!"Thin Crust".equals(crust)) {
            basePrice += 1.0;
        }
        if ("Gluten Free Crust".equals(crust)) {
            basePrice += 2.0;
        }
        return Double.parseDouble(String.format("%.2f", basePrice));
    }

    /**
     * Generates a descriptive title for the pizza.
     *
     * @return A formatted string with the pizza's size, crust, toppings, and price.
     */
    public String getTitle(){
        DecimalFormat df = new DecimalFormat("#.##");
        if(getToppings().isEmpty()){
            return size + ", " + crust + "; No Toppings\nPrice: "+ price;
        }
        else{
            String returner = size + ", " + crust + "; with ";
            for(String top: toppings){
                returner += top + ", ";
            }
            return returner.substring(0, returner.lastIndexOf(",")) + " toppings\nPrice: " + df.format(price);
        }
    }

    //getters and setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
