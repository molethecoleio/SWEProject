package pizza.project.demo0;

public class Response extends Question{
    private int OPQuestionID;
    private int OPUserID;

    Response(int posterId, int likes, String title, String body, int questionId, int OPQuestionID, int OPUserID){
        super(posterId,likes,title,body,questionId);
        this.OPQuestionID = OPQuestionID;
        this.OPUserID = OPUserID;
    }

    Response(int posterId, int likes, String title, String body, int OPQuestionID, int OPUserID){
        super(posterId,likes,title,body);
        this.OPQuestionID = OPQuestionID;
        this.OPUserID = OPUserID;
    }

    public int getOPQuestionID() {
        return OPQuestionID;
    }

    public int getOPUserID(){
        return getPosterUserId();
    }

    @Override
    public String toString(){
        return getPosterUserId() + "," +
                getLikes() + "," +
                getTitle() + "," +
                getBody() + "," +
                getQuestionId() + "," +
                OPQuestionID + "," +
                OPUserID;
    }
}
