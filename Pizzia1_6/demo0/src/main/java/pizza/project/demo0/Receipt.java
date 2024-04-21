package pizza.project.demo0;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

import static com.sun.javafx.util.Utils.split;

public class Receipt {

    private int userID, receiptID;
    private ArrayList<pizza> ordered_pizzas = new ArrayList<>();
    private ArrayList<drink> ordered_drinks = new ArrayList<>();


    public Receipt(){
        userID = Controller.getCurrentCard().getOwnerUserId();
        //receiptID = generate
        ordered_pizzas = pizzaLoader.readPizzaFromFile();
        ordered_drinks = drink.readDrinkFromFile();
    }

    /**
     * Generates a unique ID for a new pizza.
     *
     * @param receipts Existing list of pizzas.
     * @return A unique pizza ID.
     */
    public static int generateNewPizzaID(List<Receipt> receipts) {
        Random random = new Random();
        int num;
        do {
            num = random.nextInt(9000000) + 1000000;
            final int finalNum = num;
            if (receipts.stream().noneMatch(p -> p.receiptID == finalNum)) {
                return finalNum;
            }
        } while (true);
    }


    public ArrayList<pizza> getOrdered_pizzas() {
        return ordered_pizzas;
    }

    public void setOrdered_pizzas(ArrayList<pizza> ordered_pizzas) {
        ordered_pizzas = ordered_pizzas;
    }

    public ArrayList<drink> getOrdered_drinks() {
        return ordered_drinks;
    }

    public void setOrdered_drinks(ArrayList<drink> ordered_drinks) {
        ordered_drinks = ordered_drinks;
    }
}







