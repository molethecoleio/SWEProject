package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class editCardController extends Controller implements Initializable {

    @FXML
    private ToggleGroup addressType;

    @FXML
    private RadioButton americanRadio;

    @FXML
    private TextField cardNumTxtField;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private TextField cvvTxtField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton discoverRadio;

    @FXML
    private Text labelName;

    @FXML
    private Text labelCVV;

    @FXML
    private Text labelCard;

    @FXML
    private Text labelDate;

    @FXML
    private RadioButton masterRadio;

    @FXML
    private TextField nameTxtField;

    @FXML
    private Label topMessage;

    @FXML
    private RadioButton visaRadio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Color color = getCurrentUser().getFontcolor();
        visaRadio.setTextFill(color);
        masterRadio.setTextFill(color);
        discoverRadio.setTextFill(color);
        americanRadio.setTextFill(color);

        topMessage.setTextFill(color);
        labelCard.setFill(color);
        labelCVV.setFill(color);
        labelName.setFill(color);
        labelDate.setFill(color);

        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        Cards card = getCurrentCard();
        cardNumTxtField.setText(card.getCardNumbers());
        System.out.println(card.getExpYear());
        System.out.println(card.getExpMonth());
        datePicker.setValue( createLocalDateFromString( String.valueOf(card.getExpYear()), String.valueOf(card.getExpMonth())));
        cvvTxtField.setText( String.valueOf(card.getCVV()));
        nameTxtField.setText( card.getName());
        if(card.getCardProvider().equals("Visa")){
            visaRadio.setSelected(true);
        }
        if(card.getCardProvider().equals("Master Card")){
            masterRadio.setSelected(true);
        }
        if(card.getCardProvider().equals("American Express")){
            americanRadio.setSelected(true);
        }
        if(card.getCardProvider().equals("Discover")){
            discoverRadio.setSelected(true);
        }
    }

    public static LocalDate createLocalDateFromString(String yearStr, String monthStr) {
        int year = Integer.parseInt(yearStr);  // Convert year string to integer
        int month = Integer.parseInt(monthStr);  // Convert month string to integer

        return LocalDate.of(year, month, 1);  // Create LocalDate with the first day of the given month
    }

    /**
     * Updates the list of addresses by removing the current address and adding the new one.
     * @param allCard The list of addresses.
     * @param newCard The new address to add.
     */
    private void updateCards(List<Cards> allCard, Cards newCard) {
        allCard.removeIf(addr -> addr.getTitle().equals(getCurrentCard().getTitle()));
        allCard.add(newCard);
        setCurrentCard(newCard);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        toDefault(event, "CardBook.fxml", "Card Book");
    }

    @FXML
    void saveCard(ActionEvent event) throws IOException {
        if(checkValidation(event)){
            CardLoader cardLoader = new CardLoader("Cards.txt");
            List<Cards> allCards = cardLoader.readCards();
            Cards card = new Cards(getCurrentUser().getUserID(), cardNumTxtField.getText(), Integer.parseInt( getDate(event).substring(5,7)), Integer.parseInt( getDate(event).substring(0,4)), Integer.parseInt(cvvTxtField.getText()), nameTxtField.getText(), getCardPro());
            updateCards(allCards, card);
            cardLoader.writeCards(allCards);

            toDefault(event, "CardBook.fxml", "Card Book");
        }
    }

    public String getCardPro(){
        if(visaRadio.isSelected()){
            return visaRadio.getText();
        }
        if(americanRadio.isSelected()){
            return americanRadio.getText();
        }
        if(discoverRadio.isSelected()){
            return discoverRadio.getText();
        }
        if(masterRadio.isSelected()){
            return masterRadio.getText();
        }
        return null;
    }

    @FXML
    String getDate(ActionEvent event) {
        LocalDate myDate = datePicker.getValue();
        if(myDate != null){
            System.out.println(myDate.toString().substring(0,4));
            System.out.println(myDate.toString().substring(5,7));
            return myDate.toString();
        }
        return null;
    }

    public boolean checkValidation(ActionEvent event){
        String cardNumber = cardNumTxtField.getText();
        String cvv = cvvTxtField.getText();

        if(cardNumber.isEmpty()){
            errorMessage("Card Number Field is Empty");
            return false;
        }

        // Check if the card number contains only digits and is 16 characters long
        if(!cardNumber.matches("\\d{16}")){
            errorMessage("Number Must Contain 16 Digits Your At " + cardNumber.length() +"/16");
            return false;
        }
        if(cvv.isEmpty()){
            errorMessage("CVV Field is Empty");
            return false;
        }
        if(nameTxtField.getText().isEmpty()){
            errorMessage("Name Field is Empty");
            return false;
        }
        if(!visaRadio.isSelected() && !americanRadio.isSelected() && !discoverRadio.isSelected() && !masterRadio.isSelected()){
            errorMessage("No Radio Button Is Currently Selected");
            return false;
        }

        if(getDate(event) == null){
            errorMessage("Date is Empty");
            return false;
        }

        // CVV should be a 3 or 4 digit number depending on the card type
        if(!cvv.matches("\\d{3,4}")){
            errorMessage("CVV Must Be 3 or 4 Digits");
            return false;
        }

        System.out.println("Valid");
        return true;
    }


    public void errorMessage(String error){
    topMessage.setTextFill(getCurrentUser().getFontcolor());
    topMessage.setText(error);
}

    @FXML
    void toMenuPage(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }

}
