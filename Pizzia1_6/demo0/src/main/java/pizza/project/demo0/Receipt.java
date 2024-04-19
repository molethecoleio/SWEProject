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
import javax.imageio.ImageIO;

import static com.sun.javafx.util.Utils.split;

public class Receipt {
    static ArrayList<String> ordered_pizzas = new ArrayList<>();
    static ArrayList<String> ordered_toppings = new ArrayList<>();

    /**
    * Retrieves pizza from temp.txt file and attributes it to an arraylist for easier access
    * @param orderNum variable used to sort which order gets chosen from the txt file
    */
    public static void retrievePizza(int orderNum) {

        try (FileReader fileReader = new FileReader("temp.txt")) {
            String line;
            int lineNumber = 1;
            while ((line = String.valueOf(fileReader.read())) != null) {

                if (lineNumber == orderNum) {
                    String[] elements = split(line, ",");
                    ordered_pizzas.addAll(Arrays.asList(elements));
                    break;
                }
                lineNumber++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves toppings from toppings.txt file and attributes it to an arraylist for easier access
     */
    public static void retrieveToppings(int orderNum) {

        try (FileReader fileReader = new FileReader("toppings.txt")) {
            String line;
            int lineNumber = 1;
            while ((line = String.valueOf(fileReader.read())) != null) {

                if (lineNumber == orderNum) {
                    String[] elements = split(line, ",");
                    ordered_toppings.addAll(Arrays.asList(elements));
                    break;
                }
                lineNumber++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void printReceipt(){

        /*
         * Iterates through the arraylist to display the description of each pizza
         * iterates twice to avoid printing pizzaID
         */
        int counter=1;

        for(int i =0; i<2; i++){

            if(counter==1)
            {
                System.out.println("Size: %s"+ ordered_pizzas.get(i));
                counter++;
            }

            else
            {
                System.out.println("Crust: %s"+ ordered_pizzas.get(i));
            }
        }

        /*
         * Iterates through the arraylist to display the toppings of each pizza
         * if(element == ordered_toppings[0]) statements avoids printing pizzaID
         */

        for(String element : ordered_toppings){
            if(element == ordered_toppings.get(0))
            {
                continue;
            }

            System.out.println("Topping: %s"+element);

        }

    }

    public static void printTotal(pizza Pizza, drink Drink)
    {
        double pizzaSub = Pizza.getPrice();
        double drinkSub = Drink.getPrice();
        double total = pizzaSub + drinkSub;
        System.out.print("TOTAL: $%.2f" + total);


    }

    public static void printScreen()
    {
        try {
            // Create an instance of Robot class
            Robot robot = new Robot();

            // Get the screen size
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Capture the screenshot
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);

            // Save the screenshot to a file
            File outputFile = new File("screenshot.png");
            ImageIO.write(screenCapture, "png", outputFile);

            System.out.println("Screenshot saved successfully!");
        } catch (AWTException | IOException ex) {
            System.err.println("Failed to capture screenshot: " + ex.getMessage());
        }
    }
    }







