package pizza.project.demo0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class drink {

    String size, selection;
    int drinkId;
    double price;
    public drink(int size, String selection)
    {
        this.size = getSizeFromByte(size);
        this.selection = selection;
        this.drinkId = generateNewDrinkID();
        this.price = getPrice();
        System.out.println(size);
    }

    public drink(int size, String selection, int drinkId){
        this.size = getSizeFromByte(size);
        this.selection = selection;
        this.drinkId = drinkId;
        this.price = getPrice();
        price = getPrice();
    }

    public static String getSizeFromByte(int x) {
        return switch (x) {
            case 0 -> "Small";
            case 1 -> "Medium";
            case 2 -> "Large";
            case 3 -> "Extra Large";
            default -> "Small*"; // Biggg Boi
        };
    }

    public void setSelection(String newSelection)
    {
        this.selection = newSelection;
    }

    public static void writeDrinkToFile(drink myDrink) {
        try (FileWriter writer = new FileWriter("temp_drinks.txt", true)) {
            writer.append(String.format("%s,%s,%s\n", getSizeByteFromString(myDrink.size), myDrink.selection, myDrink.drinkId));
            //writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<drink> readDrinkFromFile() {
        ArrayList<drink> drinkz = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new
                FileReader("temp_drinks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int size = Integer.parseInt(data[0].trim());
                String selection = data[1].trim();
                int iD = Integer.parseInt(data[2].trim());
                drinkz.add(new drink(size,selection,iD));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return drinkz;
    }

    public static void wipeDrinksFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a string representation of a pizza size to its corresponding byte code.
     *
     * @param size The string representation of the size.
     * @return The byte code for the given pizza size.
     */
    static byte getSizeByteFromString(String size) {
        return switch (size) {
            case "Small" -> 0;
            case "Medium" -> 1;
            case "Large" -> 2;
            case "Extra Large" -> 3;
            default -> 4; // Biggg Boi
        };
    }

    int generateNewDrinkID(){
        ArrayList<drink> allDrink = readDrinkFromFile();
        int num;
        boolean bool = true;
        while(true){
            num = generateRandomNumber();
            for(drink x: allDrink){
                if(x.drinkId == num){
                    bool = false;
                }
            }
            if(bool){
                break;
            }
        }
        return num;
    }

    public static int generateRandomNumber() {
        // Create an instance of Random class
        Random random = new Random();
        // Generate a random integer between 1000000 (inclusive) and 9999999 (exclusive) and return it
        return random.nextInt(9000000) + 1000000;
    }

    public double getPrice(){
        double price = 0.0;
        if(size.equals("Small")){
            price = 1.99;
        }
        else if(size.equals("Medium")){
            price = 2.99;
        }
        else if(size.equals("Large")){
            price = 3.49;
        }
        else if(size.equals("Extra Large")){
            price = 3.79;
        }
        // Formatting the price to two decimal places
        return Double.parseDouble(String.format("%.2f", price));
    }
    public static String getTitle(drink myDrink){
        DecimalFormat df = new DecimalFormat("#.##");
        String returner = myDrink.size + ", " + myDrink.selection + "; \n Price:" + df.format(myDrink.price);
        return returner;
    }
}