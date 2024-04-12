package pizza.project.demo0;

//importables, code by ya main main cole
import javafx.scene.paint.Color;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * This class manages user IDs and passwords, providing encryption and decryption functionality.
 */
public class IDandPasswords {
    /*
    NOTE: the IV is randomly generated, no two same text encrypted will have the same encryption
    In Base64 encoding, every 3 bytes of input data are converted into 4 characters of output data.
    If the input data length is not divisible evenly by 3, padding is added at the end to make the length a multiple of 3.
    Specifically, one or two "=" characters are added at the end to indicate how many padding bytes were added.
     */
    //Encryption constants
    private static final String SECRET_KEY = "F9A3F6C8B1E4A7D0F3C6A9D2B5E8C1A4"; // 256-bit key
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH = 16; // 128 bits for AES

    //hashmap<Username, Password>; Hashmaps are similar to arraylists they just store pairs of data at each index
    //static HashMap<String, String> logininfo = new HashMap<String,String>();
    static ArrayList<User> users = new ArrayList<>();
    //Constructor
    IDandPasswords(){}

    /**
     * Retrieves the login information.
     *
     * @return the login information hashmap
     */
    protected static ArrayList<User> getLoginInfo(){
        System.out.println("getting users");
        /*
        for(User x: users){
            System.out.println(x);
        }
         */
        if(users.size()==0){
            System.out.println("array empty");
        }
        return users;
    }

    public static void writeUserToFile(String fileName, User x){
        //IDandPasswords.writeToFile(fileName, "Cole,Hello,molethecole.io@gmail.com,999,0,0,0,FF,FF,FF"); // this will be username and password
        writeToFile(fileName, x.toStringData());
    }

