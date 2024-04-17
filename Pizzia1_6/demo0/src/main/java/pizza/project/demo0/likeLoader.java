package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles operations related to loading, updating, and persisting likes or dislikes.
 */
public class likeLoader {
    //private static final String FILE_PATH = "likes.txt";  // File path for storing likes data.



    /**
     * Writes a list of likes to the file.
     * @param likeArray ArrayList of likes to be written to file.
     */
    public static void writeLikesToFile(ArrayList<likes> likeArray) {
        File file = new File("likes.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("The file was not found: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: likes.txt");
        }



        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            for (likes like : likeArray) {
                out.println(like.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Updates or adds a like based on existing records.
     * If the user has previously liked or disliked the same question, this method updates the record.
     * Otherwise, it adds a new record.
     * @param like The likes object to be processed.
     */
    public static void writeLikeToFile(likes like) {
        ArrayList<likes> allLikes = readLikesFromFile();
        boolean found = false;
        for (likes existingLike : allLikes) {
            if (existingLike.userID == like.userID && existingLike.questionID == like.questionID) {
                existingLike.isDislike = like.isDislike;
                found = true;
                break;
            }
        }
        if (!found) {
            allLikes.add(like);
        }
        writeLikesToFile(allLikes);
    }

    /**
     * Reads likes from a file and constructs an ArrayList of likes.
     * @return ArrayList of likes loaded from file.
     */
    public static ArrayList<likes> readLikesFromFile() {
        File file = new File("likes.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("The file was not found: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: likes.txt");
        }

        ArrayList<likes> likesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                likes like = parseLike(line);
                likesList.add(like);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        }
        return likesList;
    }

    /**
     * Parses a line of text into a likes object.
     * @param data The string to be parsed, expected format: "isDislike,isQuestion,userID,questionID".
     * @return The parsed likes object.
     */
    public static likes parseLike(String data) {
        String[] parts = data.split(",");
        boolean isDislike = Boolean.parseBoolean(parts[0]);
        boolean isQuestion = Boolean.parseBoolean(parts[1]);  // Consider removing or renaming if not used.
        int userID = Integer.parseInt(parts[2]);
        int questionID = Integer.parseInt(parts[3]);
        return new likes(isDislike, isQuestion, userID, questionID);
    }
}
