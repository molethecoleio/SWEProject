package pizza.project.demo0;

public class likeList {
    private int userID;
    private boolean isQuestion;
    private int questionOrResponseID;
    private boolean like;

    public likeList(int userID, boolean isQuestion, int questionOrResponseID, boolean like) {
        this.userID = userID;
        this.isQuestion = isQuestion;
        this.questionOrResponseID = questionOrResponseID;
        this.like = like;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    public int getQuestionOrResponseID() {
        return questionOrResponseID;
    }

    public void setQuestionOrResponseID(int questionOrResponseID) {
        this.questionOrResponseID = questionOrResponseID;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