    /**
     * Writes encrypted content to a specified file.
     *
     * @param fileName the name of the file to write to
     * @param content  the content to encrypt and write to the file
     */
    public static void writeToFile(String fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            // Encrypt the content
            //String encryptedContent = encrypt(content);
            String encryptedContent = content;

            // Write the encrypted content to the file on a new line
            printWriter.println(encryptedContent);

            //print letting admin know when a new user has been added
            System.out.println("Content has been encrypted and written to the file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUsersFile(String fileName, ArrayList<User> users) {
        clearFile();
        for(User x: users){
            writeToFile(fileName, x.toStringData());
        }
        System.out.println("Changes Committed To Database");
    }

    public static void clearFile(){
        try {
            FileWriter fileWriter = new FileWriter("database.txt");
            fileWriter.close(); // Closes the file without writing, clearing its contents
            System.out.println("File cleared successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads encrypted content from a specified file, decrypts it, and populates a hashmap with the decrypted content.
     *
     * @param fileName the name of the file to read from
     *
     */
    /*
    public static void readFromFile(String fileName) {
        try (BufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Decrypt the content after reading line by line
                String decryptedContent = decrypt(line);
                //put decrypted content into the has map, username first password second
                String[] parts = decryptedContent.split(",");

                // Check if parts array has exactly 10 elements
                if (parts.length == 10) {
                    String username = parts[0];
                    String password = parts[1];
                    String email = parts[2];
                    int userID = Integer.parseInt(parts[3]);
                    //The 16 at the end is for the radix; to convert HEX 16 base into INT 10 base is required
                    int fontRed   = Integer.parseInt(parts[4], 16);
                    int fontGreen = Integer.parseInt(parts[5], 16);
                    int fontBlue  = Integer.parseInt(parts[6], 16);
                    Color fontColor = Color.rgb(fontRed, fontGreen, fontBlue);

                    int BGRed   = Integer.parseInt(parts[7], 16);
                    int BGGreen = Integer.parseInt(parts[8], 16);
                    int BGBlue  = Integer.parseInt(parts[9], 16);
                    Color BGColor = Color.rgb(BGRed, BGGreen, BGBlue);

                    // Proceed with using username, password, and email

                    User x = new User(username, password, email, fontColor, BGColor);
                    System.out.println(x);
                    System.out.println(x.toStringData());
                    users.add(x);
                } else {
                    // Handle the error, for example, by logging or throwing a more informative exception
                    System.err.println("Invalid input format. Expected format: Username,Password,Email");
                }
                //String substringed = decryptedContent.substring(decryptedContent.indexOf(",") + 1, decryptedContent.length());
                //logininfo.put(decryptedContent.substring(0,decryptedContent.indexOf(",")) , substringed);

                //System.out.println("User Added: userN:" + username + "  Pass: " + password + "email: " + email);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    /**
     * Reads users from the file, expecting a specific format for each line.
     * The expected format is: username,password,email,userID,BGRed,BGGreen,BGBlue,FontRed,FontGreen,FontBlue
     * where color components are floating-point numbers.
     *
     * @param fileName The name of the file to read from.
     */
    public static ArrayList<User> readFromFile(String fileName) {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //String decryptedContent = decrypt(line);
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Adjusted for username, password, email, userID, and two color values
                    String username = parts[0];
                    String password = parts[1];
                    String email = parts[2];
                    int userID = Integer.parseInt(parts[3]); // Assuming userID is an integer
                    Color BGColor = Color.web(parts[4]); // Hexadecimal to Color for background
                    Color fontColor = Color.web(parts[5]); // Hexadecimal to Color for font

                    // Assuming an updated constructor or method to set colors directly
                    User newUser = new User(username, password, email, userID, BGColor, fontColor);

                    users.add(newUser);
                    System.out.println(newUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer from file: " + e.getMessage());
        }
        System.out.println(users.size() + " users added");
        return users;
    }


    /**
     * encrypts a given string using AES encryption with a pre-defined 256bit secret key.
     *
     * @param strToEncrypt the string to decrypt
     * @return the decrypted string
     */
    public static String encrypt(String strToEncrypt) {
        try {
            // Generate a random IV
            byte[] ivBytes = generateIV();

            // Create the key and IV objects
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            // Initialize the cipher in encryption mode with the key and IV
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            // Encrypt the plaintext
            byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());

            // Concatenate IV and ciphertext and return as base64 encoded string
            byte[] ivAndCipherText = concatenateIVAndCipherText(ivBytes, encryptedBytes);
            return Base64.getEncoder().encodeToString(ivAndCipherText);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Decrypts a given string using AES encryption with a pre-defined secret key.
     *
     * @param strToDecrypt the string to decrypt
     * @return the decrypted string
     */
    public static String decrypt(String strToDecrypt) {
        try {
            // Decode the base64 encoded string
            byte[] ivAndCipherText = Base64.getDecoder().decode(strToDecrypt);

            // Extract IV and ciphertext
            byte[] ivBytes = extractIV(ivAndCipherText);
            byte[] cipherText = extractCipherText(ivAndCipherText);

            // Create the secret key object from the pre-defined secret key
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            // Initialize the cipher in decryption mode with the key and IV
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

            // Decrypt the ciphertext
            byte[] decryptedBytes = cipher.doFinal(cipherText);
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Generates a random initialization vector (IV) for AES encryption.
     *
     * @return a byte array containing the randomly generated IV
     */
    private static byte[] generateIV() {
        //Initialize a SecureRandom object to generate random bytes
        SecureRandom secureRandom = new SecureRandom();
        //Create a byte array to hold the generated IV
        byte[] iv = new byte[IV_LENGTH];
        //Generate random bytes and store them in the IV array
        secureRandom.nextBytes(iv);
        //Return the generated IV
        return iv;
    }

    /**
     * Concatenates the initialization vector (IV) and ciphertext into a single byte array.
     *
     * @param iv         the initialization vector
     * @param cipherText the ciphertext
     * @return a byte array containing the concatenated IV and ciphertext
     */
    private static byte[] concatenateIVAndCipherText(byte[] iv, byte[] cipherText) {
        byte[] ivAndCipherText = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, ivAndCipherText, 0, iv.length);
        System.arraycopy(cipherText, 0, ivAndCipherText, iv.length, cipherText.length);
        return ivAndCipherText;
    }

    /**
     * Extracts the initialization vector (IV) from the combined IV and ciphertext byte array.
     *
     * @param ivAndCipherText the combined IV and ciphertext
     * @return the extracted IV
     */
    private static byte[] extractIV(byte[] ivAndCipherText) {
        byte[] iv = new byte[IV_LENGTH];
        System.arraycopy(ivAndCipherText, 0, iv, 0, IV_LENGTH);
        return iv;
    }

    /**
     * Extracts the ciphertext from the combined IV and ciphertext byte array.
     *
     * @param ivAndCipherText the combined IV and ciphertext
     * @return the extracted ciphertext
     */
    private static byte[] extractCipherText(byte[] ivAndCipherText) {
        byte[] cipherText = new byte[ivAndCipherText.length - IV_LENGTH];
        System.arraycopy(ivAndCipherText, IV_LENGTH, cipherText, 0, cipherText.length);
        return cipherText;
    }
}
