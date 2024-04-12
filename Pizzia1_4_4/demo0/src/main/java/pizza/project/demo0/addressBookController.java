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
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class addressBookController extends Controller implements Initializable {

    @FXML
    private Pane colorDisplayPane;

    @FXML
    private ListView<String> listView;

    public String selectedAddressString = "";

    @FXML
    private Label topLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
        System.out.println("after after: " + addresses.size());
        fillListWithArrayList(addresses);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedAddressString = listView.getSelectionModel().getSelectedItem();

            }
        });
    }

    public void fillListWithArrayList(ArrayList<Addresses> addresses){
        listView.getItems().clear();
        String[] addressArray = new String[addresses.size()];
        int counter = 0;
        for (Addresses address : addresses) {
            addressArray[counter] = address.getTitle();
            counter++;
        }
        listView.getItems().addAll(addressArray);
    }

    @FXML
    void delete(ActionEvent event) {
        ArrayList<Addresses> newAddresses = addressLoader.readResponsesFromFile();
        Iterator<Addresses> iterator = newAddresses.iterator();
        while (iterator.hasNext()) {
            Addresses address = iterator.next();
            if (address.getTitle().equals(selectedAddressString)) {
                iterator.remove();  // Use iterator's remove method
            }
        }

        addressLoader.writeResponseToFile(newAddresses);

        fillListWithArrayList(newAddresses);
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
