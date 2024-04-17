package pizza.project.demo0;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class cardController extends Controller implements Initializable {

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    public String selectedAddressString = "";

    @FXML
    private Label topLabel;

    @FXML
    private Button undoDeleteButton;

    private Cards undoDelete;

    CardLoader cardLoader = new CardLoader("Cards.txt");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topLabel.setTextFill(getCurrentUser().getFontcolor());
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        ArrayList<Cards> cards = (ArrayList<Cards>) cardLoader.readCards();
        ArrayList<Cards> filteredCards = new ArrayList<>();
        for (Cards card : cards){
            if(card.getOwnerUserId() == getCurrentUser().getUserID()){
                filteredCards.add(card);
            }
        }
        System.out.println("after after: " + filteredCards.size());
        fillListWithArrayList(cards);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedAddressString = listView.getSelectionModel().getSelectedItem();

            }
        });
    }

    public void fillListWithArrayList(ArrayList<Cards> cards){
        listView.getItems().clear();
        String[] addressArray = new String[cards.size()];
        int counter = 0;
        for (Cards card : cards) {
            addressArray[counter] = card.getTitle();
            counter++;
        }
        listView.getItems().addAll(addressArray);
    }

    @FXML
    void delete(ActionEvent event) {
        if(selectedAddressString.equals("")){
            topLabel.setText("Click on address to delete first");
        }
        else {
            ArrayList<Cards> newCards = (ArrayList<Cards>) cardLoader.readCards();

            for (Cards card : newCards) {
                if (card.getTitle().equals(selectedAddressString)) {
                    undoDelete = card;
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

            fillListWithArrayList(newCards);
            undoDeleteButton.setDisable(false);
            selectedAddressString = "";
        }
    }

    @FXML
    void undoDelete(ActionEvent event) {
        ArrayList<Cards> newCards = (ArrayList<Cards>) cardLoader.readCards();
        newCards.add(undoDelete);

        cardLoader.writeCards(newCards);
        fillListWithArrayList(newCards);


        undoDeleteButton.setDisable(true);
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        if(selectedAddressString.equals("")){
            topLabel.setText("Click on address to edit first");
        }
        else{
            for (Addresses address :addressLoader.readResponsesFromFile()){
                if(address.getTitle().equals(selectedAddressString)){
                    setCurrentAddress(address);
                }
            }

            toDefault(event, "editAddress.fxml", "Edt Address");
        }


    }

    @FXML
    void addAddress(ActionEvent event) throws IOException {
        toDefault(event, "newAddress.fxml", "Address Adder");
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        toDefault(event, "menu.fxml", "Menu Page");
    }
}
