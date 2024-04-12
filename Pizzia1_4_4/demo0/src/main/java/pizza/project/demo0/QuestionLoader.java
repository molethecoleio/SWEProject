package pizza.project.demo0;

import java.io.*;
import java.util.ArrayList;

public class QuestionLoader {
    private static final String FILE_PATH = "questions.txt";
    private static ArrayList<Question> allQuestions = new ArrayList<>();

    public static void writeQuestionsToFile(ArrayList<Question> questions) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Question question : questions) {
                out.println(question.getQuestionId() + "," + question.getPosterUserId() + "," + question.getLikes() + "," + question.getTitle().replace(",", "，") + "," + question.getBody().replace(",", "，"));
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Question> readQuestionsFromFile() {
        allQuestions.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int questionId = Integer.parseInt(parts[0]);
                    int posterUserId = Integer.parseInt(parts[1]);
                    int likes = Integer.parseInt(parts[2]);
                    String title = parts[3].replace("，", ",");
                    String body = parts[4].replace("，", ",");
                    allQuestions.add(new Question(posterUserId, likes, title, body, questionId));
                    System.out.println("Question with ID" + questionId + " processed");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return allQuestions;
    }
}
