package pizza.project.demo0;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

import static pizza.project.demo0.Question.generateRandomNumber;

/**
 * Represents a user in the system with various personal details and user-specific settings.
 */
public class User {
    private int userID; // Unique identifier for the user.
    private String username; // Username of the user.
    private String password; // Password of the user.
    private String email; // Email address of the user.
    private Color BGcolor; // Background color for the user interface.
    private Color fontcolor; // Font color for the user interface.

    private ArrayList<Cards> cards; // Collection of cards associated with the user.

    /**
     * Constructs a user with specified username, password, and email.
     * Initializes user with default colors and a new user ID.
     *
     * @param user  the username
     * @param pas   the password
     * @param email the email address
     */
    User(String user, String pas, String email) {
        username = user;
        password = pas;
        this.email = email;
        userID = generateNewUserID();
        BGcolor = Color.valueOf("White");
        fontcolor = Color.valueOf("Black");
        // cards = new ArrayList<>();
    }

    /**
     * Constructs a user with specified username, password, email, and color settings.
     * Initializes user with a new user ID.
     *
     * @param user  the username
     * @param pas   the password
     * @param email the email address
     * @param bg    the background color
     * @param font  the font color
     */
    User(String user, String pas, String email, Color bg, Color font) {
        username = user;
        password = pas;
        this.email = email;
        userID = generateNewUserID();
        BGcolor = bg;
        fontcolor = font;
        // cards = new ArrayList<>();
    }

    /**
     * Constructs a user with specified username, password, email, user ID, and color settings.
     *
     * @param user   the username
     * @param pas    the password
     * @param email  the email address
     * @param userID the user ID
     * @param bg     the background color
     * @param font   the font color
     */
    User(String user, String pas, String email, int userID, Color bg, Color font) {
        username = user;
        password = pas;
        this.email = email;
        this.userID = userID;
        BGcolor = bg;
        fontcolor = font;
        // cards = new ArrayList<>();
    }

    /**
     * Constructs a user with specified username, password, and color settings.
     * Initializes user with a new user ID.
     *
     * @param user the username
     * @param pas  the password
     * @param bg   the background color
     * @param font the font color
     */
    User(String user, String pas, Color bg, Color font) {
        username = user;
        password = pas;
        email = ""; // Initialized email to an empty string.
        userID = generateNewUserID();
        BGcolor = bg;
        fontcolor = font;
        // cards = new ArrayList<>();
    }

    /**
     * Generates a new unique user ID.
     * Ensures that the ID does not conflict with existing user IDs.
     *
     * @return the newly generated user ID
     */
    int generateNewUserID() {
        ArrayList<User> loginInfo = IDandPasswords.getLoginInfo();
        int num;
        boolean bool = true;
        while (true) {
            num = generateRandomNumber();
            for (User x : loginInfo) {
                if (x.getUserID() == num) {
                    bool = false;
                }
            }
            if (bool) {
                break;
            }
        }
        return num;
    }

    /**
     * Retrieves a user by their user ID.
     * Searches through the login information to find a matching user ID.
     *
     * @param id the unique identifier for the user
     * @return the User object if found, null otherwise
     */
    public static User getUserWithId(int id) {
        for (User x : IDandPasswords.getLoginInfo()) {
            if (x.getUserID() == id) {
                return x;
            }
        }
        return null;
    }

    /**
     * Overrides the toString method to provide a string representation of the user.
     * Includes user details such as username, email, and ID formatted as a string.
     *
     * @return formatted string containing user information
     */
    @Override
    public String toString() {
        return String.format("User: %s, Password: %s, Email: %s, UserID: %s, BG Color: #%02X%02X%02X, Font Color: #%02X%02X%02X",
                username, password, email, userID,
                (int) (BGcolor.getRed() * 255), (int) (BGcolor.getGreen() * 255), (int) (BGcolor.getBlue() * 255),
                (int) (fontcolor.getRed() * 255), (int) (fontcolor.getGreen() * 255), (int) (fontcolor.getBlue() * 255));
    }

    /**
     * Provides a data string that represents the user, suitable for saving to a file.
     * Includes all user information, separated by commas and converted to a consistent format.
     *
     * @return a comma-separated string representing user data
     */
    public String toStringData() {
        return String.copyValueOf((username + "," + password + "," + email + "," + userID + "," + colorToHex(BGcolor) + "," + colorToHex(fontcolor)).toCharArray());
    }

    /**
     * Converts a Color object to a hexadecimal color string.
     *
     * @param color the Color object to convert
     * @return the hexadecimal color string
     */
    public static String colorToHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * Converts a hexadecimal color string to a Color object.
     *
     * @param hex the hexadecimal color string
     * @return the Color object
     */
    public static Color hexToColor(String hex) {
        return Color.web(hex);
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBGcolor(Color color) {
        BGcolor = color;
    }

    public void setFontcolor(Color color) {
        fontcolor = color;
    }

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
}