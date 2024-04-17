package pizza.project.demo0;

import java.util.ArrayList;
import java.util.Random;

public class Question {
    private int posterUserId;
    private int questionId;
    private int likes;
    private String title;
    private String body;

    //private ArrayList<>

    private static ArrayList<Question> allQuestions = new ArrayList<>();

    Question(int posterId,int like, String title, String body){
        posterUserId = posterId;
        this.likes = calculateLikes();
        this.title = title;
        this.body = body;
        questionId = generateNewQuestionID();
    }

    Question(int posterId,int like, String title, String body, int questionId){
        posterUserId = posterId;
        this.likes = calculateLikes();
        this.title = title;
        this.body = body;
        this.questionId = questionId;
    }
    private int calculateLikes(){
        int rateing = 0;
        ArrayList<likes> allLikes = likeLoader.readLikesFromFile();
        for (likes x: allLikes) {
            if(x.isQuestion && x.getQuestionID() == questionId){
                if(x.isDislike){
                    rateing--;
                }
                else{
                    rateing++;
                }
            }
        }
        return rateing;
    }

    public int getPosterUserId() {
        return posterUserId;
    }

    public int getLikes() {
        return likes;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    int generateNewQuestionID(){
        ArrayList<User> loginInfo = IDandPasswords.getLoginInfo();
        int num;
        boolean bool = true;
        while(true){
            num = generateRandomNumber();
            for( Question x:allQuestions){
                if(x.getQuestionId() == num){
                    bool = false;
                }
            }
            if(bool){
                break;
            }
        }
        return num;
    }

    public static int generateRandomNumber() {
        // Create an instance of Random class
        Random random = new Random();
        // Generate a random integer between 1000000 (inclusive) and 9999999 (exclusive) and return it
        return random.nextInt(9000000) + 1000000;
    }
    public int getQuestionId(){
        return questionId;
    }

    public ArrayList<Response> getResponses(){
        ArrayList<Response> allResponses = ResponseLoader.readResponsesFromFile();
        ArrayList<Response> filteredResponses = new ArrayList<>();

        for(Response response: allResponses){
            if(response.getOPQuestionID() == questionId){
                filteredResponses.add(response);
            }
        }

        return filteredResponses;
    }
}