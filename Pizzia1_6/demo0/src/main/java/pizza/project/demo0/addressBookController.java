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
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    @FXML
    private Button undoDeleteButton;

    private Addresses undoDeleteAddress;


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
        topLabel.setTextFill(getCurrentUser().getFontcolor());
        colorDisplayPane.setBackground(new Background(new BackgroundFill(getCurrentUser().getBGcolor(), CornerRadii.EMPTY, Insets.EMPTY)));
        ArrayList<Addresses> addresses = addressLoader.readResponsesFromFile();
        ArrayList<Addresses> filteredAddresses = new ArrayList<>();
        for (Addresses newAddress : addresses){
            if(newAddress.getOwnerID() == getCurrentUser().getUserID()){
                filteredAddresses.add(newAddress);
            }
        }
        System.out.println("after after: " + filteredAddresses.size());
        fillListWithArrayList(filteredAddresses);
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

            fillListWithArrayList(newAddresses);
            undoDeleteButton.setDisable(false);
            selectedAddressString = "";
        }
    }

    @FXML
    void undoDelete(ActionEvent event) {
        ArrayList<Addresses> newAddresses = addressLoader.readResponsesFromFile();
        newAddresses.add(undoDeleteAddress);

        addressLoader.writeResponseToFile(newAddresses);
        fillListWithArrayList(newAddresses);


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
            isEditing = true;
            toDefault(event, "newAddress.fxml", "Edt Address");
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
