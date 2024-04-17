package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles the operations related to storing and retrieving pizza
 * objects from files. It provides functionality to write pizza details and toppings
 * into separate files and to read them back into pizza objects.
 */
public class pizzaLoader {

    /**
     * Writes the details of a given pizza to a file. This includes the pizza's size, crust, and ID.
     * Toppings are written to a separate file.
     *
     * @param myPizza The pizza object to write to file.
     */
    public static void writePizzaToFile(pizza myPizza) {
        try (FileWriter writer = new FileWriter("temp.txt", true)) {
            writer.append(String.format("%s,%s,%s\n", myPizza.getSize(), myPizza.getCrust(), myPizza.getPizzaId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("toppings.txt", true)) {
            if (myPizza.getToppings().isEmpty()) {
                writer.append(String.format("%d,\n", myPizza.getPizzaId())); // Ensure even pizzas with no toppings are correctly formatted
            } else {
                writer.append(String.format("%d,%s\n", myPizza.getPizzaId(), String.join(",", myPizza.getToppings())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the stored pizza objects from the file 'temp.txt' and constructs
     * pizza objects from the data.
     *
     * @return An ArrayList containing the reconstructed pizza objects.
     */
    public static ArrayList<pizza> readPizzaFromFile() {
        ArrayList<pizza> pizzas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int pizzaId = Integer.parseInt(data[2].trim());
                ArrayList<String> toppings = readToppings(pizzaId);
                byte sizeByte = getSizeByteFromString(data[0].trim());
                byte crustByte = getCrustByteFromString(data[1].trim());
                pizzas.add(new pizza(sizeByte, crustByte, toppings, pizzaId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzas;
    }

    /**
     * Reads the toppings associated with a specific pizza ID from 'toppings.txt'.
     *
     * @param pizzaId The ID of the pizza for which toppings need to be read.
     * @return An ArrayList of strings representing the toppings of the pizza.
     * @throws IOException If an I/O error occurs during file reading.
     */
    private static ArrayList<String> readToppings(int pizzaId) throws IOException {
        ArrayList<String> toppings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("toppings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) == pizzaId) {
                    if (data.length > 1 && !data[1].trim().isEmpty()) {
                        toppings.addAll(Arrays.asList(data).subList(1, data.length));
                    }
                    break;
                }
            }
        }
        return toppings;
    }

    /**
     * Clears the content of a specified file by overwriting it.
     *
     * @param filePath The path of the file to be wiped.
     */
    public static void wipeFile(String filePath) {
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
    private static byte getSizeByteFromString(String size) {
        switch (size) {
            case "Small": return 0;
            case "Medium": return 1;
            case "Large": return 2;
            case "Extra Large": return 3;
            default: return 4; // Biggg Boi
        }
    }

    /**
     * Converts a string representation of a pizza crust type to its corresponding byte code.
     *
     * @param crust The string representation of the crust.
     * @return The byte code for the given crust type.
     */
    private static byte getCrustByteFromString(String crust) {
        switch (crust) {
            case "Thin Crust": return 0;
            case "Thick Crust": return 1;
            case "Stuffed Crust": return 2;
            case "Garlic Parmesan Crust": return 3;
            default: return 4; // Gluten Free Crust
        }
    }
}
