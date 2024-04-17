package pizza.project.demo0;

public class likes {
    boolean isDislike, isQuestion;
    int userID,questionID;

    public likes(boolean isDislike, boolean isQuestion, int userID, int questionOrResponseID){
        this.isDislike = isDislike;
        this.isQuestion = isQuestion;
        this.userID = userID;
        questionID = questionOrResponseID;
    }

    public boolean isDislike() {
        return isDislike;
    }

    public void setDislike(boolean dislike) {
        isDislike = dislike;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }


    @Override
    public String toString() {
        return isDislike +
                "," + isQuestion +
                ","+ userID +
                "," + questionID;
    }
}
