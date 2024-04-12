package pizza.project.demo0;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private Color BGcolor;
    private Color fontcolor;

    private ArrayList<Cards> cards;

    User(String user, String pas, String email){
        username = user;
        password = pas;
        this.email = email;
        userID = generateNewUserID();
        BGcolor = Color.valueOf("White");
        fontcolor = Color.valueOf("Black");
        //cards = new ArrayList<>();
    }

    User(String user, String pas, String email, Color bg, Color font){
        username = user;
        password = pas;
        this.email = email;
        userID = generateNewUserID();
        BGcolor = bg;
        fontcolor = font;
        //cards = new ArrayList<>();
    }

    User(String user, String pas, String email, int userID, Color bg, Color font){
        username = user;
        password = pas;
        this.email = email;
        this.userID = userID;
        BGcolor = bg;
        fontcolor = font;
        //cards = new ArrayList<>();
    }

    int generateNewUserID(){
        ArrayList<User> loginInfo = IDandPasswords.getLoginInfo();
        int num;
        boolean bool = true;
        while(true){
            num = generateRandomNumber();
            for( User x:loginInfo){
                if(x.getUserID() == num){
                    bool = false;
                }

            }
            if(bool){
                break;
            }
        }
        return num;
    }

        public int getUserID() {
        return userID;
    }


    public boolean checkDups(int num){
        return false;
    }

    public static int generateRandomNumber() {
        // Create an instance of Random class
        Random random = new Random();
        // Generate a random integer between 1000000 (inclusive) and 9999999 (exclusive) and return it
        return random.nextInt(9000000) + 1000000;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setBGcolor(Color color){BGcolor = color;}

    public void setFontcolor(Color color){fontcolor = color;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    public Color getBGcolor() {
        return BGcolor;
    }

    public Color getFontcolor() {
        return fontcolor;
    }

    public static User getUserWithId(int id){
        for (User x: IDandPasswords.getLoginInfo()){
            if(x.getUserID() == id){
                return x;
            }
        }
        return null;
    }

    // Override toString() method
    @Override
    public String toString() {
        return String.format("User: %s, Password: %s, Email: %s, UserID: %s, BG Color: #%02X%02X%02X, Font Color: #%02X%02X%02X",
                username, password, email, userID,
                (int) (BGcolor.getRed() * 255), (int) (BGcolor.getGreen() * 255), (int) (BGcolor.getBlue() * 255),
                (int) (fontcolor.getRed() * 255), (int) (fontcolor.getGreen() * 255), (int) (fontcolor.getBlue() * 255));
    }

    public String toStringData() {
        return
                String.copyValueOf((username + "," + password + "," + email + "," + userID + "," + colorToHex(BGcolor) + "," + colorToHex(fontcolor)).toCharArray());
    }

    // Convert Color to Hexadecimal String
    public static String colorToHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    // Convert Hexadecimal String to Color
    public static Color hexToColor(String hex) {
        return Color.web(hex);
    }
}
