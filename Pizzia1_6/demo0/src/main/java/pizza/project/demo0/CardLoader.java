package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardLoader {
    static String filename = "Cards.txt";

    public CardLoader(String filename) {
        this.filename = filename;
    }

    // Reads cards from the file and creates Cards objects
    public static List<Cards> readCards() {
        List<Cards> cards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int userID = Integer.parseInt(parts[0]);
                    String cardNumbers = parts[1];
                    int expMonth = Integer.parseInt(parts[2]);
                    int expYear = Integer.parseInt(parts[3]);
                    int CVV = Integer.parseInt(parts[4]);
                    String name = parts[5];
                    String cardProvider = parts[6];
                    cards.add(new Cards(userID, cardNumbers, expMonth, expYear, CVV, name, cardProvider));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return cards;
    }

    // Writes Cards objects to the file
    public void writeCards(List<Cards> cards) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Cards card : cards) {
                writer.write(card.getOwnerUserId() + "," + card.getCardNumbers() + "," + card.getExpMonth() + "," + card.getExpYear() + "," + card.getCVV() + "," + card.getName() + "," + card.getCardProvider());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Writes Cards objects to the file
    public void writeCard(Cards card1) {
        List<Cards> cards = readCards();
        cards.add(card1);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Cards card : cards) {
                writer.write(card.getOwnerUserId() + "," + card.getCardNumbers() + "," + card.getExpMonth() + "," + card.getExpYear() + "," + card.getCVV() + "," + card.getName() + "," + card.getCardProvider());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        System.out.println('r');
    }

    // Wipes the file and writes a new list of Cards objects
    public void replaceCards(List<Cards> newCards) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (Cards card : newCards) {
                writer.write(card.getOwnerUserId() + "," + card.getCardNumbers() + "," + card.getExpMonth() + "," + card.getExpYear() + "," + card.getCVV() + "," + card.getName()+ "," + card.getCardProvider());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
