package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;

public class addressLoader {
    private static final String FILE_PATH = "addresses.txt";
    private static ArrayList<Addresses> allAddresses = new ArrayList<>();

    public static void writeResponseToFile(ArrayList<Addresses> allAddresses) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Addresses address : allAddresses) {
                out.println(address.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Addresses> readResponsesFromFile() {
        allAddresses.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    int ownerID = Integer.parseInt(parts[0]);
                    int isBillingAddress = Integer.parseInt(parts[1]);
                    String streetAddress = parts[2].replace("，", ",");
                    String apartment = parts[3].replace("，", ",");
                    String city = parts[4].replace("，", ",");
                    String state = parts[5].replace("，", ",");
                    int zip = Integer.parseInt(parts[6]);
                    String phone = parts[7].replace("，", ",");
                    allAddresses.add(new Addresses(ownerID, isBillingAddress, streetAddress, apartment, city, state, zip, phone));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return allAddresses;
    }
}
