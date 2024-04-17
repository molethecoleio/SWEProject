package pizza.project.demo0;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addressLoader {
    private static final Logger LOGGER = Logger.getLogger(addressLoader.class.getName());
    private static final String FILE_PATH = "addresses.txt";
    private static List<Addresses> allAddresses = new ArrayList<>();

    public static void writeResponseToFile(List<Addresses> allAddresses) {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(FILE_PATH)))) {
            for (Addresses address : allAddresses) {
                out.println(address.toString());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file", e);
        }
    }

    public static ArrayList<Addresses> readResponsesFromFile() {
        ArrayList<Addresses> allAddresses = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Addresses address = parseAddress(line);
                    if (address != null) {
                        allAddresses.add(address);
                    }
                } catch (RuntimeException e) {
                    LOGGER.log(Level.WARNING, "Skipping invalid address record", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading from file", e);
        }
        return allAddresses;
    }

    private static Addresses parseAddress(String line) {
        String[] parts = line.split(",");
        if (parts.length == 8) {
            try {
                int ownerID = Integer.parseInt(parts[0]);
                int addressType = Integer.parseInt(parts[1]);
                String streetAddress = parts[2].replace("，", ",");
                String apartment = parts[3].replace("，", ",");
                String city = parts[4].replace("，", ",");
                String state = parts[5].replace("，", ",");
                int zip = Integer.parseInt(parts[6]);
                String phone = parts[7].replace("，", ",");
                return new Addresses(ownerID, addressType, streetAddress, apartment, city, state, zip, phone);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "Error parsing address components", e);
                return null;
            }
        }
        return null;
    }
}
