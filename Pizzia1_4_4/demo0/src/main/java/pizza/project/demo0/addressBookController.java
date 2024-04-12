package pizza.project.demo0;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addressBookController extends Controller implements Initializable {

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
        String[] addressArray = new String[addresses.size()];
        int counter = 0;
        for (Addresses address : addresses) {
            System.out.println(address.getStreetAddress());
            addressArray[counter] = address.getStreetAddress() + " " + address.getApartment();
            counter++;
        }
        listView.getItems().addAll(addressArray);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                String temp = listView.getSelectionModel().getSelectedItem();

            }
        });
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

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
