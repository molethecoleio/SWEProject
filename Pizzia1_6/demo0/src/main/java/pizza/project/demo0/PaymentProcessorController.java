package pizza.project.demo0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentProcessorController extends Controller implements Initializable {

    @FXML
    private Label addressLabel;

    @FXML
    private Label cardLabel;

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listViewAddresses;

    @FXML
    private ListView<String> listViewCards;

    @FXML
    private Label topLabel;

    @FXML
    private Button undoAddressDelete;

    @FXML
    private Button undoCardDelete;

    CardLoader cardLoader = new CardLoader("Cards.txt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startAddressList();
        startCardList();
        setUpColors();
    }

    public void setUpColors(){
        topLabel.setTextFill(getCurrentUser().getFontcolor());
        addressLabel.setTextFill(getCurrentUser().getFontcolor());
        cardLabel.setTextFill(getCurrentUser().getFontcolor());
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void startAddressList(){
        ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
        ArrayList<Addresses> filteredAddresses = new ArrayList<>();
        for (Addresses newAddress : addresses){
            if(newAddress.getOwnerID() == getCurrentUser().getUserID()){
                filteredAddresses.add(newAddress);
            }
        }
        System.out.println("after after: " + filteredAddresses.size());
        fillAddressListWithArrayList(filteredAddresses);
    }

    public void startCardList(){
        ArrayList<Cards> cards = (ArrayList<Cards>) cardLoader.readCards();
        ArrayList<Cards> filteredCards = new ArrayList<>();
        for (Cards card : cards){
            if(card.getOwnerUserId() == getCurrentUser().getUserID()){
                filteredCards.add(card);
            }
        }
        System.out.println("after after: " + filteredCards.size());
        fillCardListWithArrayList(filteredCards);
    }

    public void fillAddressListWithArrayList(ArrayList<Addresses> addresses){
        listViewAddresses.getItems().clear();
        String[] addressArray = new String[addresses.size()];
        int counter = 0;
        for (Addresses address : addresses) {
            addressArray[counter] = address.getTitle();
            counter++;
        }
        listViewAddresses.getItems().addAll(addressArray);
    }

    public void fillCardListWithArrayList(ArrayList<Cards> allCard){
        listViewCards.getItems().clear();
        String[] addressArray = new String[allCard.size()];
        int counter = 0;
        for (Cards card : allCard) {
            addressArray[counter] = card.getTitle();
            counter++;
        }
        listViewCards.getItems().addAll(addressArray);
    }
    @FXML
    void addAddress(ActionEvent event) {

    }

    @FXML
    void addCard(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void cardDelete(ActionEvent event) {

    }

    @FXML
    void deleteAddress(ActionEvent event) {

    }

    @FXML
    void editAddress(ActionEvent event) {

    }

    @FXML
    void editCard(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

    @FXML
    void undoAddressDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void undoCardDeleteOnAction(ActionEvent event) {

    }

}
