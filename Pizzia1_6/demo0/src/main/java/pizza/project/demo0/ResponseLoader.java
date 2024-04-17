package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;

public class ResponseLoader {
    private static final String FILE_PATH = "responses.txt";
    private static ArrayList<Response> allResponses = new ArrayList<>();

    public static void writeResponseToFile(ArrayList<Response> responses) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Response response : responses) {
                out.println(response.toString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Response> readResponsesFromFile() {
        allResponses.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int posterUserId = Integer.parseInt(parts[0]);
                    int likes = Integer.parseInt(parts[1]);
                    String title = parts[2].replace("，", ",");
                    String body = parts[3].replace("，", ",");
                    int questionId = Integer.parseInt(parts[4]);
                    int OPQID = Integer.parseInt(parts[5]);
                    int OPUserID = Integer.parseInt(parts[6]);
                    allResponses.add(new Response(posterUserId, likes, title, body, questionId, OPQID, OPUserID));
                    System.out.println("Question with ID" + questionId + " processed");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return allResponses;
    }
}
