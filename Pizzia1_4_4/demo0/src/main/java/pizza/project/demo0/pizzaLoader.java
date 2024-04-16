package pizza.project.demo0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pizzaLoader {
    public static void writePizzaToFile(pizza myPizza) {
        try (FileWriter writer = new FileWriter("temp.txt")) {
            String toppings = String.join(", ", myPizza.toppings);
            writer.append(String.format("%s,%s,\"%s\",%.2f\n", myPizza.size, myPizza.crust, toppings, myPizza.price));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static pizza readPizzaFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String size = data[0].trim();
                String crust = data[1].trim();
                // Correctly parsing the toppings, assuming they are enclosed in quotes
                String toppingsLine = data[2].replaceAll("^\"|\"$", ""); // Remove surrounding quotes
                List<String> toppings = Arrays.asList(toppingsLine.split(",\\s*"));
                //double price = Double.parseDouble(data[3].trim());

                byte sizeByte = getSizeByteFromString(size);
                byte crustByte = getCrustByteFromString(crust);

                pizza myPizza = new pizza(sizeByte, crustByte, new ArrayList<>(toppings));
                System.out.println(myPizza);
                return myPizza;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte getSizeByteFromString(String size) {
        switch (size) {
            case "Small": return 0;
            case "Medium": return 1;
            case "Large": return 2;
            case "Extra Large": return 3;
            default: return 4; // Biggg Boi
        }
    }

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
