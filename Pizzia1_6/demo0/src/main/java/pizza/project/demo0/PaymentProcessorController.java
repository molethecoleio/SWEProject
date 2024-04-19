package pizza.project.demo0;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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

    String selectedAddressString;
    String selectedCardString;

    Addresses undoDeleteAddress;
    Cards undoDeleteCard;

    CardLoader cardLoader = new CardLoader("Cards.txt");

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void navigate(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isPaymentProcessing = true;
        isEditing = false;
        startAddressList();
        startCardList();
        setUpColors();

        listViewAddresses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedAddressString = listViewAddresses.getSelectionModel().getSelectedItem();

            }
        });

        listViewCards.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedCardString = listViewCards.getSelectionModel().getSelectedItem();

            }
        });
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
    void addAddress(ActionEvent event) throws IOException {
        toDefault(event, "newAddress.fxml", "Address Adder");
    }


    @FXML
    void deleteAddress(ActionEvent event) {
        if(selectedAddressString.equals("")){
            topLabel.setText("Click on address to delete first");
        }
        else {
            ArrayList<Addresses> newAddresses = addressLoader.readResponsesFromFile();

            for (Addresses address : newAddresses) {
                if (address.getTitle().equals(selectedAddressString)) {
                    undoDeleteAddress = address;
                }
            }
            Iterator<Addresses> iterator = newAddresses.iterator();
            while (iterator.hasNext()) {
                Addresses address = iterator.next();
                if (address.getTitle().equals(selectedAddressString)) {
                    iterator.remove();  // Use iterator's remove method
                }
            }

            addressLoader.writeResponseToFile(newAddresses);

            fillAddressListWithArrayList(newAddresses);
            undoAddressDelete.setDisable(false);
            selectedAddressString = "";
        }
    }

    @FXML
    void undoAddressDeleteOnAction(ActionEvent event) {
        ArrayList<Addresses> newAddresses = addressLoader.readResponsesFromFile();
        newAddresses.add(undoDeleteAddress);

        addressLoader.writeResponseToFile(newAddresses);
        fillAddressListWithArrayList(newAddresses);


        undoAddressDelete.setDisable(true);
    }

    @FXML
    void editAddress(ActionEvent event) throws IOException {
        if(selectedAddressString == null || selectedAddressString.equals("")){
            topLabel.setText("Click On Address To Edit First");
        }
        else{
            for (Addresses address :addressLoader.readResponsesFromFile()){
                if(address.getTitle().equals(selectedAddressString)){
                    setCurrentAddress(address);
                }
            }
            isEditing = true;
            toDefault(event, "newAddress.fxml", "Edt Address");
        }


    }

    @FXML
    void addCard(ActionEvent event) throws IOException {
        toDefault(event, "newCard.fxml", "Card Adder");
    }

    @FXML
    void cardDelete(ActionEvent event) {
        if(selectedAddressString.equals("")){
            topLabel.setText("Click on address to delete first");
        }
        else {
            ArrayList<Cards> newCards = (ArrayList<Cards>) cardLoader.readCards();

            for (Cards card : newCards) {
                if (card.getTitle().equals(selectedAddressString)) {
                    undoDeleteCard = card;
                }
            }
            Iterator<Cards> iterator = newCards.iterator();
            while (iterator.hasNext()) {
                Cards card = iterator.next();
                if (card.getTitle().equals(selectedAddressString)) {
                    iterator.remove();  // Use iterator's remove method
                }
            }

            cardLoader.writeCards(newCards);

            fillCardListWithArrayList(newCards);
            undoCardDelete.setDisable(false);
            selectedAddressString = "";
        }
    }

    @FXML
    void undoCardDeleteOnAction(ActionEvent event) {
        ArrayList<Cards> newCards = (ArrayList<Cards>) cardLoader.readCards();
        newCards.add(undoDeleteCard);

        cardLoader.writeCards(newCards);
        fillCardListWithArrayList(newCards);


        undoCardDelete.setDisable(true);
    }

    @FXML
    void editCard(ActionEvent event) throws IOException {
        if(selectedCardString == null || selectedCardString.equals("")){
            topLabel.setText("Click On Card To Edit");
        }
        else{
            for (Cards card : CardLoader.readCards()){
                if(card.getTitle().equals(selectedCardString)){
                    setCurrentCard(card);
                }
            }
            isEditing = true;
            toDefault(event, "newCard.fxml", "New Card");
        }


    }


    @FXML
    void submit(ActionEvent event) throws IOException {
        if(selectedCardString == null || selectedCardString.equals("")){
            topLabel.setText("Click On Card To Pay With");
        }
        else{
            if(selectedAddressString == null || selectedAddressString.equals("")){
                topLabel.setText("Click On Billing Address");
            }
            else {
                for (Addresses address :addressLoader.readResponsesFromFile()){
                    if(address.getTitle().equals(selectedAddressString)){
                        setCurrentAddress(address);
                    }
                }

                for (Cards card : CardLoader.readCards()){
                    if(card.getTitle().equals(selectedCardString)){
                        setCurrentCard(card);
                    }
                }


                toDefault(event, "FinializeOrder.fxml", "Finalize Order");
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        isPaymentProcessing = false;
        toDefault(event, "createOrder.fxml", "Order Maker");
    }



}
